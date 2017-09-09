package cn.jiangklijna.ella.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import cn.jiangklijna.ella.R
import cn.jiangklijna.ella.common.XAdapter
import cn.jiangklijna.ella.common.f
import cn.jiangklijna.ella.entry.EnglishArticle
import cn.jiangklijna.ella.model.Setting
import cn.jiangklijna.ella.ui.view.EnglishCardView

/**
 * Created by jiangKlijna on 8/12/2017.
 */
abstract class FrgEnglishCards : Fragment(), SwipeRefreshLayout.OnRefreshListener {

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
			swipeRefresh?.isRefreshing = true
			onRefresh()
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
		listView = view!!.f<ListView>(R.id.frg_englishcards_lv)
		listView!!.run {
			this@FrgEnglishCards.adapter = object : XAdapter<EnglishArticle>(list, context) {
				override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
					val holder = getHolder(context, convertView, EnglishCardView::class.java)
					holder.getConvertView<EnglishCardView>().set(getItem(position))
					return holder.getConvertView()
				}
			}
			adapter = this@FrgEnglishCards.adapter
		}

		swipeRefresh = view!!.f<SwipeRefreshLayout>(R.id.frg_englishcards_sf)
		swipeRefresh!!.run {
			setOnRefreshListener(this@FrgEnglishCards)
			setColorSchemeResources(R.color.colorPrimary)
		}
//		onRefresh()
	}

	var pages = 0
	var list = arrayListOf<EnglishArticle>()

	var swipeRefresh: SwipeRefreshLayout? = null
	var listView: ListView? = null
	var adapter: XAdapter<EnglishArticle>? = null

	var ds: Setting.Type? = null
		set(value) {
			if (field == null) field = value
		}

	fun getTitle(): String = ds!!.title

	// 当下拉刷新的时候
	abstract override fun onRefresh()

	// 当上拉加载的时候
	abstract fun onLoadMore()

	fun addCards(datas: List<EnglishArticle>) {
		list.addAll(datas)
		adapter?.notifyDataSetChanged()
		swipeRefresh?.isRefreshing = false
	}

	fun clearCards() {
		list.clear()
		adapter?.notifyDataSetChanged()
		swipeRefresh?.isRefreshing = false
	}
}