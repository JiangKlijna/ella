package cn.jiangklijna.ella.ui.dialog

import android.app.Activity
import android.support.v7.app.AlertDialog
import cn.jiangklijna.ella.R

/**
 * Created by jiangKlijna on 9/20/2017.
 */
class DialogWord(context: Activity) : BaseDialog(context,
        AlertDialog.Builder(context)
                .setView(R.layout.dialog_word)) {

    override fun show() {
        super.show()
    }

    companion object {
        const val US_PREFIX = "\tUS:\t"
        const val UK_PREFIX = "\tUK:\t"
    }
}