package com.white.plugins.data

import com.white.plugins.Common
import com.white.plugins.condition.CheckFiles
import org.jsoup.Jsoup
import java.io.File
import java.io.IOException

object DownLoadData {
    //下载职业组件
    suspend fun job() {
        val allFilesExist = CheckFiles.checkFiles(
            LocalData.specialty,
            LocalData.mage,
            LocalData.shooter,
            LocalData.guards,
            LocalData.aided,
            LocalData.pioneer,
            LocalData.tank,
            LocalData.heal
        )

        //创建文件夹
        Common.createFolder("${LocalData.url}job")

        if (!allFilesExist) {
            val job = listOf("特种", "术师", "狙击", "近卫", "辅助", "先锋", "重装", "医疗")
            for (name in job) {
                //获取图片数据
                val jobDownload =
                    Jsoup.connect("https://static.prts.wiki/charinfo/img/class_${name}.png").ignoreContentType(true)
                        .execute().bodyAsBytes()
                //存储图片地址
                val outputFile = File("${LocalData.url}job/${name}.png")
                //下载图片并存储
                Common.downLoad(outputFile, jobDownload)
            }

        }

    }

    //下载默认组件
    suspend fun default() {

        val allFilesExist = CheckFiles.checkFiles(
            LocalData.background,
            LocalData.star_1,
            LocalData.star_2,
            LocalData.star_3,
            LocalData.star_4,
            LocalData.star_5,
            LocalData.star_6
        )

        //创建文件夹
        Common.createFolder("${LocalData.url}default")

        //获取链接 并命名
        val urls = listOf(
            "https://prts.wiki/images/5/59/Bg_default.png",
            "https://static.prts.wiki/charinfo/img/star_1.png",
            "https://static.prts.wiki/charinfo/img/star_2.png",
            "https://static.prts.wiki/charinfo/img/star_3.png",
            "https://static.prts.wiki/charinfo/img/star_4.png",
            "https://static.prts.wiki/charinfo/img/star_5.png",
            "https://static.prts.wiki/charinfo/img/star_6.png"
        )
        val urlsName = listOf("背景", "一星", "二星", "三星", "四星", "五星", "六星")

        if (!allFilesExist) {
            for (num in urls.indices) {
                //获取图片数据
                val jobDownload = Jsoup.connect(urls[num]).ignoreContentType(true).execute().bodyAsBytes()
                //存储图片地址
                val outputFile = File("${LocalData.url}default/${urlsName[num]}.png")
                //下载图片并存储
                Common.downLoad(outputFile, jobDownload)
            }
        }

    }

    //下载分支图片内容
    suspend fun jobBranch() {
        var allFilesExist: Boolean
        //创建文件夹
        Common.createFolder("${LocalData.url}jobBranch")
        //遍历分支的文字内容
        for (name in LocalData.jobBranch()) {
            //去除双引号
            val branchName = name.toString().substring(1)
            val branch = branchName.substring(0, branchName.length - 1)
            //判断 是否已经下载图片数据
            allFilesExist = CheckFiles.checkFiles(File("${LocalData.jobBranch}${branch}"))
            if (!allFilesExist) {
                //图片链接
                val bUrl = "https://static.prts.wiki/charinfo/img/branch/${branch}.png"
                try {
                    //获取图片数据
                    val branchDownload = Jsoup.connect(bUrl).ignoreContentType(true).execute().bodyAsBytes()
                    //存储图片地址
                    val outputFile = File("${LocalData.url}jobBranch/${branch}.png")
                    //下载图片并存储
                    Common.downLoad(outputFile, branchDownload)
                } catch (e: IOException) {
                    println("${branch}没有图片,暂不下载")
                    continue
                }
            }
        }
    }


}