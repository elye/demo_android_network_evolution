package com.elyeproj.networkaccessevolution

import kotlinx.coroutines.*


class NetworkAccessCoroutinesAsyncAwait(private val view: MainView) : NetworkAccess {
    private var coroutineScope = MainScope()

    override fun fetchData(searchText: String) {
        coroutineScope.launch {
            val defer = async(Dispatchers.IO) {
                Network.fetchHttp(searchText)
            }
            view.updateScreen(defer.await())
        }
    }

    override fun terminate() {
        coroutineScope.cancel()
    }
}

