package com.rainm.scanote.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.{Editable, TextWatcher}
import android.view.{MenuItem, Menu, View}
import android.view.View.OnClickListener
import android.widget.ListView
import com.rainm.scanote.db.DBManager
import com.rainm.scanote.{R, TR, TypedFindView}

/**
  * Created by hackeris on 16/1/14.
  */
class SearchActivity extends AppCompatActivity with TypedFindView {

  lazy val searchResult = findViewById(R.id.list_search_result).asInstanceOf[ListView]

  override def onCreate(bundle: Bundle): Unit = {
    super.onCreate(bundle)
    setContentView(R.layout.search_note)

    val toolbar = findView(TR.toolbar)
    setSupportActionBar(toolbar)
    getSupportActionBar.setDisplayHomeAsUpEnabled(true)
    getSupportActionBar.setDisplayShowHomeEnabled(true)
    toolbar.setNavigationOnClickListener(new OnClickListener {
      override def onClick(v: View): Unit = {
        finish()
      }
    })
    doSearchAction()
    setOnInputSearch()
  }

  def setOnInputSearch(): Unit = {
    findView(TR.text_key_words).addTextChangedListener(new TextWatcher {
      override def beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int): Unit = {}

      override def onTextChanged(s: CharSequence, start: Int, before: Int, count: Int): Unit = doSearchAction()

      override def afterTextChanged(s: Editable): Unit = doSearchAction()
    })
  }

  def doSearchAction(): Unit = {
    val keyWord = findView(TR.text_key_words).getText.toString

    val manager = new DBManager(this)
    val notes = manager.searchForSimpleNote(keyWord)
    manager.close()
    val adapter = new NotesListAdapter(this)
    adapter.setNotes(notes)
    searchResult.setAdapter(adapter)
  }

  override def onCreateOptionsMenu(menu: Menu): Boolean = {
    getMenuInflater.inflate(R.menu.menu_search, menu)
    true
  }

  override def onOptionsItemSelected(item: MenuItem): Boolean = {
    item.getItemId match {
      case R.id.action_clear => {
        findView(TR.text_key_words).setText("")
        return true
      }
      case _ =>
    }
    super.onOptionsItemSelected(item)
  }
}
