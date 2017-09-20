package cn.jiangklijna.ella.ui.dialog

import android.app.Activity
import android.support.v7.app.AlertDialog
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.TextView
import cn.jiangklijna.ella.R
import cn.jiangklijna.ella.common.App
import cn.jiangklijna.ella.common.XAdapter
import cn.jiangklijna.ella.common.getVersionName
import cn.jiangklijna.ella.common.openBrowser

/**
 * Created by jiangKlijna on 9/20/2017.
 */
class DialogAppAbout(context: Activity) : BaseDialog(context,
		AlertDialog.Builder(context)
				.setIcon(R.mipmap.ic_launcher)
				.setTitle(App.TAG)
				.setView(R.layout.dialog_listview)) {

	val items = arrayOf(
			Item("版本", context.getVersionName(), null),
			Item("作者", App.AUTHOR, { context.openBrowser(App.AUTHOR_URL) }),
			Item("源码", "github", { context.openBrowser(App.PROJECT_URL) })
	)

	val adapter = object : XAdapter<Item>(items, context) {
		override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
			val hoder = getHolder(context, convertView, parent, R.layout.item_materia_horizontal)
			hoder.getView<TextView>(R.id.item_key).text = getItem(position).key
			hoder.getView<TextView>(R.id.item_value).text = getItem(position).value
			return hoder.getConvertView()
		}
	}

	override fun show() {
		super.show()
		val lv = f<ListView>(R.id.dialog_listview)
		lv.adapter = adapter
		lv.setOnItemClickListener { _, _, position, _ -> items[position].run?.invoke() }
	}

	data class Item(val key: String, val value: String, val run: (() -> Unit)?)
}