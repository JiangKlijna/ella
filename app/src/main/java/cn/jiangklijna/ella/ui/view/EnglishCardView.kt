package cn.jiangklijna.ella.ui.view

import android.content.Context
import android.view.LayoutInflater
import android.widget.LinearLayout
import cn.jiangklijna.ella.R

/**
 * Created by jiangKlijna on 8/13/2017.
 */
class EnglishCardView(context: Context) : LinearLayout(context, null, 0) {

	init {
		LayoutInflater.from(context).inflate(R.layout.view_englishcard, this, true)
	}


}