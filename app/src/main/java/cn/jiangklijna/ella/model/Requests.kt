package cn.jiangklijna.ella.model

import cn.jiangklijna.ella.entry.EnglishArticle
import cn.jiangklijna.ella.ui.fragment.FrgEcHtml
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException

/**
 * Created by jiangKlijna on 8/20/2017.
 */
object Requests {

	fun getEnglishCards(t: Setting.Type, pages: Int, runnable: Http.Runnable<List<EnglishArticle>>) =
			Http.get("${t.url}&pages=$pages",
					object : Callback {
						override fun onFailure(call: Call?, e: IOException?) = Http.Event(runnable, emptyList()).send()
						override fun onResponse(call: Call?, response: Response) =
								if (response.isSuccessful) {
									val list = if (t.frgclass == FrgEcHtml::class.java)
										Bean.EnglishCardsWithHtml(response.body()!!.byteStream()!!, t)
									else Bean.EnglishCardsWithJson(response.body()!!.string()!!, t)
									Http.Event(runnable, list).send()
								} else onFailure(call, null)
					})

}