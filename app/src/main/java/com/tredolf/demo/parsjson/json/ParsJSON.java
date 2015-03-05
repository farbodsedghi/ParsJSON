package com.tredolf.demo.parsjson.json;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * Created by Farbod Sedghi on 3/3/2015.
 */
public class ParsJSON {

    private String json;
    private JSONArray list;
    private Integer length;

    public ParsJSON(String url) throws IOException, JSONException {
        fetch(url);
    }

    /**
     *
     * @return the number of records
     */
    public Integer getLength() {
        return length;
    }

    /**
     *
     * @param idx represent the record number
     * @return a record from JSON data
     * @throws JSONException
     */
    public PostObject read(int idx) throws JSONException {
        return new PostObject(list.getJSONObject(idx));
    }

    /**
     *
     * @param url JSON file
     * @return true if retrieving is successful
     * @throws IOException
     * @throws JSONException
     */
    public boolean fetch(String url) throws IOException, JSONException {

        HttpClient httpclient = new DefaultHttpClient();

        HttpPost httppost = new HttpPost(url);

        HttpResponse httpResponse = httpclient.execute(httppost);

        InputStream is = httpResponse.getEntity().getContent();

        BufferedReader rd = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));

            boolean ok = false;

            String line;
            StringBuilder json = new StringBuilder();

            while ((line = rd.readLine()) != null) {
                json.append(line);
                ok = true;
            }
            this.json = json.toString();

            JSONObject obj = new JSONObject(this.json);

            this.list = obj.getJSONArray("posts");
            this.length = this.list.length();

            return ok;

    }
}
