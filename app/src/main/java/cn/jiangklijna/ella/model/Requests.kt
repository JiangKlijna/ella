package cn.jiangklijna.ella.model

import cn.jiangklijna.ella.entry.EnglishArticle
import cn.jiangklijna.ella.entry.Word
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException
import cn.jiangklijna.ella.model.Setting.getUrl

/**
 * Created by jiangKlijna on 8/20/2017.
 */
object Requests {

    fun listOfEnglishCard(t: Setting.Type, page: Int, runnable: Http.Runnable<List<EnglishArticle>>) =
            Http.post(t.getUrl(page), object : Callback {
                override fun onFailure(call: Call?, e: IOException?) = Http.Event(runnable, emptyList()).send()
                override fun onResponse(call: Call?, response: Response) =
                        if (response.isSuccessful) {
                            println(response.request().url())
                            val list = Bean.listOfEnglishCard(response.body()!!.string(), t)
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