package cn.jiangklijna.ella.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import cn.jiangklijna.ella.R
import cn.jiangklijna.ella.entry.EnglishArticle
import cn.jiangklijna.ella.model.Setting
import kotlinx.android.synthetic.main.view_player.view.*
import tv.danmaku.ijk.media.player.IjkMediaPlayer

/**
 * Created by leil7 on 2017/10/2. ella
 */
class PlayerView(context: Context?, attrs: AttributeSet?) : LinearLayout(context, attrs, 0) {

    private val player = IjkMediaPlayer()

    init {
        LayoutInflater.from(context).inflate(R.layout.view_player, this, true)
    }

    fun play(a: EnglishArticle, t: Setting.Type) {
        if (t.isAudio) {
            view_player_surface.visibility = View.GONE
        } else {
            player.setSurface(view_player_surface.holder.surface)
        }
        player.dataSource = a.sound
    }

}