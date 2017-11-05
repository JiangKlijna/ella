package cn.jiangklijna.ella.common

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import cn.jiangklijna.ella.entry.EnglishArticle
import org.greenrobot.eventbus.EventBus
import tv.danmaku.ijk.media.player.IMediaPlayer.MEDIA_INFO_AUDIO_RENDERING_START
import tv.danmaku.ijk.media.player.IMediaPlayer.MEDIA_INFO_BUFFERING_END
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
                Command.Seek -> {
                    seek(it.getSerializableExtra(Long::class.java.simpleName) as Long)
                }
                Command.Stop -> {
                    stop()
                }
                null -> stop()
            }
        }
        return START_STICKY
    }

    private fun stop() {
        status = MEDIA_INFO_BUFFERING_END
        player?.run {
            if (isPlaying) stop()
        }
        stopSelf()
    }

    private fun seek(long: Long) {
        player?.run {
            if (isPlaying) seekTo(long)
        }
    }

    private var status: Int = 0

    private fun play(a: EnglishArticle) {
        if (this.a == a) return
        this.a = a
        if (player != null) {
            player!!.stop()
        }
        player = IjkMediaPlayer().apply {
            dataSource = a.sound
            setOnInfoListener { _, i, j ->
                true.apply {
                    if (i == MEDIA_INFO_AUDIO_RENDERING_START) { // audio start
                        App.pool?.submit(run)
                    } else if (i == MEDIA_INFO_BUFFERING_END) { // audio end
                        status = MEDIA_INFO_BUFFERING_END
                    } else {
                        println("setOnInfoListener $i $j")
                    }
                }
            }
            prepareAsync()
            start()
        }
    }

    private val run: Runnable = Runnable {
        while (status != MEDIA_INFO_BUFFERING_END) {
            player?.apply {
                progressBus.post((currentPosition / 1000).toInt())
//                println("setOnTime ${currentPosition / 1000}")
            }
            Thread.sleep(1000)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? = null

    private enum class Command {
        Play, Stop, Seek
    }

    companion object {

        val progressBus = EventBus()

        val play = fun(context: Context, a: EnglishArticle) {
            context.startService(Intent(context, PlayerService::class.java).apply {
                putExtra(Command::class.java.simpleName, Command.Play)
                putExtra(EnglishArticle::class.java.simpleName, a)
            })
        }

        val seek = fun(context: Context, long: Long) {
            context.startService(Intent(context, PlayerService::class.java).apply {
                putExtra(Command::class.java.simpleName, Command.Seek)
                putExtra(Long::class.java.simpleName, long)
            })
        }

        val stop = fun(context: Context) {
            context.startService(Intent(context, PlayerService::class.java).apply {
                putExtra(Command::class.java.simpleName, Command.Stop)
            })
        }
    }
}