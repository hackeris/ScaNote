package com.rainm.scala.ui

import android.test.AndroidTestCase
import com.rainm.scanote.db.{DBHelper, DBManager}
import com.rainm.scanote.model.SimpleNote

/**
  * Created by hackeris on 16/1/14.
  */
class DBManagerTest extends AndroidTestCase {

  var manager: DBManager = null

  def DBManagerTest(): Unit = {}

  override def setUp(): Unit = {
    DBHelper.setDbName("test_db")
    manager = new DBManager(getContext)

    manager.deleteAllSimpleNote()

    manager.addSimpleNote(new SimpleNote("title", "content"))
    manager.addSimpleNote(new SimpleNote("title 2", "content 2"))

    super.setUp()
  }

  def testAddSimpleNote(): Unit = {
    val note: SimpleNote = new SimpleNote("title", "content")
    manager.addSimpleNote(note)

    assert(manager.queryAllSimpleNotes().size() == 3)
  }

  def testDeleteAllSimpleNote(): Unit = {
    manager.deleteAllSimpleNote()

    assert(manager.queryAllSimpleNotes().size() == 0)
  }

  def testUpdateSimpleNote(): Unit = {
    val newContent = "hello world"

    val note = manager.queryAllSimpleNotes().get(0)
    note.content = newContent
    manager.updateSimpleNote(note)

    val updatedNote = manager.queryAllSimpleNotes().get(0)

    assert(updatedNote.content.equals(newContent))
  }

  def testDeleteSimpleNote(): Unit = {
    val note = manager.queryAllSimpleNotes().get(0)
    manager.deleteSimpleNote(note)

    assert(manager.queryAllSimpleNotes().size() == 1)
  }

  override def tearDown(): Unit = {
    manager.close()
    super.tearDown()
  }

}
