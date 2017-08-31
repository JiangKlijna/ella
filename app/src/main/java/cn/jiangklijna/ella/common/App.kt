package cn.jiangklijna.ella.common

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.imagepipeline.backends.okhttp3.OkHttpImagePipelineConfigFactory
import okhttp3.OkHttpClient

/**
 * Created by jiangKlijna on 8/10/2017.
 */
class App : Application() {

	override fun onCreate() {
		super.onCreate()

		val config = OkHttpImagePipelineConfigFactory.newBuilder(this, OkHttpClient()).build()
		Fresco.initialize(this, config)
	}

	companion object {
		const val TAG = "ella"
		const val NAME = "英语听力学习"
	}
}