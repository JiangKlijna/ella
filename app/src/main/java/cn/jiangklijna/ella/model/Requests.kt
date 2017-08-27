package cn.jiangklijna.ella.model

import cn.jiangklijna.ella.entry.EnglishArticle
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException

/**
 * Created by jiangKlijna on 8/20/2017.
 */
object Requests {

	fun EnglishCardsWithHtml(t: Setting.Type, pages: Int, runnable: Http.Runnable<List<EnglishArticle>>) =
			Http.get("${t.url}&pages=$pages",
					object : Callback {
						override fun onFailure(call: Call?, e: IOException?) = Http.Event(runnable, emptyList()).send()
						override fun onResponse(call: Call?, response: Response) =
								if (response.isSuccessful)
									Http.Event(runnable, Bean.EnglishCardsWithHtml(response.body()!!.byteStream()!!, t)).send()
								else onFailure(call, null)
					})

}