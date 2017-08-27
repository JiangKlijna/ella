package cn.jiangklijna.ella.model

import cn.jiangklijna.ella.ui.fragment.FrgEcHtml
import cn.jiangklijna.ella.ui.fragment.FrgEcJson
import cn.jiangklijna.ella.ui.fragment.FrgEnglishCards

/**
 * Created by jiangKlijna on 8/13/2017.
 */
object Setting {

	private val frgs = arrayOf(
			Type(0,
					"BBC六分钟",
					"http://m.iyuba.com/bbcwap/index.jsp?parentID=1",
					"http://m.iyuba.com/bbcwap/play.jsp?id=",
					FrgEcHtml::class.java),
			Type(1,
					"BBC地道英语",
					"http://m.iyuba.com/bbcwap/index.jsp?parentID=5",
					"http://m.iyuba.com/bbcwap/play.jsp?id=",
					FrgEcHtml::class.java),
			Type(2,
					"BBC新闻",
					"http://m.iyuba.com/bbcwap/index.jsp?parentID=3",
					"http://m.iyuba.com/bbcwap/play.jsp?id=",
					FrgEcHtml::class.java),
			Type(3,
					"VOA慢速",
					"http://m.iyuba.com/voaS/index.jsp",
					"http://m.iyuba.com/voaS/play.jsp?id=",
					FrgEcHtml::class.java),
			Type(4,
					"VOA一分钟",
					"http://m.iyuba.com/voaS/indexAM.jsp",
					"http://m.iyuba.com/voaS/play.jsp?id=",
					FrgEcHtml::class.java),
			Type(5,
					"VOA视频",
					"http://m.iyuba.com/voaS/indexCV.jsp",
					"http://m.iyuba.com/voaS/play.jsp?id=",
					FrgEcHtml::class.java),
			Type(6,
					"TED演讲",
					"http://apps.iyuba.com/voa/titleTed.jsp?maxid=0&type=mobileweb&pageNum=6&format=json&pages=1",
					"http://m.iyuba.com/ted/play.jsp?id=",
					FrgEcJson::class.java)
	)

	data class Type(
			val id: Int,
			val title: String,
			val url: String,
			val playurl: String,
			val frgclass: Class<out FrgEnglishCards>
	)

	fun getFragments(): List<FrgEnglishCards> {
		return List(frgs.size, {
			val data = frgs[it]
			data.frgclass.newInstance().apply { ds = data }
		})
	}
}