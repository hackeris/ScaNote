package com.rainm.scanote.model

/**
  * Created by hackeris on 16/1/14.
  */
class SimpleNote(val title: String, val content: String) {

  var id: Int = 0
  var last_updated: String = null

  override def equals(other: Any): Boolean = {
    val ano: SimpleNote = other.asInstanceOf[SimpleNote]
    content.equals(ano.content) && title.equals(ano.title)
  }
}

object SimpleNote {
  val TABLE_NAME = "note"
  val DDL = "(" +
    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
    "title VARCHAR, content VARCHAR, " +
    "last_updated DATETIME" +
    ")"
}
