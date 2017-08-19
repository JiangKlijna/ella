package cn.jiangklijna.ella.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.jiangklijna.ella.R
import cn.jiangklijna.ella.common.println
import cn.jiangklijna.ella.model.Setting

/**
 * Created by jiangKlijna on 8/12/2017.
 */
abstract class FrgEnglishCards : Fragment(), SwipeRefreshLayout.OnRefreshListener {

	var isInit = false

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
			inflater.inflate(R.layout.frg_englishcards, container, false)


	override fun onActivityCreated(savedInstanceState: Bundle?) {
		super.onActivityCreated(savedInstanceState)
		if (!isInit) {
			isInit = true
			onInit()
		}
	}

	// 初始化,会调用一次
	fun onInit() {
		val swipeRefreshLayout = view!!.findViewById(R.id.frg_englishcards_sf) as SwipeRefreshLayout
		swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light, android.R.color.holo_orange_light, android.R.color.holo_red_light)
		swipeRefreshLayout.setOnRefreshListener(this)
		swipeRefreshLayout.isRefreshing = true
	}

	var ds: Setting.Data? = null
		set(value) {
			if (field == null) field = value
		}

	fun getTitle(): String = ds!!.title

	// 当下拉刷新的时候
	abstract override fun onRefresh()
	// 当上拉加载的时候
	abstract fun onLoadMore()

}