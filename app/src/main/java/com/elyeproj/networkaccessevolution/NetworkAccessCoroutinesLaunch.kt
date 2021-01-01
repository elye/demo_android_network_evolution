package com.elyeproj.networkaccessevolution

import kotlinx.coroutines.*

class NetworkAccessCoroutinesLaunch(private val view: MainView) : NetworkAccess {
    private var coroutineScope = CoroutineScope(Dispatchers.IO)

    override fun fetchData(searchText: String) {
        coroutineScope.launch {
            val result = Network.fetchHttp(searchText)
            launch(Dispatchers.Main) {
                view.updateScreen(result)
            }
        }
    }

    override fun terminate() {
        coroutineScope.cancel()
    }
}

