package com.elyeproj.networkaccessevolution;

import java.util.concurrent.Callable;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class NetworkAccessRxJava implements NetworkAccess {

    private Disposable disposable = null;
    private MainView view = null;

    public NetworkAccessRxJava(MainView view) {
        this.view = view;
    }

    @Override
    public void fetchData(final String searchText) {
        disposable = Single.fromCallable(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return Network.fetchHttp(searchText);
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<String>() {
                    @Override
                    public void onSuccess(String result) {
                        view.updateScreen(result);

                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    @Override
    public void terminate() {
        if (disposable != null) {
            disposable.dispose();
        }
    }
}
