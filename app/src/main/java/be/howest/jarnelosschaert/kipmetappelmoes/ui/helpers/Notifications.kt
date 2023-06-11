package be.howest.jarnelosschaert.kipmetappelmoes.ui.helpers

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.work.*
import be.howest.jarnelosschaert.kipmetappelmoes.MainActivity
import be.howest.jarnelosschaert.kipmetappelmoes.R
import java.util.concurrent.TimeUnit

const val NOTIFICATION_ID = 0
const val CHANNEL_ID = "kpa"

@Composable
fun HandleNotifications() {
    val context = LocalContext.current
    WorkManager.getInstance(context).cancelAllWork()

    val notification = OneTimeWorkRequestBuilder<NotificationWorker>()
        .setInitialDelay(1, TimeUnit.DAYS)
        .build()
    WorkManager.getInstance(context).enqueue(notification)
}

class NotificationWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    override fun doWork(): Result {
        createNotificationChannel()

        val notification = NotificationCompat.Builder(applicationContext, CHANNEL_ID)
            .setContentTitle("Kip met appelmoes")
            .setContentText("Heb je al een restaurant gevonden?")
            .setSmallIcon(R.drawable.chicken)
            .setVibrate(LongArray(0))
            .setContentIntent(getPendingIntent())
            .build()

        val notificationManager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(NOTIFICATION_ID, notification)

        return Result.success()
    }

    private fun createNotificationChannel() {
        val channelId = CHANNEL_ID
        val channelName = "KipMetAppelmoes"
        val channelDescription = "default notification channel"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(channelId, channelName, importance).apply {
            description = channelDescription
        }
        val notificationManager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

    private fun getPendingIntent(): PendingIntent {
        val intent = Intent(applicationContext, MainActivity::class.java)

        val notificationManager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.cancel(NOTIFICATION_ID)

        return PendingIntent.getActivity(
            applicationContext,
            0,
            intent,
            PendingIntent.FLAG_IMMUTABLE
        )
    }
}