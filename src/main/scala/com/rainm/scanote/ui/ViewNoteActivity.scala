package com.rainm.scanote.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.{MenuItem, Menu}
import com.rainm.scanote.db.DBManager
import com.rainm.scanote.model.SimpleNote
import com.rainm.scanote.{R, TypedFindView}
import us.feras.mdv.MarkdownView

/**
  * Created by hackeris on 16/1/14.
  */

object ViewNoteActivity {
  val KEY_NOTE_TITLE = "title"
  val KEY_NOTE_CONTENT = "content"
  val KEY_NOTE_ID = "id"

  val REQUEST_EDIT_NOTE = 0x1
}

class ViewNoteActivity extends AppCompatActivity with TypedFindView {

  lazy val note: SimpleNote = {
    val intent = getIntent
    new SimpleNote(Integer.parseInt(intent.getStringExtra(ViewNoteActivity.KEY_NOTE_ID)),
      intent.getStringExtra(ViewNoteActivity.KEY_NOTE_TITLE),
      intent.getStringExtra(ViewNoteActivity.KEY_NOTE_CONTENT))
  }

  lazy val markdownView = findViewById(R.id.text_note_content).asInstanceOf[MarkdownView]

  override def onCreate(bundle: Bundle): Unit = {
    super.onCreate(bundle)
    setContentView(R.layout.view_note)

    setTitle(note.title)

    loadNoteContent(note.content)
  }

  def loadNoteContent(content: String): Unit = {
    markdownView.loadMarkdown(content)
  }

  override def onCreateOptionsMenu(menu: Menu): Boolean = {
    getMenuInflater.inflate(R.menu.menu_view_note, menu)
    true
  }

  override
  def onOptionsItemSelected(item: MenuItem): Boolean = {
    item.getItemId match {
      case R.id.action_edit => {
        val intent = new Intent(ViewNoteActivity.this, classOf[EditNoteActivity])
        intent.putExtra(EditNoteActivity.NOTE_TITLE_KEY, note.title)
        intent.putExtra(EditNoteActivity.NOTE_CONTENT_KEY, note.content)
        startActivityForResult(intent, ViewNoteActivity.REQUEST_EDIT_NOTE);
        return true
      }
      case _ =>
    }
    super.onOptionsItemSelected(item)
  }

  override def onActivityResult(request: Int, result: Int, data: Intent): Unit = {
    request match {
      case ViewNoteActivity.REQUEST_EDIT_NOTE => {
        if (result == Activity.RESULT_OK) {
          Snackbar.make(markdownView, "succeed to change note", Snackbar.LENGTH_LONG)
            .setAction("Action", null).show()

          note.title = data.getStringExtra(EditNoteActivity.NOTE_TITLE_KEY)
          note.content = data.getStringExtra(EditNoteActivity.NOTE_CONTENT_KEY)

          val manager = new DBManager(this)
          manager.updateSimpleNote(note)
          manager.close()

          loadNoteContent(note.content)
        }
      }
    }
  }

}
