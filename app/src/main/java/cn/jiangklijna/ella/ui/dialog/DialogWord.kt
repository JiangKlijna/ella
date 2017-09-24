package cn.jiangklijna.ella.ui.dialog

import android.app.Activity
import android.support.v7.app.AlertDialog
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.TextView
import cn.jiangklijna.ella.R
import cn.jiangklijna.ella.entry.Word
import cn.jiangklijna.ella.model.Http
import cn.jiangklijna.ella.model.Requests

/**
 * Created by jiangKlijna on 9/20/2017.
 */
class DialogWord(context: Activity) : BaseDialog(context,
        AlertDialog.Builder(context)
                .setView(R.layout.dialog_word)) {

    override fun show() {
        super.show()
        val area = f<View>(R.id.dialog_word_area)
        val us = f<TextView>(R.id.dialog_word_us)
        val uk = f<TextView>(R.id.dialog_word_uk)
        val explain = f<TextView>(R.id.dialog_word_explain)
        val run = object : Http.Runnable<Word?> {
            override fun run(data: Word?) {
                if (data == null) {
                    area.visibility = View.GONE
                } else {
                    area.visibility = View.VISIBLE
                    us.text = US_PREFIX + data.us
                    uk.text = UK_PREFIX + data.uk
                    explain.text = data.zh
                }
            }
        }
        f<EditText>(R.id.dialog_word).addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun afterTextChanged(s: Editable) = Requests.translate(s.toString(), run)
        })
    }

    companion object {
        const val US_PREFIX = "\tUS:\t"
        const val UK_PREFIX = "\tUK:\t"
    }
}