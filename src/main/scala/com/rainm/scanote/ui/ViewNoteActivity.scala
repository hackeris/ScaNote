package com.rainm.scanote.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
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

}
