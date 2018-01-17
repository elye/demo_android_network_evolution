package com.elyeproj.networkaccessevolution

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class NetworkAccessRxJavaKotlin(private val view: MainView) : NetworkAccess {

    private var disposable: Disposable? = null

    override fun fetchData(searchText: String) {
        disposable = Single.fromCallable { Network.fetchHttp(searchText) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { result -> view.updateScreen(result) }
    }

    override fun terminate() {
        disposable?.dispose()
    }
}
