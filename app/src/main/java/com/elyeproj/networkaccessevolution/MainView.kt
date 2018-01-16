package com.elyeproj.networkaccessevolution

import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter

interface MainView {
    fun updateScreen(result: String)
    fun registerIntentServiceReceiver(receiver : BroadcastReceiver, filter : IntentFilter)
    fun unregisterIntentServiceReceiver(receiver: BroadcastReceiver)
    fun startIntentService(intent: Intent)
}