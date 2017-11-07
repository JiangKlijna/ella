package cn.jiangklijna.ella.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import cn.jiangklijna.ella.R
import cn.jiangklijna.ella.common.*
import cn.jiangklijna.ella.entry.EnglishArticle
import cn.jiangklijna.ella.model.Bean
import cn.jiangklijna.ella.model.Http
import cn.jiangklijna.ella.model.Requests
import cn.jiangklijna.ella.model.Setting
import cn.jiangklijna.ella.ui.dialog.DialogSubTitleSetting
import cn.jiangklijna.ella.ui.dialog.DialogWord
import cn.jiangklijna.ella.ui.view.SubTitleView
import kotlinx.android.synthetic.main.act_player.*
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * Created by leil7 on 2017/9/2. ella
 */
class ActPlayer : AppCompatActivity() {

    var a: EnglishArticle? = null
    var t: Setting.Type? = null
    var tagSubTitle: Bean.SubTitle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSharedElement()
        setTheme(R.style.AppTheme_NoActionBar)
        setContentView(R.layout.act_player)
        setSupportActionBar(act_player_toolbar)
        a = intent.getSerializableExtra(EnglishArticle::class.java.simpleName) as EnglishArticle
        t = intent.getSerializableExtra(Setting.Type::class.java.simpleName) as Setting.Type
        act_player_img.setImageURI(a!!.img)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        act_player_toolbar_layout.isTitleEnabled = false
        supportActionBar?.title = a?.title
        Requests.listOfSubTitle(a!!, run)
        act_player_listview.adapter = object : XAdapter<Bean.SubTitle>(this@ActPlayer) {
            override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View =
                    getHolder(context, convertView, SubTitleView::class.java).getConvertView<SubTitleView>().apply {
                        val st = getItem(position)
                        set(st, isEn, isZh, st === tagSubTitle)
                    }
        }
        act_player_listview.setOnItemClickListener { view, _, pos, _ ->
            val st = view.adapter.getItem(pos) as Bean.SubTitle
            PlayerService.seek(this@ActPlayer, (st.Timing * 1000).toLong())
        }
        val isFollow = App.mDaoSession?.englishArticleDao?.load(a?.id) != null
        if (isFollow) act_player_fab.setImageResource(R.mipmap.img_heart_on)
        act_player_fab.tag = isFollow
        act_player_fab.setOnClickListener {
            if (act_player_fab.tag as Boolean) {
                App.mDaoSession?.englishArticleDao?.deleteByKey(a?.id)
                act_player_fab.tag = false
                act_player_fab.setImageResource(R.mipmap.img_heart_off)
            } else {
                App.mDaoSession?.englishArticleDao?.insert(a)
                act_player_fab.tag = true
                act_player_fab.setImageResource(R.mipmap.img_heart_on)
            }
        }
        PlayerService.progressBus.register(this)
        PlayerService.play(this, a!!)
    }

    val run = object : Http.Runnable<List<Bean.SubTitle>> {
        override fun run(data: List<Bean.SubTitle>) {
            (act_player_listview.adapter as XAdapter<Bean.SubTitle>).setData(data)
        }
    }

    var isZh: Boolean = true
    var isEn: Boolean = true

    val onChangeListener = object : DialogSubTitleSetting.OnChangeListener {
        override fun onChange(isZh: Boolean, isEn: Boolean) {
            this@ActPlayer.isEn = isEn
            this@ActPlayer.isZh = isZh
            act_player_listview.visibility = if (isZh || isEn) View.VISIBLE else View.GONE
            (act_player_listview.adapter as XAdapter<Bean.SubTitle>).notifyDataSetChanged()
//          toast("{isZH:$isZh, isEn:$isEn}")
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEvent(o: Any) {
        val timing = o as Float
//        timing.println()
        val a = act_player_listview.adapter as XAdapter<Bean.SubTitle>
        for (st in a.collection) {
            if (st.EndTiming >= timing) {
                tagSubTitle = st
                a.notifyDataSetChanged()
                return
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        PlayerService.progressBus.unregister(this)
        PlayerService.stop(this)
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
                DialogSubTitleSetting(this, onChangeListener).show(isZh, isEn)
            }
            else -> { // 返回
                onBackPressed()
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
