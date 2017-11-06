package cn.jiangklijna.ella.ui.fragment

import cn.jiangklijna.ella.common.App

/**
 * Created by jiangKlijna on 8/19/2017.
 * 根据greendao翻页
 */
class FrgEcSql : FrgEnglishCards() {

    override fun onRefresh() {
        list.clear()
        pages = 0
        onLoadMore()
    }

    override fun onLoadMore() {
        val data = ed.queryBuilder().limit(6).offset((pages++) * 6).list()
        addCards(data)
    }

    companion object {
        private val ed = App.mDaoSession!!.englishArticleDao!!
    }
}