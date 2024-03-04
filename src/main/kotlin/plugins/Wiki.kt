package com.LoveMaom.plugins

import com.LoveMaom.plugins.getInfo.Att
import com.LoveMaom.plugins.getInfo.GetMember
import net.mamoe.mirai.event.events.GroupMessageEvent
import net.mamoe.mirai.message.data.content
import org.jsoup.Jsoup
import org.jsoup.select.Elements
import java.io.IOException

object Wiki {
    suspend fun command(event: GroupMessageEvent) {
        if ("查询干员" == event.message.content.take(4)) {
            val operators = event.message.content.split("查询干员")[1]
            try {
                //获取网页内容
                val doc = Jsoup.connect("${Common.url}${operators}").timeout(3000).get()
                if (doc != null) {
                    //将html内容放进集合中
                    val list: Elements = doc.select("div[class=mw-parser-output]")
                    val end = GetMember.main(operators, list)
                    val end2 = Att.main(list)
                    if (end != null && end2 != null) event.group.sendMessage(
                        "${end[0]}" +
                                "\n${end[1]}" +
                                "\n${end[2]}" +
                                "\n${end[3]}" +
                                "\n${end[4]}星" +
                                "\n${end[5]}=${end[6]}" +
                                "\n${end[7]}=${end[8]}" +
                                "\n${end[9]}=${end[10]}" +
                                "\n${end[11]}=${end[12]}" +
                                "\n精0一级:" +
                                "\n${end2[0]}:${end2[4]}|${end2[1]}:${end2[5]}|${end2[2]}:${end2[6]}|${end2[3]}:${end2[7]}" +
                                "\n精0满级:" +
                                "\n${end2[0]}:${end2[8]}|${end2[1]}:${end2[9]}|${end2[2]}:${end2[10]}|${end2[3]}:${end2[11]}" +
                                "\n精1满级:" +
                                "\n${end2[0]}:${end2[12]}|${end2[1]}:${end2[13]}|${end2[2]}:${end2[14]}|${end2[3]}:${end2[15]}" +
                                "\n精2满级:" +
                                "\n${end2[0]}:${end2[16]}|${end2[1]}:${end2[17]}|${end2[2]}:${end2[18]}|${end2[3]}:${end2[19]}" +
                                "\n信赖加成:" +
                                "\n${end2[0]}:${end2[20]}|${end2[1]}:${end2[21]}|${end2[2]}:${end2[22]}|${end2[3]}:${end2[23]}"
                    ) else event.group.sendMessage("没有这个干员呢")


                }
            } catch (_: IOException) {
            } catch (e: Exception) {
                event.group.sendMessage("没有这个干员呢")
                e.printStackTrace()
            }
        }
    }
}