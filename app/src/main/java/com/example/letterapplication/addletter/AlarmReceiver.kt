package com.example.letterapplication.addletter

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.Toast
import androidx.annotation.CallSuper
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.letterapplication.R
import com.example.letterapplication.addletter.AddLetterFragment.Companion.NEXT_ID
import com.example.letterapplication.letterlist.LetterListActivity
import com.example.letterapplication.repository.LetterRepository
import com.example.letterapplication.repository.LetterRepositoryImpl
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

abstract class HiltBroadcastReceiver : BroadcastReceiver() {
    @CallSuper
    override fun onReceive(context: Context, intent: Intent) {}
}

@AndroidEntryPoint
class AlarmReceiver: HiltBroadcastReceiver() {

    @Inject lateinit var letterRepository: LetterRepository

    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)
        val id = intent.getLongExtra(NEXT_ID, 0)
        val pendingResult = goAsync()
        val workerThread = Thread {
            GlobalScope.launch(Dispatchers.IO) {
                letterRepository.updateLetter(isLocked = false, id = id.toInt()?: -1)
                pendingResult.finish()
            }
        }
        workerThread.start()
        createNotification(context, id.toInt())
    }

    private fun createNotification(context: Context, id: Int) {
        val intent = Intent(context, LetterListActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(context, 0, intent, 0)
        val builder = NotificationCompat.Builder(context, "1")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle("Letter is ready to be read")
            .setContentText("Letter id $id is ready to be read")
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        createNotificationChannel(context)
        with(NotificationManagerCompat.from(context)) {
            // notificationId is a unique int for each notification that you must define
            notify(id, builder.build())
        }
    }
    private fun createNotificationChannel(context: Context) {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = context.getString(R.string.app_name)
            val descriptionText = "Letters"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel("1", name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

}