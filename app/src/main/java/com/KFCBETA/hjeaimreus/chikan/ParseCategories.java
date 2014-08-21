package com.KFCBETA.hjeaimreus.chikan;

import android.util.Log;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;


/**
 * To parse the side menu items from mySQL database,
 * return a array list in order to input into constructor
 * of navigation drawer.
 * Created by hrw on 14/8/20.
 */
public class ParseCategories {
    private final static String TAG = "ParseCategories";
    private final String link = "http://ea2ac45.ngrok.com/navigationitem";
    private HttpClient httpClient = new DefaultHttpClient();
    private HttpGet request;
    private HttpResponse httpResponse;
    private BufferedReader bufferedReader;
    private ArrayList<String> titles;
    private ArrayList<Integer> article_count;

    ParseCategories() {
        try {
            request = new HttpGet(new URI(link));
            httpResponse = httpClient.execute(request);
        } catch (IOException e) {
            onError(e);
        } catch (URISyntaxException e) {
            onError(e);
        }
    }

    /**
     * Get titles in categories
     * @return ArrayList<String>
     */
    public ArrayList<String> getTitles () {
        try {
            titles = new ArrayList<String>();
            bufferedReader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
            String tempStr = bufferedReader.readLine();
            JSONArray jsonArray = new JSONArray(tempStr);
            for (int i = 0; i < jsonArray.length(); i++) {
                titles.add(jsonArray.getJSONObject(i).getString("titles"));
                article_count.add(jsonArray.getJSONObject(i).getInt("article_count"));
            }
        }catch (IOException e){
            onError(e);
        } catch (JSONException e) {
            onError(e);
        }
        return titles;
    }

    public ArrayList<Integer> getArticleCount () {
        article_count = new ArrayList<Integer>();
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
            String tempStr = bufferedReader.readLine();
            JSONArray jsonArray = new JSONArray(tempStr);
            for (int i = 0; i < jsonArray.length(); i++) {
                article_count.add(jsonArray.getJSONObject(i).getInt("article_count"));
            }
        } catch (IOException e) {
            onError(e);
        } catch (JSONException e) {
            onError(e);
        }
        return article_count;
    }


    private void onError(Exception e){
        Log.w(TAG,e.toString());
    }

}
