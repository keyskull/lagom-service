package me.keyskull.play

import java.io.File



/**
  * Created by keyskull on 2017/1/26.
  */
object util {
  def changeFilePathToLinux(file: File): String = {
    val 盘符 = """\b{1}\w:""".r
    val toBackslash = """\\\\|\\""".r
  //    println(file.getAbsolutePath)
  //    println(toBackslash.replaceAllIn(file.getAbsolutePath,"/"))
    val content = 盘符.replaceAllIn(toBackslash.replaceAllIn(file.getAbsolutePath,"/"), "")
    println(content)
    content

  }

}
