package com.white.plugins.condition

import java.io.File

object CheckFiles {
    fun checkFiles(vararg files: File): Boolean {
        //循环判断是否存在该图片
        for (file in files) {
            if (!file.exists()) {
                return false
            }
        }
        return true
    }
}