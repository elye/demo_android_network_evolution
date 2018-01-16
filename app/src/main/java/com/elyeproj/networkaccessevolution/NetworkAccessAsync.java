package com.elyeproj.networkaccessevolution;


import android.os.AsyncTask;

import java.lang.ref.WeakReference;

public class NetworkAccessAsync implements NetworkAccess  {

    private MainView view;
    private MyAsyncTask asyncTask;

    private static class MyAsyncTask extends AsyncTask<String, Void, String> {

        private WeakReference<MainView> view;

        // only retain a weak reference to the activity
        MyAsyncTask(MainView view) {
            this.view = new WeakReference<>(view);
        }

        @Override
        protected String doInBackground(String... params) {
            String result = Network.fetchHttp(params[0]);
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            if (view.get() != null) {
                view.get().updateData(result);
            }
        }
    }

    public NetworkAccessAsync(MainView view) {
        this.view = view;
    }

    @Override
    public void fetchData(final String seachString) {
        asyncTask = new MyAsyncTask(view);
        asyncTask.execute(seachString);
    }

    @Override
    public void terminate() {
        asyncTask.cancel(true);
    }
}
