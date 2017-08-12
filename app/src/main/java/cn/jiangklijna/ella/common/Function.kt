package cn.jiangklijna.ella.common

import android.app.Activity
import android.content.Context
import android.util.DisplayMetrics
import android.widget.Toast

/**
 * Created by jiangKlijna on 8/12/2017.
 */

fun <T> T?.println() {
	android.util.Log.i("App", this?.toString())
}

fun Context.toast(msg: String) {
	Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

//获得activity的宽高
fun Activity.getPixel(): DisplayMetrics = DisplayMetrics().apply {
	windowManager.defaultDisplay.getMetrics(this)
}