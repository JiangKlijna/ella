package cn.jiangklijna.ella.ui.fragment

import cn.jiangklijna.ella.entry.EnglishArticle
import cn.jiangklijna.ella.model.Http
import cn.jiangklijna.ella.model.Requests

/**
 * Created by jiangKlijna on 8/19/2017.
 * 根据网络请求翻页
 */
class FrgEcNet : FrgEnglishCards() {

	private val r = object : Http.Runnable<List<EnglishArticle>> {
		override fun run(data: List<EnglishArticle>) = addCards(data)
	}

	override fun onRefresh() {
		pages = 1
		list.clear()
		Requests.getEnglishCards(ds!!, pages, r)
	}

	override fun onLoadMore() = Requests.getEnglishCards(ds!!, ++pages, r)
}