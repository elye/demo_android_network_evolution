package com.elyeproj.networkaccessevolution;

import java.util.concurrent.Callable;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
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
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        view.updateScreen(s);
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
