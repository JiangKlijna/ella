package cn.jiangklijna.ella.model

import cn.jiangklijna.ella.ui.fragment.FrgEnglishCards

/**
 * Created by jiangKlijna on 8/13/2017.
 */
object Setting {

	private val frgs = arrayOf(
			Data(0, "VOA慢速", FrgEnglishCards::class.java),
			Data(1, "VOA一分钟", FrgEnglishCards::class.java),
			Data(2, "VOA视频", FrgEnglishCards::class.java),
			Data(3, "BBC六分钟", FrgEnglishCards::class.java),
			Data(4, "BBC地道英语", FrgEnglishCards::class.java),
			Data(5, "BBC新闻", FrgEnglishCards::class.java),
			Data(6, "TED演讲", FrgEnglishCards::class.java)
	)

	data class Data(
			val id: Int,
			val title: String,
			val frgclass: Class<out FrgEnglishCards>
	)


	fun getFragments():List<FrgEnglishCards> {
		return List(frgs.size, {
			frgs[it].frgclass.newInstance()
		})
	}
}