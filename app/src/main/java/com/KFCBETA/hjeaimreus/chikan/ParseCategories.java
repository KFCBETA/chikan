package com.KFCBETA.hjeaimreus.chikan;

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
import java.net.URL;
import java.util.ArrayList;


/**
 * To parse the side menu items from mySQL database,
 * return a array list in order to input into constructor
 * of navigation drawer.
 * Created by hrw on 14/8/20.
 */
public class ParseCategories {
    private final static String TAG = "ParseCategories";
    private final String link = "";
    private URL url;
    private HttpClient httpClient = new DefaultHttpClient();
    private HttpGet request;
    private HttpResponse httpResponse;
    private BufferedReader bufferedReader;
    private ArrayList<String> titles;
    private ArrayList<Integer> article_count;
    private ArrayList navigationItem;

    ParseCategories() throws IOException, URISyntaxException {
        url = new URL(link);
        request = new HttpGet(new URI(link));
        httpResponse = httpClient.execute(request);
    }

    public ArrayList getNavigationDrawerList () throws IOException, JSONException {
        bufferedReader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
        titles = new ArrayList<String>();
        article_count = new ArrayList<Integer>();
        String tempStr = bufferedReader.readLine();
        JSONArray jsonArray = new JSONArray(tempStr);
        for (int i = 0 ; i < jsonArray.length() ; i++){
            titles.add(jsonArray.getJSONObject(i).getString("titles"));
            article_count.add(jsonArray.getJSONObject(i).getInt("article_count"));
        }
        navigationItem.add(titles);
        navigationItem.add(article_count);
        return navigationItem;
    }

}
