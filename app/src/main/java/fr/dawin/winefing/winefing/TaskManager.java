package fr.dawin.winefing.winefing;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Greg on 25/01/2017.
 */

class TasksManagerGet extends AsyncTask<String,Void,String> {
    @Override
    protected String doInBackground(String... params) {
        String request = params[0];
        URL url = null;
        try {
            url = new URL(request);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpURLConnection urlConnection = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            return readStream(urlConnection.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error";
    }

    protected String readStream(InputStream in) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null)
            sb.append(line + "\n");
        br.close();
        return sb.toString();
    }
}
