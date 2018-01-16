package com.elyeproj.networkaccessevolution;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import org.jetbrains.annotations.NotNull;

import static com.elyeproj.networkaccessevolution.NetworkIntentService.PARAM_OUT_MSG;

public class NetworkAccessIntentService implements NetworkAccess {

    private MainView view;
    private IntentReceiver intentReceiver = new IntentReceiver();


    public NetworkAccessIntentService(MainView view) {
        this.view = view;
    }

    public void registerReceiver() {
        IntentFilter filter = new IntentFilter(NetworkIntentService.ACTION_RESP);
        filter.addCategory(Intent.CATEGORY_DEFAULT);
        view.registerIntentServiceReceiver(intentReceiver, filter);
    }

    @Override
    public void fetchData(@NotNull String parameterName) {

        Intent msgIntent = new Intent((Context) view, NetworkIntentService.class);
        msgIntent.putExtra(NetworkIntentService.PARAM_IN_MSG, parameterName);
        view.startIntentService(msgIntent);

    }

    @Override
    public void terminate() {
        view.unregisterIntentServiceReceiver(intentReceiver);
    }

    class IntentReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            NetworkAccessIntentService.this.view.updateScreen(intent.getStringExtra(PARAM_OUT_MSG));
        }
    }
}
