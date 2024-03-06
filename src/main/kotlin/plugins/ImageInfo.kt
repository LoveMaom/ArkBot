package com.whitememory.plugins

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import net.mamoe.mirai.event.events.GroupMessageEvent
import net.mamoe.mirai.utils.ExternalResource.Companion.uploadAsImage
import java.awt.image.BufferedImage
import java.io.File
import java.net.URL
import java.util.concurrent.CopyOnWriteArrayList
import javax.imageio.ImageIO

object ImageInfo {
    // https://prts.wiki/images/c/c0/立绘_干员名字_1.png?image_process=format,webp/quality,Q_90  = 立绘png
    // https://prts.wiki/images/5/59/Bg_default.png = 背景图片
    // https://static.prts.wiki/charinfo/img/star_6.png = 星级图片
    suspend fun image(event: GroupMessageEvent, memberAtt: CopyOnWriteArrayList<String>?, getMember: CopyOnWriteArrayList<String>?, name: String) {
        //背景图片
        val bgDefault = ImageIO.read(getUrlImage("https://prts.wiki/images/5/59/Bg_default.png","背景"))
        //干员立绘图片
        val wikiImage = ImageIO.read(getUrlImage("https://prts.wiki/images/c/c0/立绘_${name}_1.png?image_process=format,webp/quality,Q_90","立绘"))
        //干员星级图片
        val star = ImageIO.read(getUrlImage("https://static.prts.wiki/charinfo/img/star_${getMember!![4]}.png","星"))

        //设置图片大小并创建
        val height = 1080
        val width = 1920
        val getImage = BufferedImage(height,width,BufferedImage.TYPE_INT_RGB)
        val graphics = getImage.createGraphics()

        //设置背景图片
        graphics.drawImage(bgDefault,0,0,1920,1080,null)

        //创建临时文件
        val tempFile = File.createTempFile(name, ".png")
        ImageIO.write(getImage,"png",tempFile)

        event.group.sendMessage(tempFile.uploadAsImage(event.group))
    }
    // 下载头像图片
    private suspend fun getUrlImage(url: String,name: String): File {
        // 获取图片
        val urlFile = File("${name}.png")

        // 下载图片
        withContext(Dispatchers.IO) {
            URL(url).openStream()
        }.use { input ->
            urlFile.outputStream().use { output ->
                input.copyTo(output)
            }
        }

        return urlFile
    }
}