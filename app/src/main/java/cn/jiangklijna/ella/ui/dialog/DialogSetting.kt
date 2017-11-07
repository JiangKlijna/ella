package cn.jiangklijna.ella.ui.dialog

import android.app.Activity
import android.support.v7.app.AlertDialog
import cn.jiangklijna.ella.R
import cn.jiangklijna.ella.common.App

/**
 * Created by jiangKlijna on 9/20/2017.
 */
class DialogSetting(context: Activity) : BaseDialog(context,
        AlertDialog.Builder(context)
                .setIcon(R.mipmap.ic_launcher)
                .setTitle(App.TAG)
                .setMessage("没什么好设置的"))