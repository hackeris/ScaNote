package com.rainm.scanote.model

import java.util

import com.rainm.scanote.utils.FileUtils
import org.json.JSONStringer

/**
  * Created by hackeris on 16/1/14.
  */
object NoteExporter {

  def exportNotesToSdCard(notes: util.List[SimpleNote], path: String): Unit = {

    FileUtils.writeStringToFile(notesToJsonString(notes), path)
  }

  def notesToJsonString(notes: util.List[SimpleNote]): String = {
    val arrayStringer = new JSONStringer()

    val length = notes.size()

    arrayStringer.array()
    for (i <- 0 to length - 1) {
      arrayStringer.`object`()
      val note = notes.get(i)
      arrayStringer.key("title")
      arrayStringer.value(note.title)
      arrayStringer.key("content")
      arrayStringer.value(note.content)
      arrayStringer.key("last_updated")
      arrayStringer.value(note.last_updated)
      arrayStringer.endObject()
    }
    arrayStringer.endArray()

    arrayStringer.toString
  }
}
