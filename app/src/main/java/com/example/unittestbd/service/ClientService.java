package com.example.unittestbd.service;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import com.example.unittestbd.utils.AsyncResponse;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class ClientService extends AsyncTask<String, Integer, String> {

    static String host1 = "http://192.168.110.49";
    static String host2 = "http://192.168.110.49";
    static String host3 = "http://10.0.0.160";

    private static final String BASE_URL = host3 + "/apicartenfc/serveurclient.php";
    public String responseServer = "";

    private AsyncResponse listener;

    public ClientService() {
        super();
    }

    public ClientService(AsyncResponse listener) {
        this.listener = listener;
    }

    public void setListener(AsyncResponse listener) {
        this.listener = listener;
    }
    @Override
    protected String doInBackground(String... strings) {
        try {
            String params = strings[0];
            Log.d("STRINGGGGGG ", strings[0]);
            Log.d("params", params);
            String url = BASE_URL + params;
            Log.d("url", url.toString());
            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url(url)
                    .build();
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                responseServer = response.body().string();
            } else {
                responseServer = "Echec";
            }
        } catch (IOException e) {
            responseServer = "catch%Connexion impossible...";
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (listener != null) {
            listener.processFinish(responseServer.toString());
        }
    }

}
