package com.purplecorn.icebreaknow.util;

import android.os.AsyncTask;

import org.json.JSONObject;

/**
 * Created by Kit on 5/21/2016.
 */
public class RemoteImageLoader extends AsyncTask<String, Integer, JSONObject> {

    private AsyncTaskCallbackListener callback;

    public RemoteImageLoader(AsyncTaskCallbackListener callback){
        this.callback = callback;
    }

    @Override
    protected JSONObject doInBackground(String... params) {
        return null;
    }

    @Override
    protected void onPostExecute(JSONObject jsonObject) {
        super.onPostExecute(jsonObject);
    }
}
