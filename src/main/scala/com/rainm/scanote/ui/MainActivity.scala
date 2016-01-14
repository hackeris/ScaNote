/*
 * Copyright (C) 2015 47 Degrees, LLC http://47deg.com hello@47deg.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may obtain
 * a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.rainm.scanote.ui

import java.util

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.{Snackbar, FloatingActionButton}
import android.support.v7.app.AppCompatActivity
import android.view.{MenuItem, Menu, View}
import android.view.View.OnClickListener
import android.widget.AdapterView.OnItemClickListener
import android.widget.{AdapterView, ListView}
import com.rainm.scanote.db.DBManager
import com.rainm.scanote.model.SimpleNote
import com.rainm.scanote.{TR, R, TypedFindView}

object MainActivity {
  val REQUEST_NOTE_CONTENT = 0x1
}

class MainActivity extends AppCompatActivity with TypedFindView {

  lazy val noteListView: ListView = findView(TR.list_notes)
  lazy val addButton: FloatingActionButton = findView(TR.fab)

  lazy val listAdapter: NotesListAdapter = new NotesListAdapter(this)

  var notes: util.List[SimpleNote] = null

  override def onCreate(bundle: Bundle) {
    super.onCreate(bundle)
    setContentView(R.layout.main)

    initView()
    reloadNoteList()
  }

  def initView(): Unit = {

    setSupportActionBar(findView(TR.toolbar))
    addButton.setOnClickListener(new OnClickListener {
      override def onClick(v: View): Unit = {
        startActivityForResult(
          new Intent(MainActivity.this, classOf[EditNoteActivity]),
          MainActivity.REQUEST_NOTE_CONTENT)
      }
    })
    noteListView.setOnItemClickListener(new OnItemClickListener {
      override def onItemClick(parent: AdapterView[_], view: View, position: Int, id: Long): Unit = {
        onNoteItemClick(parent, view, position, id)
      }
    })
    noteListView.setAdapter(listAdapter)
  }

  def onNoteItemClick(parent: AdapterView[_], view: View, position: Int, id: Long): Unit = {
    val intent = new Intent(MainActivity.this, classOf[ViewNoteActivity])
    val note = notes.get(position)
    intent.putExtra(ViewNoteActivity.KEY_NOTE_ID, String.valueOf(note.id))
    intent.putExtra(ViewNoteActivity.KEY_NOTE_TITLE, note.title)
    intent.putExtra(ViewNoteActivity.KEY_NOTE_CONTENT, note.content)
    startActivity(intent)
  }

  override def onCreateOptionsMenu(menu: Menu): Boolean = {
    getMenuInflater.inflate(R.menu.menu_main, menu)
    true
  }

  override def onOptionsItemSelected(item: MenuItem): Boolean = {
    item.getItemId match {
      case R.id.action_search => {
        startActivity(new Intent(this, classOf[SearchActivity]))
      }
      case R.id.action_export =>
      case R.id.action_import =>
      case R.id.action_settings =>
      case _ =>
    }
    super.onOptionsItemSelected(item)
  }

  def refreshNoteList(): Unit = {
    listAdapter.notifyDataSetChanged()
  }

  def reloadNoteList(): Unit = {
    val db = new DBManager(this)
    notes = db.queryAllSimpleNotes()
    db.close()
    listAdapter.setNotes(notes)
    listAdapter.notifyDataSetChanged()
  }

  override def onActivityResult(request: Int, result: Int, data: Intent): Unit = {
    request match {
      case MainActivity.REQUEST_NOTE_CONTENT => {
        if (result == Activity.RESULT_OK) {
          Snackbar.make(addButton, "succeed to add note", Snackbar.LENGTH_LONG)
            .setAction("Action", null).show()

          val note = new SimpleNote(data.getStringExtra(EditNoteActivity.NOTE_TITLE_KEY),
            data.getStringExtra(EditNoteActivity.NOTE_CONTENT_KEY))
          notes.add(note)

          val db = new DBManager(this)
          db.addSimpleNote(note)
          db.close()

          reloadNoteList()
        }
      }
      case _ =>
    }
  }

  override def onResume(): Unit = {
    reloadNoteList()
    super.onResume()
  }
}
