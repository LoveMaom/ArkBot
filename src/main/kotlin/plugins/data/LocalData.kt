package com.white.plugins.data

import com.white.plugins.Common
import kotlinx.serialization.json.*
import java.io.File

object LocalData {

    val url = "data/com.white.ArkBot/module/"


    //特种职业图片
    val specialty = File("${url}job/特种.png")

    //术师职业图片
    val mage = File("${url}job/术师.png")

    //狙击职业图片
    val shooter = File("${url}job/狙击.png")

    //近卫职业图片
    val guards = File("${url}job/近卫.png")

    //辅助职业图片
    val aided = File("${url}job/辅助.png")

    //先锋职业图片
    val pioneer = File("${url}job/先锋.png")

    //重装职业图片
    val tank = File("${url}job/重装.png")

    //医疗职业图片
    val heal = File("${url}job/医疗.png")


    //背景图片
    val background = File("${url}default/背景.png")

    //星级图片
    val star_1 = File("${url}default/一星.png")
    val star_2 = File("${url}default/二星.png")
    val star_3 = File("${url}default/三星.png")
    val star_4 = File("${url}default/四星.png")
    val star_5 = File("${url}default/五星.png")
    val star_6 = File("${url}default/六星.png")


    val jobBranch = "${url}jobBranch/"
    // 获取所有分支职业
    fun jobBranch(): JsonArray {
        return Json.parseToJsonElement(
            Common.star.select("div[id=filter-filter]").html()
        ).jsonObject["filters"]!!.jsonArray[0].jsonObject.getValue("filter").jsonArray[1].jsonObject.getValue(
            "cbt"
        ).jsonArray
    }
}