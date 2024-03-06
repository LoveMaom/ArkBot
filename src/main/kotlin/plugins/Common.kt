package com.whitememory.plugins

import org.jsoup.Jsoup
import org.jsoup.nodes.Document

object Common {
    var url = "https://prts.wiki/w/"

    val star: Document? = Jsoup.connect("${url}干员一览").get()
}