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

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.view.{MenuItem, Menu, View}
import android.view.View.OnClickListener
import android.widget.ListView
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
  }

  override def onCreateOptionsMenu(menu: Menu): Boolean = {
    getMenuInflater.inflate(R.menu.menu_main, menu)
    true
  }

  override def onOptionsItemSelected(item: MenuItem): Boolean = {
    item.getItemId match {
      case R.id.action_search =>
      case R.id.action_export =>
      case R.id.action_import =>
      case R.id.action_settings =>
      case _ =>
    }
    super.onOptionsItemSelected(item)
  }

}
