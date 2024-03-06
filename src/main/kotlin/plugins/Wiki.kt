package com.white.plugins

import com.white.plugins.getInfo.Att
import com.white.plugins.getInfo.GetMember
import net.mamoe.mirai.event.events.GroupMessageEvent
import net.mamoe.mirai.message.data.content
import org.jsoup.Jsoup
import org.jsoup.select.Elements
import java.io.IOException

object Wiki {
    suspend fun command(event: GroupMessageEvent) {
        if ("查询干员" == event.message.content.take(4)) {
            val operators = event.message.content.split("查询干员")[1]
            println("值:" + Common.star!!.select("div[data-zh=${operators}]").text())
            try {
                //从干员列表中查询是否有该干员
                if (Common.star!!.select("div[data-zh=${operators}]").text() != "") {
                    //获取网页内容
                    val doc = Jsoup.connect("${Common.url}${operators}").timeout(3000).get()
                    //将html内容放进集合中
                    val list: Elements = doc.select("div[class=mw-parser-output]")
                    //调用图片制作
                    ImageInfo.image(event, Att.main(list), GetMember.main(operators, list), operators)
                    println("执行成功")
                } else { event.group.sendMessage("没有这个干员") }
            } catch (_: IOException) {
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}