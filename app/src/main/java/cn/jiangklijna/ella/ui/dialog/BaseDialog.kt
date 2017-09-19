package cn.jiangklijna.ella.ui.dialog

import android.app.Activity
import android.support.annotation.IdRes
import android.support.v7.app.AlertDialog
import android.view.View

/**
 * Created by jiangKlijna on 2017/1/4. ella
 * 所有Dialog_类的父类
 */
abstract class BaseDialog {

	protected val mContext: Activity
	protected val mDialog: AlertDialog

	constructor(mContext: Activity, adb: AlertDialog.Builder) {
		this.mContext = mContext
		mDialog = adb.create()
	}

	protected fun <T : View> f(@IdRes viewId: Int): T = mDialog.findViewById(viewId) as T

	fun isShowing() = mDialog.isShowing

	open fun show() = mDialog.show()

	open fun cancel() = mDialog.cancel()
}
