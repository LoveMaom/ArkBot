package com.white

import com.white.plugins.Wiki
import com.white.plugins.data.Data
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
        Data.reload()
        GlobalEventChannel.subscribeAlways<GroupMessageEvent> {
            Wiki.command(this)
        }
    }
}