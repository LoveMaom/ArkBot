package com.white

import com.white.plugins.Wiki
import com.white.plugins.data.DownLoadData
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.event.GlobalEventChannel
import net.mamoe.mirai.event.events.GroupMessageEvent
import net.mamoe.mirai.utils.info

object ArkBot : KotlinPlugin(
    JvmPluginDescription(
        id = "com.white.ArkBot",
        name = "ArkBot",
        version = "0.0.1",
    ) {

        author("WhiteMemory")
    }
) {
    override fun onEnable() {
        logger.info { "方舟机器人启动成功~" }
        startDownload()
        GlobalEventChannel.subscribeAlways<GroupMessageEvent> {
            Wiki.command(this)
        }
    }
    private fun startDownload() {
        // 仅执行一次
        runBlocking {
            launch {
                delay(1000) // 模拟耗时操作
                // 下载本地图片数据
                DownLoadData.job()
                DownLoadData.default()
                DownLoadData.jobBranch()
            }
        }
    }
}