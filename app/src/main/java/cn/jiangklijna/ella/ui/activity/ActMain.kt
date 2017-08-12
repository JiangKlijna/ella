package cn.jiangklijna.ella.ui.activity

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import cn.jiangklijna.ella.R
import cn.jiangklijna.ella.common.App
import cn.jiangklijna.ella.common.toast
import kotlinx.android.synthetic.main.act_main.*

/**
 * Created by jiangKlijna on 8/10/2017.
 */
class ActMain : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.act_main)
		supportActionBar?.title = App.NAME
		act_main_tab.tabMode = TabLayout.MODE_SCROLLABLE

		act_main_tab.addTab(act_main_tab.newTab().setText("Tab 1"))
		act_main_tab.addTab(act_main_tab.newTab().setText("Tab 2"))
		act_main_tab.addTab(act_main_tab.newTab().setText("Tab 3"))
	}


	override fun onCreateOptionsMenu(menu: Menu?): Boolean {
		menuInflater.inflate(R.menu.action, menu)
		return super.onCreateOptionsMenu(menu)
	}

	// menu item 的点击事件
	override fun onOptionsItemSelected(item: MenuItem): Boolean {
		when (item.itemId) {
			R.id.menu_search -> { // 搜索英文单词
				toast("搜索英文单词")
			}
			R.id.menu_setting -> { // 应用的设置
				toast("应用的设置")
			}
			R.id.menu_about -> { // 应用的关于
				toast("应用的关于")
			}
		}
		return super.onOptionsItemSelected(item)
	}

}