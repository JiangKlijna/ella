package cn.jiangklijna.ella.ui.dialog

import android.app.Activity
import android.support.v7.app.AlertDialog
import android.widget.CheckBox
import android.widget.CompoundButton
import cn.jiangklijna.ella.R
import cn.jiangklijna.ella.common.App

/**
 * Created by leil7 on 2017/10/15. ella
 */
class DialogSubTitleSetting(context: Activity, val listener: OnChangeListener) :
        BaseDialog(context, AlertDialog.Builder(context)
                .setIcon(R.mipmap.ic_launcher)
                .setTitle(App.SETTING)
                .setView(R.layout.dialog_subtitle_setting)), CompoundButton.OnCheckedChangeListener {

    override fun onCheckedChanged(buttonView: CompoundButton, isChecked: Boolean) {
        when (buttonView.id) {
            R.id.dialog_subtitle_setting_en ->
                listener.onChange(f<CheckBox>(R.id.dialog_subtitle_setting_zh).isChecked, isChecked)
            R.id.dialog_subtitle_setting_zh ->
                listener.onChange(isChecked, f<CheckBox>(R.id.dialog_subtitle_setting_en).isChecked)
        }
    }

    override fun show() {
        super.show()
        f<CheckBox>(R.id.dialog_subtitle_setting_en).setOnCheckedChangeListener(this)
        f<CheckBox>(R.id.dialog_subtitle_setting_zh).setOnCheckedChangeListener(this)
    }

    interface OnChangeListener {
        fun onChange(isZh: Boolean, isEn: Boolean)
    }
}
