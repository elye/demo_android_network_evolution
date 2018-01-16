package com.elyeproj.networkaccessevolution;

import android.os.Handler;
import android.os.Looper;

public class NetworkAccessThread implements NetworkAccess {

    private MainView view;
    private Thread thread = null;

    public NetworkAccessThread(MainView view) {
        this.view = view;
    }

    @Override
    public void fetchData(final String searchText) {
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                final String result = Network.fetchHttp(searchText);

                // Run the result on Main UI Thread
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        view.updateScreen(result);
                    }
                });
            }
        });

        thread.start();
    }

    @Override
    public void terminate() {
        if (thread != null) {
            thread.interrupt();
        }
    }
}
