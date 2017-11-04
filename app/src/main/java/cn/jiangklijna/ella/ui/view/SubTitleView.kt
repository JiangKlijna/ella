package cn.jiangklijna.ella.ui.view

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.RippleDrawable
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import cn.jiangklijna.ella.R
import cn.jiangklijna.ella.common.f
import cn.jiangklijna.ella.common.println
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

    fun set(s: Bean.SubTitle, isEn: Boolean, isZh: Boolean, isTag: Boolean) {
        en.text = s.Sentence
        zh.text = s.Sentence_cn
        en.visibility = if (isEn) View.VISIBLE else View.GONE
        zh.visibility = if (isZh) View.VISIBLE else View.GONE

        //设置背景颜色和字体颜色
        if (isTag) {
            setBackgroundColor(Color.rgb(0x3F, 0x51, 0xB5))
            en.setTextColor(Color.WHITE)
            zh.setTextColor(Color.WHITE)
        } else {
            setBackgroundColor(Color.WHITE)
            en.setTextColor(Color.BLACK)
            zh.setTextColor(Color.BLACK)
        }

    }
}