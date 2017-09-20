package cn.jiangklijna.ella.common

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.graphics.drawable.RippleDrawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RoundRectShape
import android.net.Uri
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

// 根据手机的分辨率从 dp 的单位 转成为 px(像素)
fun Context.dp2px(dpValue: Float): Int =
		resources.displayMetrics.density.let {
			(dpValue * it + 0.5f).toInt()
		}


// 代码设置RippleDrawable
fun getAdaptiveRippleDrawable(normalColor: Int, pressedColor: Int): Drawable {
	return RippleDrawable(ColorStateList.valueOf(pressedColor), null, getRippleMask(normalColor))
}

private fun getRippleMask(color: Int): Drawable {
	// 3 is radius of final ripple,
	// instead of 3 you can give required final radius
	val outerRadii = FloatArray(8, { 3f })
	val r = RoundRectShape(outerRadii, null, null)
	val shapeDrawable = ShapeDrawable(r)
	shapeDrawable.paint.color = color
	return shapeDrawable
}

// 获得版本名
fun Context.getVersionName(): String = packageManager.getPackageInfo(packageName, PackageManager.GET_CONFIGURATIONS).versionName

// 获得版本号
fun Context.getVersionCode(): Int = packageManager.getPackageInfo(packageName, PackageManager.GET_CONFIGURATIONS).versionCode

fun Context.openBrowser(url: String) = Intent().run {
	action = "android.intent.action.VIEW"
	data = Uri.parse(url)
	startActivity(this)
}