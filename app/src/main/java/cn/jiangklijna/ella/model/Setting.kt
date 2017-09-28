package cn.jiangklijna.ella.model

import java.io.Serializable

/**
 * Created by jiangKlijna on 8/13/2017.
 */
object Setting {
    const val LIST_URL = "http://cms.iyuba.com/dataapi/jsp/getTitle.jsp"
    const val INFO_URL = "http://cms.iyuba.com/dataapi/jsp/getText.jsp"

    val ts = arrayOf(
            Type(0, "慢速", "voa", true),
            Type(1, "常速", "csvoa", true),
            Type(2, "BBC", "bbc", true),
            Type(3, "听歌", "song", true),
            Type(4, "美语", "meiyu", false),
            Type(5, "TED", "ted", false),
            Type(6, "VOA视频", "voavideo", false),
            Type(7, "头条视频", "topvideos", false),
            Type(8, "BBC视频", "bbcwordvideo", false)
    )

    data class Type(
            val id: Int,
            val title: String,
            val type: String,
            val isAudio: Boolean
    ) : Serializable

}