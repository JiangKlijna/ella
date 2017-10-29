package cn.jiangklijna.ella.common

import android.app.Notification
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Handler
import android.os.IBinder
import android.os.SystemClock
import android.support.v4.app.NotificationCompat
import cn.jiangklijna.ella.R
import cn.jiangklijna.ella.entry.EnglishArticle
import org.greenrobot.eventbus.EventBus
import tv.danmaku.ijk.media.player.IjkMediaPlayer


/**
 * Created by leil7 on 2017/10/21. ella
 */
class PlayerService : Service() {

    var a: EnglishArticle? = null
    var player: IjkMediaPlayer? = null
    var nf: NotificationCompat.Builder? = null

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
        player?.run {
            if (isPlaying) stop()
        }
        App.pool?.shutdown()
        stopSelf()
    }

    private fun seek(long: Long) {
        player?.run {
            if (isPlaying) seekTo(long)
        }
    }

    private fun play(a: EnglishArticle) {
        if (this.a == a) return
        this.a = a
        if (player != null) {
            player!!.stop()
        }
        player = IjkMediaPlayer().apply {
            dataSource = a.sound
            prepareAsync()
            start()
        }
        App.pool?.submit {
            while (true) {
                println("duration = ${player!!.duration}, currentPosition = ${player!!.currentPosition}")
                Thread.sleep(1000)
            }
        }
        initNotification(a)
        notify(nf!!)
    }

    private fun initNotification(a: EnglishArticle) {
        if (nf == null) {
            nf = NotificationCompat.Builder(this)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setLargeIcon(BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher))
//                  .setContentIntent(PendingIntent.getActivity(this, 0, Intent(), 0))
                    .setContentTitle(a.title)
                    .setTicker(a.title)
                    .setAutoCancel(true)
                    .setVisibility(Notification.VISIBILITY_PUBLIC)
        }
        nf!!.setProgress(100, 0, false)
    }

    private fun notify(nf: NotificationCompat.Builder) {
        val nm = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        nm.notify(0, nf.build())
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

        fun play(context: Context, a: EnglishArticle) {
            context.startService(Intent(context, PlayerService::class.java).apply {
                putExtra(Command::class.java.simpleName, Command.Play)
                putExtra(EnglishArticle::class.java.simpleName, a)
            })
        }

        fun seek(context: Context, long: Long) {
            context.startService(Intent(context, PlayerService::class.java).apply {
                putExtra(Command::class.java.simpleName, Command.Seek)
                putExtra(Long::class.java.simpleName, long)
            })
        }

        fun stop(context: Context) {
            context.startService(Intent(context, PlayerService::class.java).apply {
                putExtra(Command::class.java.simpleName, Command.Stop)
            })
        }
    }
}