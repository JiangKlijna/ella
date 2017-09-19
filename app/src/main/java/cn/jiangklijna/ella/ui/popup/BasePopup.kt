package cn.jiangklijna.ella.ui.popup

import android.app.Activity
import android.support.annotation.IdRes
import android.support.annotation.LayoutRes
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.PopupWindow

/**
 * Created by jiangKlijna on 7/20/2017.
 * 所有Popup_类的父类
 */
open class BasePopup {

	protected val layout: View
	protected val pw: PopupWindow
	protected val context: Activity

	constructor(context: Activity,
				@LayoutRes id: Int,
				width: Int = WindowManager.LayoutParams.MATCH_PARENT,
				height: Int = WindowManager.LayoutParams.WRAP_CONTENT) {
		this.context = context
		layout = LayoutInflater.from(context).inflate(id, null, false)
		pw = PopupWindow(layout, width, height)
	}

	protected fun <T : View> f(@IdRes viewId: Int): T = layout.findViewById(viewId) as T

	fun isShowing() = pw.isShowing

	protected open fun show(gravity: Int = Gravity.CENTER) {
		val root = context.window.decorView.findViewById(android.R.id.content)
		pw.showAtLocation(root, gravity, 0, 0)
	}

	open fun dismiss() = pw.dismiss()
}