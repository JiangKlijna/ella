package cn.jiangklijna.ella.common

import android.app.Application

/**
 * Created by jiangKlijna on 8/10/2017.
 */
class App : Application() {

	override fun onCreate() {
		super.onCreate()

	}

	companion object {
		const val NAME = "英语听力学习"
	}
}