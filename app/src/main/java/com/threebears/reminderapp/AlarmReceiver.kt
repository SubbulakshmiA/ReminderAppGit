package com.threebears.reminderapp

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.Toast
import androidx.core.content.ContextCompat


class AlarmReceiver:BroadcastReceiver() {
    lateinit var nb: Notification.Builder

    override fun onReceive(context: Context?, intent: Intent?) {
        Toast.makeText(context, "ALARM ON", Toast.LENGTH_SHORT).show()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "reminders_notification_channel_id",
                "reminders_notification_channel_name",
                NotificationManager.IMPORTANCE_HIGH
            )
            if (context != null) {
                ContextCompat.getSystemService(context, NotificationManager::class.java)
                    ?.createNotificationChannel(channel)
            }
            val intent = Intent(context, MainActivity::class.java)

            val pi = PendingIntent.getActivity(context,0,intent, PendingIntent.FLAG_IMMUTABLE)

            nb = Notification.Builder(context, "reminders_notification_channel_id")
                .setContentIntent(pi)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Alarm ON WAKE UP")
                .setContentText(intent.getStringExtra("time"))
        }
        if (context != null) {
            ContextCompat.getSystemService(context, NotificationManager::class.java)?.notify(
                1,nb.build())
        }
    }
}