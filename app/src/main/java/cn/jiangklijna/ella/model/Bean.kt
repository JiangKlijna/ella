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

    data class SubTitle(
            val EndTiming: Float,
            val IdIndex: Int,
            val ParaId: Int,
            val Sentence: String,
            val Sentence_cn: String,
            val Timing: Float
    )

    fun listOfEnglishCard(s: String, t: Setting.Type): List<EnglishArticle> {
        try {
            val datas = JSON.parseObject(s).getJSONArray("data")
            return List(datas.size, {
                val jo = datas.getJSONObject(it)
                val date = jo.getString("CreateTime").split(" ")[0]
                EnglishArticle(
                        jo.getLongValue("Id"),
                        jo.getString("Title_cn"),
                        jo.getString("Pic"), date,
                        jo.getString("Sound"), t.id)
            })
        } catch (e: Exception) {
            e.printStackTrace()
            return emptyList()
        }
    }

    fun listOfSubTitle(s: String): List<SubTitle> {
        try {
            val datas = JSON.parseObject(s).getJSONArray("data")
            return List(datas.size, {
                val jo = datas.getJSONObject(it)
                SubTitle(
                        EndTiming = jo.getFloat("EndTiming"),
                        IdIndex = jo.getInteger("IdIndex"),
                        ParaId = jo.getInteger("ParaId"),
                        Sentence = jo.getString("Sentence"),
                        Sentence_cn = jo.getString("Sentence_cn"),
                        Timing = jo.getFloat("Timing")
                )
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