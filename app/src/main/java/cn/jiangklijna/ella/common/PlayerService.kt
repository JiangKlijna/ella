package cn.jiangklijna.ella.common

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import cn.jiangklijna.ella.entry.EnglishArticle
import tv.danmaku.ijk.media.player.IjkMediaPlayer

/**
 * Created by leil7 on 2017/10/21. ella
 */
class PlayerService : Service() {

    var a: EnglishArticle? = null
    var player: IjkMediaPlayer? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.let {
            when (it.getSerializableExtra(Command::class.java.simpleName) as Command?) {
                Command.Play -> {
                    play(it.getSerializableExtra(EnglishArticle::class.java.simpleName) as EnglishArticle)
                }
                null -> stopSelf()
            }
        }
        return START_STICKY
    }

    private fun play(a: EnglishArticle) {

    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? = null

    private enum class Command {
        Play
    }

    companion object {
        fun play(context: Context, a: EnglishArticle) {
            context.startService(Intent(context, PlayerService::class.java).apply {
                putExtra(Command::class.java.simpleName, Command.Play)
                putExtra(EnglishArticle::class.java.simpleName, a)
            })
        }
    }
}