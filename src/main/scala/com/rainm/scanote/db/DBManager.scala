package com.rainm.scanote.db

import java.text.SimpleDateFormat
import java.util.Date

import android.content.{ContentValues, Context}
import android.database.sqlite.SQLiteDatabase
import com.rainm.scanote.model.SimpleNote

/**
  * Created by hackeris on 16/1/14.
  */
class DBManager(context: Context) {

  val db: SQLiteDatabase = new DBHelper(context).getWritableDatabase

  def addSimpleNote(note: SimpleNote): Unit = {
    if (note.last_updated != null && !note.last_updated.isEmpty) {
      db.execSQL("INSERT INTO note VALUES(null, ?, ?, ?)",
        Array(note.title, note.content, note.last_updated))
    } else {
      db.execSQL("INSERT INTO note VALUES(null, ?, ?, DATETIME())",
        Array(note.title, note.content))
    }
  }

  def updateSimpleNote(note: SimpleNote): Unit = {
    val cv: ContentValues = new ContentValues()
    cv.put("title", note.title)
    cv.put("content", note.content)
    val dateFormat: SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    val now: Date = new Date();
    cv.put("last_updated", dateFormat.format(now))
    db.update(SimpleNote.TABLE_NAME, cv, "id=?", Array(String.valueOf(note.id)))
  }

  def close(): Unit = {
    db.close()
  }

}
