package com.rainm.scanote.model

import java.util

import android.content.Context
import com.rainm.scanote.db.DBManager
import com.rainm.scanote.utils.FileUtils
import org.json.{JSONObject, JSONArray}

/**
  * Created by hackeris on 16/1/14.
  */
object NoteImporter {

  def importNotesFromSdCard(path: String): util.List[SimpleNote] = {

    val jsonString = FileUtils.readStringFromFile(path)
    parseNotesFromJson(jsonString)
  }

  def parseNotesFromJson(json: String): util.List[SimpleNote] = {

    val notes = new util.ArrayList[SimpleNote]

    val jsonArray = new JSONArray(json)
    val length = jsonArray.length()
    for (i <- 0 to length - 1) {
      val jsonNote = jsonArray.get(i).asInstanceOf[JSONObject]
      val note = new SimpleNote(jsonNote.getString("title"), jsonNote.getString("content"))
      note.last_updated = jsonNote.getString("last_updated")
      notes.add(note)
    }

    notes
  }

}
