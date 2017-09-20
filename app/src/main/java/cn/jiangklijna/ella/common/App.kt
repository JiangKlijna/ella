package cn.jiangklijna.ella.common

import android.app.Application
import cn.jiangklijna.ella.entry.DaoMaster
import cn.jiangklijna.ella.entry.DaoSession
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.imagepipeline.backends.okhttp3.OkHttpImagePipelineConfigFactory
import okhttp3.OkHttpClient

/**
 * Created by jiangKlijna on 8/10/2017.
 */
class App : Application() {

	override fun onCreate() {
		super.onCreate()

		// init Fresco
		val config = OkHttpImagePipelineConfigFactory.newBuilder(this, OkHttpClient()).build()
		Fresco.initialize(this, config)

		// init greeendao
		val mHelper = DaoMaster.DevOpenHelper(this, "ella", null)
		val mDaoMaster = DaoMaster(mHelper.writableDatabase)
		mDaoSession = mDaoMaster.newSession()
	}

	companion object {
		const val TAG = "ella"
		const val NAME = "英语听力学习"
		const val AUTHOR = "jiangKlijna"
		const val AUTHOR_URL = "https://github.com/$AUTHOR"
		const val PROJECT_URL = "$AUTHOR_URL/$TAG"
		var mDaoSession: DaoSession? = null
	}
}