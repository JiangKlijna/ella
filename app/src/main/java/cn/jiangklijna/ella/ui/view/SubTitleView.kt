package cn.jiangklijna.ella.ui.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import cn.jiangklijna.ella.R
import cn.jiangklijna.ella.common.f
import cn.jiangklijna.ella.model.Bean

/**
 * Created by leil7 on 2017/10/2. ella
 */
class SubTitleView(context: Context) : LinearLayout(context, null, 0) {

    val en: TextView
    val zh: TextView

    init {
        LayoutInflater.from(context).inflate(R.layout.view_subtitle, this, true)
        en = f(R.id.view_subtitle_en)
        zh = f(R.id.view_subtitle_zh)
    }

    fun set(s: Bean.SubTitle, isEn: Boolean, isZh: Boolean) {
        en.text = s.Sentence
        zh.text = s.Sentence_cn
        en.visibility = if (isEn) View.VISIBLE else View.GONE
        zh.visibility = if (isZh) View.VISIBLE else View.GONE
    }
}