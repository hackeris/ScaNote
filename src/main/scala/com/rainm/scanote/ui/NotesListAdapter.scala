package com.rainm.scanote.ui

import java.util

import android.content.Context
import android.view.{View, ViewGroup}
import android.widget.{BaseAdapter, TextView}
import com.rainm.scanote.model.SimpleNote

/**
  * Created by hackeris on 16/1/14.
  */
class NotesListAdapter(context: Context) extends BaseAdapter {

  var notes: util.List[SimpleNote] = null

  def setNotes(notes: util.List[SimpleNote]): Unit = {
    this.notes = notes
  }

  override def getItemId(position: Int): Long = position

  override def getCount: Int = notes.size()

  override def getItem(position: Int): AnyRef = notes.get(position)

  override def getView(position: Int, convertView: View, parent: ViewGroup): View = {
    //convertView= LayoutInflater.from(context).inflate(R)
    null
  }

  class NoteItemHolder(title: TextView, content: TextView)

}
