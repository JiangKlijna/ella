package cn.jiangklijna.ella.model

import cn.jiangklijna.ella.entry.EnglishArticle
import cn.jiangklijna.ella.entry.Word
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException

/**
 * Created by jiangKlijna on 8/20/2017.
 */
object Requests {

    fun getEnglishCards(t: Setting.Type, pages: Int, runnable: Http.Runnable<List<EnglishArticle>>) =
            Http.get("${t.url}pages=$pages",
                    object : Callback {
                        override fun onFailure(call: Call?, e: IOException?) = Http.Event(runnable, emptyList()).send()
                        override fun onResponse(call: Call?, response: Response) =
                                if (response.isSuccessful) {
                                    println(response.request().url())
                                    val list = if (Setting.isJson(t))
                                        Bean.EnglishCardsWithJson(response.body()!!.string()!!, t)
                                    else Bean.EnglishCardsWithHtml(response.body()!!.byteStream()!!, t)
                                    Http.Event(runnable, list).send()
                                } else onFailure(call, null)
                    })

    fun translate(word: String, runnable: Http.Runnable<Word?>) =
            Http.get("http://m.youdao.com/dict?q=$word", object : Callback {
                override fun onFailure(p0: Call?, p1: IOException?) = Http.Event(runnable, null).send()
                override fun onResponse(call: Call?, response: Response) =
                        if (response.isSuccessful) {
                            println(response.request().url())
                            val w = Bean.WordWithHtml(response.body()!!.byteStream()!!, word)
                            Http.Event(runnable, w).send()
                        } else onFailure(call, null)
            })
}