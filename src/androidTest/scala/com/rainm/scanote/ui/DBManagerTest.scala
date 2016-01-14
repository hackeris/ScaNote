package com.rainm.scala.ui

import android.test.AndroidTestCase
import com.rainm.scanote.db.DBManager
import com.rainm.scanote.model.SimpleNote

/**
  * Created by hackeris on 16/1/14.
  */
class DBManagerTest extends AndroidTestCase {

  val dbManager = new DBManager(getContext)

  def DBManagerTest(): Unit = {}

  override def setUp(): Unit = {

  }

  def testAddSimpleNote(): Unit = {
    val note: SimpleNote = new SimpleNote("title", "content")
    dbManager.addSimpleNote(note)
  }

  override def tearDown(): Unit = {
    dbManager.close()
  }

}
