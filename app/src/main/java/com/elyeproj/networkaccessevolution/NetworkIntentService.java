package com.elyeproj.networkaccessevolution;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

public class NetworkIntentService extends IntentService {

    public static final String PARAM_IN_MSG = "param_in_msg";
    public static final String PARAM_OUT_MSG = "param_out_msg";
    public static final String ACTION_RESP = "com.elyeproj.networkaccessevolution";


    public NetworkIntentService() {
        super("Network Access Intent Service");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String searchText = intent.getStringExtra(PARAM_IN_MSG);
        String result = Network.fetchHttp(searchText);

        Intent broadcastIntent = new Intent();
        broadcastIntent.setAction(ACTION_RESP);
        broadcastIntent.addCategory(Intent.CATEGORY_DEFAULT);
        broadcastIntent.putExtra(PARAM_OUT_MSG, result);
        sendBroadcast(broadcastIntent);
    }
}
