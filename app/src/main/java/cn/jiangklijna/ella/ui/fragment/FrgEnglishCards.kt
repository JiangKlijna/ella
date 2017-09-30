package cn.jiangklijna.ella.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.jiangklijna.ella.R
import cn.jiangklijna.ella.common.XAdapter
import cn.jiangklijna.ella.common.f
import cn.jiangklijna.ella.entry.EnglishArticle
import cn.jiangklijna.ella.model.Setting
import cn.jiangklijna.ella.ui.view.EnglishCardView
import cn.jiangklijna.ella.ui.view.LoadMoreListView

/**
 * Created by jiangKlijna on 8/12/2017.
 */
abstract class FrgEnglishCards : Fragment(), SwipeRefreshLayout.OnRefreshListener, LoadMoreListView.OnLoadMoreListener {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
            inflater.inflate(R.layout.frg_englishcards, container, false)


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        onInit()
        if (savedInstanceState != null) {
            ds = savedInstanceState.getSerializable("ds") as Setting.Type?
            val list = savedInstanceState.getSerializable("list") as List<EnglishArticle>
            for (e in list) this.list.add(e)
            adapter?.notifyDataSetChanged()
        } else {
            listView?.isRefreshing = true
//            swipeRefresh?.isRefreshing = true
//            onLoadMore()
//			  onRefresh()
        }
    }

    // 保存状态,在onActivityCreated恢复
    override fun onSaveInstanceState(outState: Bundle) {
        outState.putSerializable("ds", ds)
        outState.putSerializable("list", list)
    }

    override fun onDestroy() {
        super.onDestroy()
        clearCards()
    }

    // 初始化,会调用一次
    fun onInit() {
        listView = view!!.f<LoadMoreListView>(R.id.frg_englishcards_lv)
        listView!!.run {
            this@FrgEnglishCards.adapter = object : XAdapter<EnglishArticle>(list, context) {
                override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
                    val holder = getHolder(context, convertView, EnglishCardView::class.java)
                    holder.getConvertView<EnglishCardView>().set(getItem(position))
                    return holder.getConvertView()
                }
            }
            adapter = this@FrgEnglishCards.adapter
            setColorSchemeResource(R.color.colorPrimary)
            onLoadMoreListener = this@FrgEnglishCards
        }

        swipeRefresh = view!!.f<SwipeRefreshLayout>(R.id.frg_englishcards_sf)
        swipeRefresh!!.run {
            setOnRefreshListener(this@FrgEnglishCards)
            setColorSchemeResources(R.color.colorPrimary)
        }
    }

    var pages = 0
    var list = arrayListOf<EnglishArticle>()

    var swipeRefresh: SwipeRefreshLayout? = null
    var listView: LoadMoreListView? = null
    var adapter: XAdapter<EnglishArticle>? = null

    var ds: Setting.Type? = null
        set(value) {
            if (field == null) field = value
        }

    fun getTitle(): String = ds!!.title

    // 当下拉刷新的时候
    abstract override fun onRefresh()

    // 当上拉加载的时候
    abstract override fun onLoadMore()

    fun addCards(datas: List<EnglishArticle>) {
        list.addAll(datas)
        adapter?.notifyDataSetChanged()
        listView?.isRefreshing = false
        swipeRefresh?.isRefreshing = false
    }

    fun clearCards() {
        list.clear()
        adapter?.notifyDataSetChanged()
    }

    companion object {
        fun getFragments(): List<FrgEnglishCards> {
            return List<FrgEnglishCards>(Setting.ts.size, {
                FrgEcNet().apply {
                    ds = Setting.ts[it]
                }
            })
        }
    }
}