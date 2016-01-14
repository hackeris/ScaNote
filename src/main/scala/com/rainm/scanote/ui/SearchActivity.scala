package com.rainm.scanote.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.rainm.scanote.{TR, R, TypedFindView}

/**
  * Created by hackeris on 16/1/14.
  */
class SearchActivity extends AppCompatActivity with TypedFindView {

  override def onCreate(bundle: Bundle): Unit = {
    super.onCreate(bundle)
    setContentView(R.layout.search_note)

    setSupportActionBar(findView(TR.toolbar))
  }

}
