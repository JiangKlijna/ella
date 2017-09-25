package cn.jiangklijna.ella.model

import cn.jiangklijna.ella.entry.EnglishArticle
import cn.jiangklijna.ella.entry.Word
import com.alibaba.fastjson.JSON
import org.jsoup.Jsoup
import java.io.InputStream

/**
 * Created by jiangKlijna on 8/13/2017.
 */
object Bean {

    fun EnglishCardsWithHtml(i: InputStream, t: Setting.Type): List<EnglishArticle> {
        try {
            val divs = Jsoup.parse(i, "utf-8", "").getElementsByAttributeValue("class", "c")
            return List(divs.size, {
                val div = divs[it]
                val title = div.getElementsByTag("p")[0].text()
                val img = div.getElementsByTag("img")[0].attr("src")
                val id = img.substring(img.lastIndexOf('/') + 1).split(".")[0]
                val url = t.playurl + id
                val date = div.getElementsByTag("small")[0].text().split(" ")[0]
                EnglishArticle(id.toInt(), title, url, img, date, t.id)
            })
        } catch (e: Exception) {
            e.printStackTrace()
            return emptyList()
        }
    }

    fun EnglishCardsWithJson(s: String, t: Setting.Type): List<EnglishArticle> {
        try {
            val datas = JSON.parseObject(s).getJSONArray("data")
            return List(datas.size, {
                val jo = datas.getJSONObject(it)
                val id = jo.getIntValue("VoaId")
                val date = jo.getString("CreatTime").split(" ")[0]
                EnglishArticle(id, jo.getString("Title_cn"), t.playurl + id, jo.getString("Pic"), date, t.id)
            })
        } catch (e: Exception) {
            e.printStackTrace()
            return emptyList()
        }
    }

    fun WordWithHtml(i: InputStream, en: String): Word? {
        try {
            val body = Jsoup.parse(i, "utf-8", "").body()
            val ec = body.getElementById("ec")
            val lis = ec.getElementsByTag("li")
            val s = StringBuilder()
            for (li in lis) s.append(li.text()).append('\n')
            if (s.endsWith('\n')) s.deleteCharAt(s.length - 1)
            val phonetics = ec.getElementsByClass("phonetic")
            val us: String?
            val uk: String?
            if (phonetics.isEmpty()) {
                us = ""
                uk = ""
            } else if (phonetics.size == 1) {
                uk = phonetics[0].text()
                us = uk
            } else {
                uk = phonetics[0].text()
                us = phonetics[1].text()
            }
            return Word(en, s.toString(), us, uk)
        } catch (e: Exception) {
            return null
        }
    }

    fun Word.getUsPhoneticUrl(): String = "http://dict.youdao.com/dictvoice?type=2&audio=" + en
    fun Word.getUkPhoneticUrl(): String = "http://dict.youdao.com/dictvoice?type=1&audio=" + en

}