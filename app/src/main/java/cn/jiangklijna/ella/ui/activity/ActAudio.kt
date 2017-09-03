package cn.jiangklijna.ella.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import cn.jiangklijna.ella.R
import cn.jiangklijna.ella.common.setSharedElement
import cn.jiangklijna.ella.entry.EnglishArticle
import kotlinx.android.synthetic.main.act_audio.*

/**
 * Created by leil7 on 2017/9/2. ella
 */
class ActAudio : AppCompatActivity() {

    var a: EnglishArticle? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSharedElement()
        setContentView(R.layout.act_audio)
        a = intent.getSerializableExtra(EnglishArticle::class.java.simpleName) as EnglishArticle
        act_audio_img.setImageURI(a!!.img)

    }

}
