package fr.dawin.winefing.winefing;

import android.net.Uri;

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
import java.util.HashMap;

/**
 * Created by Greg on 31/01/2017.
 */
public class RequestManager {

    public String post(String url, HashMap<String,String> params){

        URL request = null;
        try {
            request = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpURLConnection urlConnection = null;

        try {
            urlConnection = (HttpURLConnection) request.openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.setRequestMethod("POST");

            Uri.Builder builder = new Uri.Builder();
            for(String param : params.keySet()){
                builder.appendQueryParameter(param, params.get(param));
            }
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
        try {
            return String.valueOf(urlConnection.getResponseCode());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "error_server";
    }

    public String get(String url)
    {
        URL request = null;
        try {
            request = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpURLConnection urlConnection = null;

        try {
            urlConnection = (HttpURLConnection) request.openConnection();
            urlConnection.setRequestMethod("GET");
            return readStream(urlConnection.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
            return "error_server";
        }
    }

    public String delete(String url, HashMap<String,String> params){
        return "";

    }

    public String patch(String url, HashMap<String,String> params){
        return "";

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
