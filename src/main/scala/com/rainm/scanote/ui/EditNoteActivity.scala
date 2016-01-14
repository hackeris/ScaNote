package com.rainm.scanote.ui


import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.rainm.scanote.{TypedFindView, R}

/**
  * Created by hackeris on 16/1/14.
  */
class EditNoteActivity extends AppCompatActivity with TypedFindView {
  override def onCreate(savedInstanceState: Bundle) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.edit_note)
  }
}