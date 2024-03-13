package com.white.plugins

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import net.mamoe.mirai.event.events.GroupMessageEvent
import net.mamoe.mirai.utils.ExternalResource.Companion.uploadAsImage
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import java.awt.image.BufferedImage
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.concurrent.CopyOnWriteArrayList
import javax.imageio.ImageIO


object ImageInfo {
    // https://prts.wiki/images/c/c0/立绘_干员名字_1.png?image_process=format,webp/quality,Q_90  = 立绘png
    // https://prts.wiki/images/5/59/Bg_default.png = 背景图片
    // https://static.prts.wiki/charinfo/img/star_6.png = 星级图片
    suspend fun image(event: GroupMessageEvent, memberAtt: CopyOnWriteArrayList<String>?, getMember: CopyOnWriteArrayList<String>?, name: String) {
        //背景图片
        val bgDefault = withContext(Dispatchers.IO) {
            ImageIO.read(File("data/com.white.ArkBot/default/Bg_default.png"))
        }
        //立绘图片
        val imageRead = File("data/com.white.ArkBot/干员默认立绘/${name}.png")
        //若本地没有立绘则开始下载
        if (!imageRead.isFile) {
            //获取的网站 url
            val imageUrl = "https://prts.wiki/w/文件:立绘_${name}_1.png"
            //将获取的网站 url 中的图片链接放进列表
            val getUrl: Elements = Jsoup.connect(imageUrl).get().select("meta[property=og:image]")
            //发起 Http 链接，从获取到的图片链接提取图片
            val doc = Jsoup.connect(getUrl[0].attr("content")).ignoreContentType(true).execute().bodyAsBytes()
            //保存图片的路径和格式
            val outputFile = File("data/com.white.ArkBot/干员默认立绘/${name}.png")
            try {
                //写入目标路径
                withContext(Dispatchers.IO) {
                    FileOutputStream(outputFile).use { fos ->
                        fos.write(doc)
                    }
                }
                println("该干员立绘不存在 成功下载到本地：${outputFile.absolutePath}")
            } catch (e: IOException) {
                println("无法下载图片：$e")
            }
        }
        //设置图片大小并创建
        val height = 1080
        val width = 1920
        val getImage = BufferedImage(width,height,BufferedImage.TYPE_INT_RGB)
        val graphics = getImage.createGraphics()

        //设置背景图片
        graphics.drawImage(bgDefault,0,0,1920,1080,null)

        //创建临时文件
        val tempFile = withContext(Dispatchers.IO) {
            File.createTempFile(name, ".png")
        }
        withContext(Dispatchers.IO) {
            ImageIO.write(getImage, "png", tempFile)
        }

        event.group.sendMessage(tempFile.uploadAsImage(event.group))
    }
}