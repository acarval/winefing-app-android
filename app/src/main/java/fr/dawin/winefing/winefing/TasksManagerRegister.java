package fr.dawin.winefing.winefing;

import android.net.Uri;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Greg on 26/01/2017.
 */

class TasksManagerRegister extends AsyncTask<String,Void,String> {
    @Override
    protected String doInBackground(String... params) {
        String request = params[0];
        String firstName = params[1];
        String lastName = params[2];
        String email = params[3];
        String password = params[4];

        URL url = null;
        try {
            url = new URL(request);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpURLConnection urlConnection = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.setRequestMethod("POST");

            Uri.Builder builder = new Uri.Builder()
                    .appendQueryParameter("firstName", firstName)
                    .appendQueryParameter("lastName", lastName)
                    .appendQueryParameter("email", email)
                    .appendQueryParameter("password", password);
            String query = builder.build().getEncodedQuery();

            OutputStream os = urlConnection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write(query);
            writer.flush();
            writer.close();
            os.close();
            return readStream(urlConnection.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "404 Error";
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

