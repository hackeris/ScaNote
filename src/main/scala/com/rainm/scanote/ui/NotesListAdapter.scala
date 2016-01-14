package com.rainm.scanote.ui

import java.util

import android.content.Context
import android.view.{LayoutInflater, View, ViewGroup}
import android.widget.{BaseAdapter, TextView}
import com.rainm.scanote.R
import com.rainm.scanote.model.SimpleNote

/**
  * Created by hackeris on 16/1/14.
  */
class NotesListAdapter(context: Context) extends BaseAdapter {

  var notes: util.List[SimpleNote] = new util.ArrayList[SimpleNote]()

  def setNotes(notes: util.List[SimpleNote]): Unit = {
    this.notes = notes
  }

  override def getItemId(position: Int): Long = position

  override def getCount: Int = notes.size()

  override def getItem(position: Int): AnyRef = notes.get(position)

  override def getView(position: Int, convertView: View, parent: ViewGroup): View = {

    val listItem: View = {
      if (convertView == null) {
        val tmp = LayoutInflater.from(context).inflate(R.layout.note_list_item, null)
        tmp.setTag(new NoteItemHolder(
          tmp.findViewById(R.id.text_note_title).asInstanceOf[TextView],
          tmp.findViewById(R.id.text_note_content).asInstanceOf[TextView]))
        tmp
      }
      else convertView
    }

    val holder: NoteItemHolder = listItem.getTag.asInstanceOf[NoteItemHolder]
    holder.title.setText(notes.get(position).title)
    holder.content.setText(notes.get(position).content)

    listItem
  }

  class NoteItemHolder(val title: TextView, val content: TextView)

}
