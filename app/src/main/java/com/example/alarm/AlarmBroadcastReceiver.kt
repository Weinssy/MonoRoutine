package com.example.alarm

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import androidx.core.app.NotificationCompat
import com.example.MainActivity

class AlarmBroadcastReceiver : BroadcastReceiver() {
    companion object {
        const val CHANNEL_ID = "monoroutine_alarm_channel"
        const val EXTRA_HABIT_ID = "extra_habit_id"
        const val EXTRA_HABIT_NAME = "extra_habit_name"
        const val EXTRA_HABIT_TIME = "extra_habit_time"
        const val EXTRA_SOUND_PRESET = "extra_sound_preset"
    }

    override fun onReceive(context: Context, intent: Intent) {
        val habitId = intent.getLongExtra(EXTRA_HABIT_ID, 0L)
        val habitName = intent.getStringExtra(EXTRA_HABIT_NAME) ?: "Rutinitas Harian"
        val habitTime = intent.getStringExtra(EXTRA_HABIT_TIME) ?: ""
        val soundPreset = intent.getStringExtra(EXTRA_SOUND_PRESET) ?: "Lembut"

        // Ensure notification channel exists
        createNotificationChannel(context)

        // Trigger vibration
        vibrate(context)

        // Launch app intent
        val launchIntent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            putExtra("triggered_habit_id", habitId)
            putExtra("triggered_habit_name", habitName)
            putExtra("triggered_sound_preset", soundPreset)
        }

        val pendingIntent = PendingIntent.getActivity(
            context,
            habitId.toInt(),
            launchIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_lock_idle_alarm)
            .setContentTitle("Waktunya Rutinitas: $habitName")
            .setContentText("Jadwal: $habitTime WIB. Buka aplikasi untuk mencatat penyelesaian.")
            .setPriority(NotificationCompat.PRIORITY_MAX)
            .setCategory(NotificationCompat.CATEGORY_ALARM)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .setVibrate(longArrayOf(0, 500, 200, 500))
            .build()

        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify((habitId.toInt() + 1000), notification)
    }

    private fun createNotificationChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "MonoRoutine Pengingat & Alarm",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Pengingat alarm rutinitas harian dan tenggat waktu tugas"
                enableVibration(true)
                setShowBadge(true)
            }
            val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }

    private fun vibrate(context: Context) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                val vm = context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as? VibratorManager
                vm?.defaultVibrator?.vibrate(
                    VibrationEffect.createWaveform(longArrayOf(0, 400, 200, 400), -1)
                )
            } else {
                @Suppress("DEPRECATION")
                val v = context.getSystemService(Context.VIBRATOR_SERVICE) as? Vibrator
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    v?.vibrate(
                        VibrationEffect.createWaveform(longArrayOf(0, 400, 200, 400), -1)
                    )
                } else {
                    @Suppress("DEPRECATION")
                    v?.vibrate(longArrayOf(0, 400, 200, 400), -1)
                }
            }
        } catch (_: Exception) {}
    }
}
