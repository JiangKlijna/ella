package cn.jiangklijna.ella.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import cn.jiangklijna.ella.R
import cn.jiangklijna.ella.common.setSharedElement
import cn.jiangklijna.ella.common.toast
import cn.jiangklijna.ella.entry.EnglishArticle
import cn.jiangklijna.ella.model.Bean
import cn.jiangklijna.ella.model.Http
import cn.jiangklijna.ella.model.Requests
import cn.jiangklijna.ella.ui.dialog.DialogWord
import kotlinx.android.synthetic.main.act_player.*

/**
 * Created by leil7 on 2017/9/2. ella
 */
class ActPlayer : AppCompatActivity() {

    var a: EnglishArticle? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSharedElement()
        setTheme(R.style.AppTheme_NoActionBar)
        setContentView(R.layout.act_player)
        setSupportActionBar(act_player_toolbar)
        a = intent.getSerializableExtra(EnglishArticle::class.java.simpleName) as EnglishArticle
        act_player_img.setImageURI(a!!.img)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        act_player_toolbar_layout.isTitleEnabled = false
        supportActionBar?.title = a?.title
        
        Requests.listOfSubTitle(a!!, run)
    }

    val run = object : Http.Runnable<List<Bean.SubTitle>> {
        override fun run(data: List<Bean.SubTitle>) {

        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.player, menu)
        return super.onCreateOptionsMenu(menu)
    }

    // menu item 的点击事件
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_total -> {
            }
            R.id.menu_search -> {
                DialogWord(this).show()
            }
            R.id.menu_setting -> { // 页面的设置
                toast("页面的设置")
            }
            else -> { // 返回
                onBackPressed()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
