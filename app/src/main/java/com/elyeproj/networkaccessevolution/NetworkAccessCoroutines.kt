package com.elyeproj.networkaccessevolution

import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch

class NetworkAccessCoroutines(private val view: MainView) : NetworkAccess {
    private var job: Job? = null

    override fun fetchData(searchText: String) {
        val defer = async {
            Network.fetchHttp(searchText)
        }

        job = launch(UI) {
            view.updateScreen(defer.await())
        }
    }

    override fun terminate() {
        job?.cancel()
    }
}

