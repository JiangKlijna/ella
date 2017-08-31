package cn.jiangklijna.ella.ui.view

import android.content.Context
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import cn.jiangklijna.ella.R
import cn.jiangklijna.ella.common.f
import cn.jiangklijna.ella.entry.EnglishArticle
import com.facebook.drawee.view.SimpleDraweeView

/**
 * Created by jiangKlijna on 8/13/2017.
 */
class EnglishCardView(context: Context) : LinearLayout(context, null, 0) {

	val title: TextView
	val img: SimpleDraweeView

	init {
		LayoutInflater.from(context).inflate(R.layout.view_englishcard, this, true)
		title = f<TextView>(R.id.view_ec_title)
		img = f<SimpleDraweeView>(R.id.view_ec_img)
	}

//	private var isInit = false

	fun init(a: EnglishArticle) {
//		if (isInit) return
//		isInit = true
		title.text = a.title
		img.setImageURI(a.img)
//		f<TextView>(R.id.view_ec_title).text = a.title
//		f<SimpleDraweeView>(R.id.view_ec_img).run {
//			setImageURI(a.img)
//		}
	}

}