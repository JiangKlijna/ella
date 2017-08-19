package cn.jiangklijna.ella.model

import okhttp3.*
import okio.BufferedSink
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * Created by jiangKlijna on 7/27/2017.
 * 网络请求
 */
object Http {
	private val client = OkHttpClient()
	private val bus = EventBus.getDefault()

	fun get(url: String, cb: Callback) = client.newCall(Request.Builder().url(url).get().build()).enqueue(cb)

	fun post(url: String, cb: Callback) = client.newCall(Request.Builder().url(url).post(EmptyBody()).build()).enqueue(cb)

	// 回调
	interface Runnable<T> {
		fun run(data: T)
	}

	// 通过EventBus 将网络请求的回调运行在ui线程
	class Event<T>(runnable: Runnable<T>, t: T) {
		val run: Runnable<T> = runnable
		val data: T = t

		fun send() = bus.post(this)

		@Subscribe(threadMode = ThreadMode.MAIN)
		fun onEvent(o: Any) {
			run.run(data)
			bus.unregister(this)
		}

		init {
			bus.register(this)
		}
	}

	// 空的RequestBody
	class EmptyBody : RequestBody() {
		override fun contentType(): MediaType? = type

		override fun contentLength(): Long = 0

		override fun writeTo(sink: BufferedSink) {}

		companion object {
			val type = MediaType.parse("application/x-www-form-urlencoded; charset=UTF-8")
		}
	}

}