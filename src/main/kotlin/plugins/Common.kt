package com.white.plugins

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

object Common {
    var url = "https://prts.wiki/w/"

    val star: Document = Jsoup.connect("${url}干员一览").get()

    //下载图片
    suspend fun downLoad(outputFile: File,byte: ByteArray) {
        try {
            //写入目标路径
            withContext(Dispatchers.IO) {
                FileOutputStream(outputFile).use { fos ->
                    fos.write(byte)
                }
            }
        } catch (e: IOException) {
            println("无法下载图片：$e")
        }
    }

    //创建文件夹
    fun createFolder(path: String) {

        val filePath = File(path)

        // 创建文件夹及其父文件夹（不存在的话）
        if (!filePath.exists()) {
            val created = filePath.mkdirs()

            if (created) {
                println("文件夹创建成功")
            } else {
                println("文件夹创建失败")
            }
        } else {
            println("文件夹已存在 不创建")
        }

    }
}