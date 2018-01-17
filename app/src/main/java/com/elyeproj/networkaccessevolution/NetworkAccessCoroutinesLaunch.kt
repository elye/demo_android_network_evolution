package com.elyeproj.networkaccessevolution

import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch

class NetworkAccessCoroutinesLaunch(private val view: MainView) : NetworkAccess {
    private var job: Job? = null

    override fun fetchData(searchText: String) {
        job = launch {
            val result = Network.fetchHttp(searchText)

            launch(UI) {
                view.updateScreen(result)
            }
        }
    }

    override fun terminate() {
        job?.cancel()
    }
}

