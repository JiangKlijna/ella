package cn.jiangklijna.ella

import android.content.Context
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import cn.jiangklijna.ella.model.Http
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import java.io.IOException

/**
 * Instrumentation test, which will execute on an Android device.

 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
	@Test
	@Throws(Exception::class)
	fun useAppContext() {
		// Context of the app under test.
		val appContext = InstrumentationRegistry.getTargetContext()
		assertEquals("cn.jiangklijna.ella", appContext.packageName)
	}

	@Test
	fun useHttp() = Http.get("http://ie.icoa.cn", object : Callback {
		override fun onFailure(call: Call?, e: IOException?) = fail(e?.message)
		override fun onResponse(call: Call?, response: Response) = assertTrue(response.isSuccessful)
	})

}
