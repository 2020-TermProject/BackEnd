package com.example.templatesendinfo;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class LoginTask extends AsyncTask<String, Void , String> {

    @Override
    protected String doInBackground(String... strings) {
        StringBuilder result = new StringBuilder();
        HttpURLConnection urlConnection;
        URL url = null;
        JSONObject js = new JSONObject();
        String name = strings[1];
        String email = strings[2];
        String kid = strings[3];
        try {
            url = new URL(strings[0]);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setDoOutput(true);
            urlConnection.setRequestMethod("POST");

            js.put("email", email);
            js.put("userName", name);
            js.put("kid", kid);
            js.put("extra", "jjkj");

            Log.e("name",name);
            Log.e("email",email);
            Log.e("kid",kid);

            String message = js.toString();
            String data = URLEncoder.encode("postData", "UTF-8") + "=" + URLEncoder.encode(message, "UTF-8");
            Log.e("jsonData",data);
            OutputStreamWriter outr = new OutputStreamWriter(urlConnection.getOutputStream());
            outr.write(data);
            outr.flush();

            Log.e("data 가는지 ","gone");
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            InputStreamReader isr = new InputStreamReader(in);
            BufferedReader reader = new BufferedReader(isr);

            String line;
            while((line = reader.readLine()) != null) {
                result.append(line);
                Log.e("inputData", line);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }
}
