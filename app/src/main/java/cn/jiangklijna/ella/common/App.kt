package cn.jiangklijna.ella.common

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco

/**
 * Created by jiangKlijna on 8/10/2017.
 */
class App : Application() {

	override fun onCreate() {
		super.onCreate()
		Fresco.initialize(this)
	}

	companion object {
		const val TAG = "ella"
		const val NAME = "英语听力学习"
	}
}