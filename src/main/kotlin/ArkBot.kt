package com.whitememory

import com.whitememory.plugins.Wiki
import com.whitememory.plugins.data.Data
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.event.GlobalEventChannel
import net.mamoe.mirai.event.events.GroupMessageEvent
import net.mamoe.mirai.utils.info

object ArkBot : KotlinPlugin(
    JvmPluginDescription(
        id = "com.whiteMemory.ArkBot",
        name = "ArkBot",
        version = "0.0.1",
    ) {

        author("LoveMaom")
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