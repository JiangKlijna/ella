package cn.jiangklijna.ella.common

import android.app.Activity
import android.content.Context
import android.support.annotation.IdRes
import android.util.DisplayMetrics
import android.view.View
import android.widget.Toast
import com.facebook.drawee.drawable.ScalingUtils
import com.facebook.drawee.view.DraweeTransition

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

fun <T : View> View.f(@IdRes id: Int): T = findViewById(id) as T

//设置SharedElement
fun Activity.setSharedElement() {
	window.sharedElementEnterTransition = DraweeTransition.createTransitionSet(ScalingUtils.ScaleType.CENTER_CROP, ScalingUtils.ScaleType.FIT_CENTER)
	window.sharedElementReturnTransition = DraweeTransition.createTransitionSet(ScalingUtils.ScaleType.FIT_CENTER, ScalingUtils.ScaleType.CENTER_CROP)
}