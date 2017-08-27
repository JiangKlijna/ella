package cn.jiangklijna.ella.model

import cn.jiangklijna.ella.entry.EnglishArticle
import org.jsoup.Jsoup
import java.io.InputStream

/**
 * Created by jiangKlijna on 8/13/2017.
 */
object Bean {

	fun EnglishCardsWithHtml(i: InputStream, t: Setting.Type): List<EnglishArticle> {
		try {
			val divs = Jsoup.parse(i, "utf-8", "").getElementsByAttributeValue("class", "c")
			return List(divs.size, {
				val div = divs[it]
				val title = div.getElementsByTag("p")[0].text()
				val img = div.getElementsByTag("img")[0].attr("src")
				val id = img.substring(img.lastIndexOf('/') + 1).split(".")[0]
				val url = t.playurl + id
				val date = div.getElementsByTag("small")[0].text().split(" ")[0]
				EnglishArticle(id.toInt(), title, url, img, date, t.id)
			})
		} catch (e: Exception) {
			return emptyList()
		}
	}

}