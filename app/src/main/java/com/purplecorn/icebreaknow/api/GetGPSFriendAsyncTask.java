package com.purplecorn.icebreaknow.api;

import android.os.AsyncTask;

import com.purplecorn.icebreaknow.util.AsyncTaskCallbackListener;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Kit on 5/15/2016.
 */
public class GetGPSFriendAsyncTask extends AsyncTask<JSONObject, Integer, JSONObject> {

    private AsyncTaskCallbackListener callback;

    public GetGPSFriendAsyncTask(AsyncTaskCallbackListener callback){
        this.callback = callback;
    }

    @Override
    protected JSONObject doInBackground(JSONObject... params) {
        HttpURLConnection urlConnection = null;
        JSONObject dataJSON = null;
        try {
            URL url = new URL(IceBreakAPIConstant.PROD_URL);
            urlConnection = (HttpURLConnection) url.openConnection();


            urlConnection.setDoOutput(true);
            urlConnection.setChunkedStreamingMode(0);
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setRequestProperty("Accept-Charset", "UTF-8");
            urlConnection.connect();

            JSONObject ibParam = new JSONObject();
            JSONObject requestParams = new JSONObject();
            JSONObject methodParams = new JSONObject();

            requestParams.put(IceBreakAPIConstant.REQ_KEY_REQUEST_METHOD, IceBreakAPIConstant.REQ_METHOD_RECENT_USERS);
            methodParams.put(IceBreakAPIConstant.REQ_KEY_UserID, "134");
            requestParams.put(IceBreakAPIConstant.REQ_KEY_METHOD_PARAMS, methodParams);

            ibParam.put(IceBreakAPIConstant.REQ_KEY_IB_REQUEST, requestParams);

            OutputStream out = new BufferedOutputStream(urlConnection.getOutputStream());
            out.write(ibParam.toString().getBytes(IceBreakAPIConstant.UTF8_ENCODING));
            out.flush();
            out.close();

            int responseCode = urlConnection.getResponseCode();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader reader = null;
            reader = new BufferedReader(new InputStreamReader(in, IceBreakAPIConstant.UTF8_ENCODING));
            int len = 1024;
            //char[] buffer = new char[len];
            StringBuilder builder = new StringBuilder();
            String buffer = null;
            //while (reader.read(buffer) != -1) {
            while((buffer = reader.readLine()) != null){
                builder.append(buffer);
            }
            in.close();
            System.out.println(builder.length());
            JSONObject responseJSON = new JSONObject(builder.toString());
            dataJSON = responseJSON.optJSONObject(IceBreakAPIConstant.RESP_KEY_IB_RESPONSE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataJSON;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(JSONObject jsonObject) {

        callback.onFinish(jsonObject);
    }
}
