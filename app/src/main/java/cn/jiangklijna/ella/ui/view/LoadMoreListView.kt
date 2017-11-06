package cn.jiangklijna.ella.ui.view

import android.content.Context
import android.graphics.Color
import android.support.annotation.ColorRes
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.ListView
import android.widget.TextView
import cn.jiangklijna.ella.common.dp2px
import cn.jiangklijna.ella.common.getAdaptiveRippleDrawable
import com.github.ybq.android.spinkit.SpinKitView
import com.github.ybq.android.spinkit.SpriteFactory
import com.github.ybq.android.spinkit.Style
import com.github.ybq.android.spinkit.sprite.Sprite
import java.util.*

/**
 * Created by leil7 on 2017/9/16. ella
 */
class LoadMoreListView(context: Context?, attrs: AttributeSet?) : ListView(context, attrs, 0), View.OnClickListener {

    private var mColor: Int = 0

    val textView = TextView(context).apply {
        text = "Load More"
        textSize = 18f
        val v = context!!.dp2px(10f)
        setPadding(v, v, v, v)
    }

    val spinKitView = SpinKitView(context).apply {
        visibility = View.GONE
    }

    val footerLayout = FrameLayout(context).apply {
        addView(textView, lp)
        addView(spinKitView, lp)
        setOnClickListener(this@LoadMoreListView)
        background = getAdaptiveRippleDrawable(Color.WHITE, Color.GRAY)
        this@LoadMoreListView.addFooterView(this)
    }

    var onLoadMoreListener: OnLoadMoreListener? = null
    var isRefreshing: Boolean = false
        set(value) {
//            if (value != field) {
            if (value) {
                val s = randomSprite().apply { color = mColor }
                spinKitView.setIndeterminateDrawable(s)
                spinKitView.visibility = View.VISIBLE
                onLoadMoreListener?.onLoadMore()
            } else {
                spinKitView.visibility = View.GONE
            }
            field = value
//            }
        }

    // footerLayout onClick
    override fun onClick(v: View?) {
        if (spinKitView.visibility == View.VISIBLE) return
        isRefreshing = true
    }

    fun setColorSchemeResource(@ColorRes colorResId: Int) =
            resources.getColor(colorResId).let {
                mColor = it
                spinKitView.setColor(it)
            }

    companion object {
        private val r = Random()
        private val styles = Style::class.java.enumConstants
        private fun randomSprite(): Sprite = SpriteFactory.create(styles[r.nextInt(styles.size)])

        private val lp = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT,
                Gravity.CENTER)
    }

    interface OnLoadMoreListener {
        fun onLoadMore()
    }

}
