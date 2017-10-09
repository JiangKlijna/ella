package cn.jiangklijna.ella.ui.view

import android.content.Context
import android.view.LayoutInflater
import android.widget.LinearLayout
import cn.jiangklijna.ella.R

/**
 * Created by leil7 on 2017/10/2. ella
 */
class PlayerView(context: Context) : LinearLayout(context, null, 0) {


    init {
        LayoutInflater.from(context).inflate(R.layout.view_player, this, true)
    }

}