package cn.jiangklijna.ella.ui.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.jiangklijna.ella.R
import cn.jiangklijna.ella.common.println

/**
 * Created by jiangKlijna on 8/12/2017.
 */
open class FrgEnglishCards : Fragment(), SwipeRefreshLayout.OnRefreshListener {

	var isInit = false

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
			inflater.inflate(R.layout.frg_englishcards, container, false)


	override fun onActivityCreated(savedInstanceState: Bundle?) {
		super.onActivityCreated(savedInstanceState)
		if (!isInit) {
			onInit()
			isInit = true
		}
	}

	// 初始化,只会被调用一次
	fun onInit() {
		val swipeRefreshLayout = view!!.findViewById(R.id.frg_englishcards_sf) as SwipeRefreshLayout
		swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light, android.R.color.holo_orange_light, android.R.color.holo_red_light)
		swipeRefreshLayout.setOnRefreshListener(this)
		swipeRefreshLayout.isRefreshing = true
	}


	override fun onRefresh() {
		"onRefresh".println()
	}

	override fun onDetach() {
		super.onDetach()
	}

}