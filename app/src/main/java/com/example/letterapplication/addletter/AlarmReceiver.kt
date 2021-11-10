package com.example.letterapplication.addletter

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.annotation.CallSuper
import com.example.letterapplication.addletter.AddLetterFragment.Companion.NEXT_ID
import com.example.letterapplication.repository.LetterRepository
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
        val pendingResult = goAsync()
        val workerThread = Thread {
            GlobalScope.launch(Dispatchers.IO) {
                val id = intent.getLongExtra(NEXT_ID, 0)
                letterRepository.updateLetter(isLocked = false, id = id.toInt()?: -1)
                pendingResult.finish()
            }
        }
        workerThread.start()
    }
}