package cn.jiangklijna.ella.ui.dialog

import android.app.Activity
import android.support.v7.app.AlertDialog
import cn.jiangklijna.ella.R
import cn.jiangklijna.ella.common.App

/**
 * Created by leil7 on 2017/10/15. ella
 */
class DialogSubTitleSetting(context: Activity) : BaseDialog(context,
        AlertDialog.Builder(context)
                .setIcon(R.mipmap.ic_launcher)
                .setTitle(App.SETTING)
                .setView(R.layout.dialog_subtitle_setting)) {

}
