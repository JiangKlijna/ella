package cn.jiangklijna.ella.model

import cn.jiangklijna.ella.ui.fragment.FrgEcHtml
import cn.jiangklijna.ella.ui.fragment.FrgEcJson
import cn.jiangklijna.ella.ui.fragment.FrgEnglishCards

/**
 * Created by jiangKlijna on 8/13/2017.
 */
object Setting {

	private val frgs = arrayOf(
			Data(0, "BBC六分钟", "http://m.iyuba.com/bbcwap/index.jsp?parentID=1", FrgEcHtml::class.java),
			Data(1, "BBC地道英语", "http://m.iyuba.com/bbcwap/index.jsp?parentID=5", FrgEcHtml::class.java),
			Data(2, "BBC新闻", "http://m.iyuba.com/bbcwap/index.jsp?parentID=3", FrgEcHtml::class.java),
			Data(3, "VOA慢速", "http://m.iyuba.com/voaS/index.jsp", FrgEcHtml::class.java),
			Data(4, "VOA一分钟", "http://m.iyuba.com/voaS/indexAM.jsp", FrgEcHtml::class.java),
			Data(5, "VOA视频", "http://m.iyuba.com/voaS/indexCV.jsp", FrgEcHtml::class.java),
			Data(6, "TED演讲", "http://apps.iyuba.com/voa/titleTed.jsp?maxid=0&type=mobileweb&pageNum=6&format=json&pages=1", FrgEcJson::class.java)
	)

	data class Data(
			val id: Int,
			val title: String,
			val url: String,
			val frgclass: Class<out FrgEnglishCards>
	)


	fun getFragments(): List<FrgEnglishCards> {
		return List(frgs.size, {
			val data = frgs[it]
			data.frgclass.newInstance().apply { ds = data }
		})
	}
}