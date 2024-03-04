package com.LoveMaom.plugins.getInfo

import com.LoveMaom.plugins.Common
import org.jsoup.HttpStatusException
import org.jsoup.Jsoup
import org.jsoup.select.Elements
import java.util.concurrent.CopyOnWriteArrayList

object GetMember {
    /*
    获取某个干员信息
    0=获得方式
    1=上线日期
    2=干员职业
    3=职业描述
    4=干员星级
    5=再部署时间文字
    6=再部署时间数值
    7=部署费用文字
    8=部署费用数值
    9=阻挡数文字
    10=阻挡数值
    11=攻击间隔文字
    12=攻击间隔时间
     */
    fun main(name: String, list: Elements) : CopyOnWriteArrayList<String>? {
        //记录集合
        val info: CopyOnWriteArrayList<String> = CopyOnWriteArrayList()
        try {
            //遍历集合
            for (index in list.indices) {
                val element = list[index]
                //获取获得方式内容
                val gain = element.select("table[class=wikitable]")
                val profession = element.select("table[class=wikitable logo]")
                //遍历多个相同table标题内容
                for (table in gain) {
                    //获取正确的获得方式内容
                    val trContent = table.select("tr").text()
                    if (trContent.contains("获得方式")) {
                        //获取获得方式
                        info.add(table.select("td")[0].text())
                        //获取上线日期
                        info.add(table.select("td")[1].text())
                    }
                }
                //获取干员职业
                info.add(profession.select("td")[0].text())
                //获取职业信息
                info.add(profession.select("td")[1].text())
                //获取干员星级
                val star = Jsoup.connect("${Common.url}干员一览").get().select("div[data-zh=${name}]").attr("data-rarity").toInt()+1
                info.add(star.toString())
                //获取属性
                val again = element.select("table[class=wikitable logo char-extra-attr-table]")
                //获取再部署字
                info.add(again.select("th")[0].text())
                //获取再部署时间
                info.add(again.select("td")[0].text())
                //获取初始部署费用
                info.add(again.select("th")[1].text())
                //获取初始部署费用数值
                info.add(again.select("td")[1].text())
                //获取阻挡数
                info.add(again.select("th")[2].text())
                //获取阻挡数值
                info.add(again.select("td")[2].text())
                //获取攻击间隔
                info.add(again.select("th")[3].text())
                //获取攻击间隔数值
                info.add(again.select("td")[3].text())
                return info
            }
        } catch (_: HttpStatusException) {
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }
}