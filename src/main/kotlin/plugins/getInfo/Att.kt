package com.whitememory.plugins.getInfo

import org.jsoup.select.Elements
import java.util.concurrent.CopyOnWriteArrayList

object Att {
    //生命属性值
    private var hpName: String = ""
    private var hp1: String = ""
    private var hp2: String = ""
    private var hp3: String = ""
    private var hp4: String = ""
    private var hp5: String = ""

    //攻击属性值
    private var atkName: String = ""
    private var atk1: String = ""
    private var atk2: String = ""
    private var atk3: String = ""
    private var atk4: String = ""
    private var atk5: String = ""

    //防御属性值
    private var defName: String = ""
    private var def1: String = ""
    private var def2: String = ""
    private var def3: String = ""
    private var def4: String = ""
    private var def5: String = ""

    //法抗属性值
    private var spdefName: String = ""
    private var spdef1: String = ""
    private var spdef2: String = ""
    private var spdef3: String = ""
    private var spdef4: String = ""
    private var spdef5: String = ""

    fun main(list: Elements): CopyOnWriteArrayList<String> {
        for (index in list.indices) {
            val element = list[index]
            //获取阶段属性
            val info = element.select("table[class=wikitable logo char-base-attr-table]")
            //生命上限
            hpName = info.select("tr")[1].select("th")[0].text()
            //精0 1级属性
            hp1 = info.select("tr")[1].select("td")[0].text()
            //精0 满级属性
            hp2 = info.select("tr")[1].select("td")[1].text()
            //精1 满级属性
            hp3 = info.select("tr")[1].select("td")[2].text()
            //精2 满级属性
            hp4 = info.select("tr")[1].select("td")[3].text()
            //信赖生命值
            if (info.select("tr")[1].select("td").size == 5) {
                hp5 = info.select("tr")[1].select("td")[4].text()
            }

            //攻击上限
            atkName = info.select("tr")[2].select("th")[0].text()
            //精0 1级属性
            atk1 = info.select("tr")[2].select("td")[0].text()
            //精0 满级属性
            atk2 = info.select("tr")[2].select("td")[1].text()
            //精1 满级属性
            atk3 = info.select("tr")[2].select("td")[2].text()
            //精2 满级属性
            atk4 = info.select("tr")[2].select("td")[3].text()
            //信赖生命值
            if (info.select("tr")[2].select("td").size == 5) {
                atk5 = info.select("tr")[2].select("td")[4].text()
            }

            //防御上限
            defName = info.select("tr")[3].select("th")[0].text()
            //精0 1级属性
            def1 = info.select("tr")[3].select("td")[0].text()
            //精0 满级属性
            def2 = info.select("tr")[3].select("td")[1].text()
            //精1 满级属性
            def3 = info.select("tr")[3].select("td")[2].text()
            //精2 满级属性
            def4 = info.select("tr")[3].select("td")[3].text()
            //信赖生命值
            if (info.select("tr")[3].select("td").size == 5) {
                def5 = info.select("tr")[3].select("td")[4].text()
            }

            //法抗上限
            spdefName = info.select("tr")[4].select("th")[0].text()
            //精0 1级属性
            spdef1 = info.select("tr")[4].select("td")[0].text()
            //精0 满级属性
            spdef2 = info.select("tr")[4].select("td")[1].text()
            //精1 满级属性
            spdef3 = info.select("tr")[4].select("td")[2].text()
            //精2 满级属性
            spdef4 = info.select("tr")[4].select("td")[3].text()
            //信赖生命值
            if (info.select("tr")[4].select("td").size == 5) {
                spdef5 = info.select("tr")[4].select("td")[4].text()
            }
        }
        val attList: CopyOnWriteArrayList<String> = CopyOnWriteArrayList(
            listOf(
                hpName,
                atkName,
                defName,
                spdefName,
                hp1,
                atk1,
                def1,
                spdef1,
                hp2,
                atk2,
                def2,
                spdef2,
                hp3,
                atk3,
                def3,
                spdef3,
                hp4,
                atk4,
                def4,
                spdef4,
                hp5,
                atk5,
                def5,
                spdef5
            )
        )
        return attList
    }
}