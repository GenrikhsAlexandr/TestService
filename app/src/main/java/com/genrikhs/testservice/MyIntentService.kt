package com.genrikhs.testservice

import android.app.IntentService
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class MyIntentService : IntentService(NAME) {

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
startForeground(NOTIFICATION_ID,createNotification())
        log("onCreate")
    }

    override fun onHandleIntent(p0: Intent?) {
        log("onHandleIntent")

        for (i in 0 until 100) {
            Thread.sleep(1000)
            log("Timer $i")
        }
    }


override fun onDestroy() {
    super.onDestroy()
    log("onDestroy")
}


private fun log(message: String) {
    Log.d("SERVICE_TAG", "MyIntentService: $message")
}

private fun createNotificationChannel() {
    val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
    val notificationChannel = NotificationChannel(
        CHANNEL_ID,
        CHANNEL_NAME,
        NotificationManager.IMPORTANCE_DEFAULT
    )
    notificationManager.createNotificationChannel(notificationChannel)
}

private fun createNotification() = NotificationCompat.Builder(this, CHANNEL_ID)
    .setContentTitle("Title")
    .setContentText("Text")
    .setSmallIcon(R.drawable.ic_launcher_background)
    .build()

companion object {

    private const val CHANNEL_ID = "channel_id"
    private const val CHANNEL_NAME = "channel_name"
    private const val NOTIFICATION_ID = 1
    private const val NAME = "intent service"

    fun newIntent(context: Context): Intent {
        return Intent(context, MyIntentService::class.java)
    }
}
}