package com.rainm.scanote.db

import android.content.Context
import android.database.sqlite.{SQLiteDatabase, SQLiteOpenHelper}
import com.rainm.scanote.model.SimpleNote

/**
  * Created by hackeris on 16/1/14.
  */
object DBHelper {
  val DB_VERSION = 0x1
  val DB_NAME = "action_note_db"
}

class DBHelper(context: Context)
  extends SQLiteOpenHelper(context, DBHelper.DB_NAME, null, DBHelper.DB_VERSION) {

  override def onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int): Unit = {
    //TODO: add upgrade code here.
  }

  override def onCreate(db: SQLiteDatabase): Unit = {
    db.execSQL("CREATE TABLE IF NOT EXISTS " + SimpleNote.TABLE_NAME + SimpleNote.DDL)
  }
}
