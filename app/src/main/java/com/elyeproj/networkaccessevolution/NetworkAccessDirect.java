package com.elyeproj.networkaccessevolution;


import android.os.StrictMode;

import org.jetbrains.annotations.NotNull;

public class NetworkAccessDirect implements NetworkAccess {

    private MainView view;

    public NetworkAccessDirect(MainView view) {
        this.view = view;
    }

    @Override
    public void fetchData(@NotNull String searchText) {

        // Special to allow Network run on UI Thread
        StrictMode.ThreadPolicy originalTreatPolicy = StrictMode.getThreadPolicy();
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());

        // Direct access Network
        String result = Network.fetchHttp(searchText);
        view.updateScreen(result);

        // Special to allow Network run on UI Thread
        StrictMode.setThreadPolicy(originalTreatPolicy);
    }

    @Override
    public void terminate() {
        // Do nothing
    }
}
