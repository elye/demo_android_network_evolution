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
    private val networkAccessAsync = NetworkAccessAsync(this)
    private val networkAccessIntentService = NetworkAccessIntentService(this)
    private val networkAccessRxJava = NetworkAccessRxJava(this)
    private val networkAccessRxJavaKotlin = NetworkAccessRxJavaKotlin(this)
    private val networkAccessCoroutinesLaunch = NetworkAccessCoroutinesLaunch(this)
    private val networkAccessCoroutinesAsyncAwait = NetworkAccessCoroutinesAsyncAwait(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        networkAccessIntentService.registerReceiver()

        btn_search_direct.setOnClickListener {
            beginSearch(::beginSearchDirect)
        }

        btn_search_thread.setOnClickListener {
            beginSearch(::beginSearchThread)
        }

        btn_search_async.setOnClickListener {
            beginSearch(::beginSearchAsync)
        }

        btn_search_intent_service.setOnClickListener {
            beginSearch(::beginSearchIntentService)
        }

        btn_search_rxjava.setOnClickListener {
            beginSearch(::beginSearchRxJava)
        }

        btn_search_rxjava_kotlin.setOnClickListener {
            beginSearch(::beginSearchRxJavaKotlin)
        }

        btn_search_coroutines_launch.setOnClickListener {
            beginSearch(::beginSearchCoroutinesLaunch)
        }
        btn_search_coroutines_async_await.setOnClickListener {
            beginSearch(::beginSearchCoroutinesAsyncAwait)
        }
    }

    private fun beginSearch(searchFunc : (query: String) -> Unit) {
        if (edit_search.text.toString().isNotEmpty()) {
            searchFunc(edit_search.text.toString())
        }
    }

    private fun beginSearchDirect(queryString: String) {
        networkAccessDirect.fetchData(queryString)
    }

    private fun beginSearchThread(queryString: String) {
        networkAccessThread.fetchData(queryString)
    }

    private fun beginSearchAsync(queryString: String) {
        networkAccessAsync.fetchData(queryString)
    }

    private fun beginSearchIntentService(queryString: String) {
        networkAccessIntentService.fetchData(queryString)
    }

    private fun beginSearchRxJava(queryString: String) {
        networkAccessRxJava.fetchData(queryString)
    }

    private fun beginSearchRxJavaKotlin(queryString: String) {
        networkAccessRxJavaKotlin.fetchData(queryString)
    }

    private fun beginSearchCoroutinesLaunch(queryString: String) {
        networkAccessCoroutinesLaunch.fetchData(queryString)
    }

    private fun beginSearchCoroutinesAsyncAwait(queryString: String) {
        networkAccessCoroutinesAsyncAwait.fetchData(queryString)
    }


    override fun onDestroy() {
        super.onDestroy()
        networkAccessDirect.terminate()
        networkAccessThread.terminate()
        networkAccessAsync.terminate()
        networkAccessIntentService.terminate()
        networkAccessRxJava.terminate()
        networkAccessRxJavaKotlin.terminate()
        networkAccessCoroutinesAsyncAwait.terminate()
    }

    override fun updateScreen(result: String) {
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
