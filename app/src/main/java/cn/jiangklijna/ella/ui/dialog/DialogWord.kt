package cn.jiangklijna.ella.ui.dialog

import android.app.Activity
import android.support.v7.app.AlertDialog
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.TextView
import cn.jiangklijna.ella.R
import cn.jiangklijna.ella.common.App
import cn.jiangklijna.ella.entry.Word
import cn.jiangklijna.ella.model.Http
import cn.jiangklijna.ella.model.Requests
import tv.danmaku.ijk.media.player.IjkMediaPlayer
import cn.jiangklijna.ella.model.Bean.getUsPhoneticUrl
import cn.jiangklijna.ella.model.Bean.getUkPhoneticUrl

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
                    us.tag = null
                    uk.tag = null
                } else {
                    area.visibility = View.VISIBLE
                    us.text = US_PREFIX + data.us
                    uk.text = UK_PREFIX + data.uk
                    explain.text = data.zh
                    us.tag = data
                    uk.tag = data
                    wordDao.save(data)
                }
            }
        }
        f<EditText>(R.id.dialog_word).addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun afterTextChanged(s: Editable) {
                val en = s.toString()
                if (en.isEmpty()) return run.run(null)
                val word = wordDao.load(en)
                if (word == null) {
                    Requests.translate(en, run)
                } else {
                    run.run(word)
                }
            }
        })
        us.setOnClickListener {
            if (it.tag == null) return@setOnClickListener
            val word = it.tag as Word
            play(word.getUsPhoneticUrl())
        }
        uk.setOnClickListener {
            if (it.tag == null) return@setOnClickListener
            val word = it.tag as Word
            play(word.getUkPhoneticUrl())
        }
    }

    private fun play(url: String) = player.run {
        if (isPlaying) return
        reset()
        dataSource = url
        prepareAsync()
        start()
    }

    override fun cancel() {
        if (player.isPlaying) player.stop()
        super.cancel()
    }

    companion object {
        const val US_PREFIX = "\tUS:\t"
        const val UK_PREFIX = "\tUK:\t"

        private val player = IjkMediaPlayer()
        private val wordDao = App.mDaoSession!!.wordDao
    }
}