package com.rainm.scanote.ui


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.{Menu, MenuItem}
import android.widget.EditText
import com.rainm.scanote.{R, TR, TypedFindView}

/**
  * Created by hackeris on 16/1/14.
  */

object EditNoteActivity {
  val NOTE_TITLE_KEY = "note_title"
  val NOTE_CONTENT_KEY = "note_content"
}

class EditNoteActivity extends AppCompatActivity with TypedFindView {

  lazy val title = findViewById(R.id.text_note_title).asInstanceOf[EditText]
  lazy val content = findViewById(R.id.text_note_content).asInstanceOf[EditText]

  //lazy val markdownView = findViewById(R.id.markdown_note_preview).asInstanceOf[MarkdownView]

  override def onCreate(savedInstanceState: Bundle) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.edit_note)

    setSupportActionBar(findView(TR.toolbar))

    val intent = getIntent
    title.setText(intent.getStringExtra(EditNoteActivity.NOTE_TITLE_KEY))
    content.setText(intent.getStringExtra(EditNoteActivity.NOTE_CONTENT_KEY))
    
//    content.addTextChangedListener(new TextWatcher {
//      override def beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int): Unit = {}
//
//      override def onTextChanged(s: CharSequence, start: Int, before: Int, count: Int): Unit = updateMarkdownView()
//
//      override def afterTextChanged(s: Editable): Unit = updateMarkdownView()
//    })
  }

  override def onCreateOptionsMenu(menu: Menu): Boolean = {
    getMenuInflater.inflate(R.menu.menu_edit_note, menu)
    true
  }

  def validateNote(): Boolean = {
    title.getText.toString.isEmpty
  }

//  def updateMarkdownView(): Unit = {
//    markdownView.loadMarkdown(content.getText.toString)
//  }

  override def onOptionsItemSelected(item: MenuItem): Boolean = {
    item.getItemId match {
      case R.id.action_done => {
        if (!validateNote()) {
          Snackbar.make(title, "Input title please", Snackbar.LENGTH_LONG)
            .setAction("Action", null).show()
        }
        val intent = new Intent()
        intent.putExtra(EditNoteActivity.NOTE_TITLE_KEY, title.getText.toString)
        intent.putExtra(EditNoteActivity.NOTE_CONTENT_KEY, content.getText.toString)
        setResult(Activity.RESULT_OK, intent)
        finish()
        return true
      }
      case _ =>
    }
    super.onOptionsItemSelected(item)
  }
}