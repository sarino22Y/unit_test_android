package com.example.unittestbd.utils;

import android.os.AsyncTask;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Classe technique de connexion distante HTTP
 */
public class HTTPAcces1 extends AsyncTask<String, Integer, Long> {

    static String host1 = "http://10.0.0.160";
    static String host2 = "http://192.168.110.49";
    private static final String BASE_URL = host2 + "/apicartenfc/serveurclient.php?action=";

    private OnOperationCompletedListener listener;

    private String responseServer = "";

    public HTTPAcces1(OnOperationCompletedListener listener) {
        this.listener = listener;
    }

    @Override
    protected Long doInBackground(String... params) {
        String operation = params[0];
        String data = params[1];

        String url = BASE_URL + operation + "&dataclient=" + data;

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();

        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                responseServer = response.body().string();
                return 1L; // Succès
            } else {
                return -1L; // Échec
            }
        } catch (IOException e) {
            e.printStackTrace();
            return -1L; // Échec
        }
    }

    @Override
    protected void onPostExecute(Long result) {
        if (listener != null) {
            if (result == 1L) {
                listener.onOperationCompleted(responseServer);
            } else {
                listener.onOperationCompleted("Echec : " + responseServer);
            }
        }
    }

    public interface OnOperationCompletedListener {
        void onOperationCompleted(String messageServeur);
    }
}
