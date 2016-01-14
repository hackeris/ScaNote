package com.rainm.scanote.model

/**
  * Created by hackeris on 16/1/14.
  */
class SimpleNote(var title: String, var content: String) {

  var id: Int = 0
  var last_updated: String = null

  def this(_id: Int, title: String, content: String) = {
    this(title, content)
    id = _id
  }

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
