package com.rainm.scanote.utils

import java.io.{FileInputStream, File}

import android.os.Environment

/**
  * Created by hackeris on 16/1/14.
  */
object FileUtils {
  def getSDPath: String = {
    val sdCardExist = Environment.getExternalStorageState()
      .equals(Environment.MEDIA_MOUNTED)
    if (sdCardExist) {
      Environment.getExternalStorageDirectory.toString
    } else {
      ""
    }
  }

  def readStringFromFile(path: String): String = {
    val file = new File(path)
    if (!file.exists()) {
      ""
    } else {
      val fis = new FileInputStream(file)
      val fileLength = fis.available()
      val buffer = new Array[Byte](fileLength)
      fis.read(buffer)
      new String(buffer, "UTF-8")
    }
  }
}
