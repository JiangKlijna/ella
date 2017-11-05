package cn.jiangklijna.ella.ui.fragment

import cn.jiangklijna.ella.common.App
import cn.jiangklijna.ella.entry.EnglishArticle
import cn.jiangklijna.ella.model.Http
import cn.jiangklijna.ella.model.Requests

/**
 * Created by jiangKlijna on 8/19/2017.
 * 根据greendao翻页
 */
class FrgEcSql : FrgEnglishCards() {

    private val r = object : Http.Runnable<List<EnglishArticle>> {
        override fun run(data: List<EnglishArticle>) = addCards(data)
    }

    override fun onRefresh() {
        pages = 1
        list.clear()
        val data = ed.queryBuilder().limit(6).offset((pages - 1) * 6).list()
        addCards(data)
//        Requests.listOfEnglishCard(ds!!, pages, r)
    }

    //Requests.listOfEnglishCard(ds!!, ++pages, r)
    override fun onLoadMore() {
        val data = ed.queryBuilder().limit(6).offset((pages++) * 6).list()
        addCards(data)
    }

    companion object {
        private val ed = App.mDaoSession!!.englishArticleDao!!
    }
}