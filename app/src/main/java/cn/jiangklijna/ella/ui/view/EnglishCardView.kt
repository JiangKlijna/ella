package cn.jiangklijna.ella.ui.view

import android.content.Context
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import cn.jiangklijna.ella.R
import cn.jiangklijna.ella.common.f
import cn.jiangklijna.ella.entry.EnglishArticle

/**
 * Created by jiangKlijna on 8/13/2017.
 */
class EnglishCardView(context: Context) : LinearLayout(context, null, 0) {

	init {
		LayoutInflater.from(context).inflate(R.layout.view_englishcard, this, true)
	}

	private var isInit = false

	fun init(a: EnglishArticle) {
		if (isInit) return
		isInit = true

		f<TextView>(R.id.view_ec_title).text = a.title
	}

}