package cn.jiangklijna.ella.common

import android.app.Service
import android.content.Intent
import android.os.IBinder

/**
 * Created by leil7 on 2017/10/21. ella
 */
class PlayerService :Service() {

    override fun onCreate() {
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? =null
}