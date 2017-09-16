package cn.jiangklijna.ella.ui.view

import android.content.Context
import android.util.AttributeSet
import android.widget.ListView

/**
 * Created by leil7 on 2017/9/16. ella
 */
class LoadMoreListView(context: Context?, attrs: AttributeSet?) : ListView(context, attrs, 0) {

    var onLoadMoreListener: OnLoadMoreListener? = null
    var isRefreshing: Boolean = false
        set(value) {
            if (value != field) {

                field = value
            }
        }

    interface OnLoadMoreListener {
        fun onLoadMore()
    }

    init {
//        addFooterView()
        isRefreshing = true

        onLoadMoreListener
    }
}
