package com.elyeproj.networkaccessevolution

import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), MainView {

    private val networkAccessDirect = NetworkAccessDirect(this)
    private val networkAccessThread = NetworkAccessThread(this)
    private val networkAccessAsynch = NetworkAccessAsync(this)
    private val networkAccessIntentService = NetworkAccessIntentService(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        networkAccessIntentService.registerReceiver()

        btn_search_direct.setOnClickListener {
            if (edit_search.text.toString().isNotEmpty()) {
                beginSearchDirect(edit_search.text.toString())
            }
        }

        btn_search_thread.setOnClickListener {
            if (edit_search.text.toString().isNotEmpty()) {
                beginSearchThread(edit_search.text.toString())
            }
        }

        btn_search_async.setOnClickListener {
            if (edit_search.text.toString().isNotEmpty()) {
                beginSearchAsync(edit_search.text.toString())
            }
        }

        btn_search_intent_service.setOnClickListener {
            if (edit_search.text.toString().isNotEmpty()) {
                beginSearchIntentService(edit_search.text.toString())
            }
        }

    }

    private fun beginSearchDirect(queryString: String) {
        networkAccessDirect.fetchData(queryString)
    }

    private fun beginSearchThread(queryString: String) {
        networkAccessThread.fetchData(queryString)
    }

    private fun beginSearchAsync(queryString: String) {
        networkAccessAsynch.fetchData(queryString)
    }

    private fun beginSearchIntentService(queryString: String) {
        networkAccessIntentService.fetchData(queryString)
    }

    override fun onDestroy() {
        super.onDestroy()
        networkAccessDirect.terminate()
        networkAccessThread.terminate()
        networkAccessAsynch.terminate()
        networkAccessIntentService.terminate()
    }

    override fun updateData(result: String) {
        txt_search_result.text = result
    }

    override fun unregisterIntentServiceReceiver(receiver: BroadcastReceiver) {
        unregisterReceiver(receiver)
    }

    override fun registerIntentServiceReceiver(receiver: BroadcastReceiver, filter: IntentFilter) {
        this.registerReceiver(receiver, filter)
    }

    override fun startIntentService(intent: Intent) {
        startService(intent)
    }
}
