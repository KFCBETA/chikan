package com.KFCBETA.hjeaimreus.chikan;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
    private final String categoryLink = "http://ea2ac45.ngrok.com/categories";
    private final String intNewsLink = "http://ea2ac45.ngrok.com/intNews";
    private final String intNewsPicsLink = "http://ea2ac45.ngrok.com/intNewsPics";
    private HttpClient httpClient;
    private HttpGet request;
    private HttpResponse httpResponse;
    private BufferedReader bufferedReader;
    private ArrayList<String> titles;
    private ArrayList<Integer> article_count;
    private ArrayList<String> intNewsContent;
//    private ArrayList<Integer> intNewsid;
    private ArrayList<String> intNewstitle;
    private ArrayList<ArrayList<String>> intNews;
    private boolean isParseFinished = false;

    public ParseCategories() {
        article_count = new ArrayList<Integer>();
        titles = new ArrayList<String>();
        intNews = new ArrayList<ArrayList<String>>();
        httpClient = new DefaultHttpClient();
    }

    /**
     * Get titles in categories
     * @return ArrayList<String>
     */
    public ArrayList<String> getTitles () {
        try {
            request = new HttpGet(new URI(categoryLink));
            httpResponse = httpClient.execute(request);
            bufferedReader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
            String tempStr = bufferedReader.readLine();
            JSONArray jsonArray = new JSONArray(tempStr);
            for (int i = 0; i < jsonArray.length(); i++) {
                titles.add(jsonArray.getJSONObject(i).getString("title"));
            }
        }catch (IOException e){
            onError(e);
        } catch (JSONException e) {
            onError(e);
        } catch (URISyntaxException e) {
            onError(e);
        }
        return titles;
    }

    public ArrayList<Integer> getArticleCount () {
        try {
            request = new HttpGet(new URI(categoryLink));
            httpResponse = httpClient.execute(request);
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
        } catch (URISyntaxException e) {
            onError(e);
        }
        return article_count;
    }


    /**
     * get International arraylist with id,title and article included.
     * @return ArrayList
     */
    public ArrayList<ArrayList<String>> getIntNews () {
        try {
//            intNewsid = new ArrayList<Integer>();
            intNewstitle = new ArrayList<String>();
            intNewsContent = new ArrayList<String>();
            request = new HttpGet(new URI(intNewsLink));
            httpResponse = httpClient.execute(request);
            bufferedReader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
            String tempStr = bufferedReader.readLine();
            JSONArray jsonArray = new JSONArray(tempStr);
            for (int i = 0; i < jsonArray.length(); i++) {
//                intNewsid.add((jsonArray.getJSONObject(i).getInt("id")));
                intNewstitle.add(jsonArray.getJSONObject(i).getString("title"));
                intNewsContent.add(jsonArray.getJSONObject(i).getString("content"));
            }
        } catch (IOException e) {
            onError(e);
        } catch (JSONException e) {
            onError(e);
        } catch (URISyntaxException e) {
            onError(e);
        }
        intNews.add(intNewstitle);
        intNews.add(intNewsContent);
        isParseFinished = true;
        return intNews;
    }

    /**
     * get International news pics.
     * @return Image in byte array
     */
    public byte[] getIntNewsPics () {
        try {
            InputStream is = (InputStream) new URL(intNewsPicsLink).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
            Bitmap bitmap = ((BitmapDrawable)d).getBitmap();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            byte[] image = stream.toByteArray();
            return image;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Transfer the getBlob output into Drawable object.
     * @param b getBlob output
     * @param context Current context
     * @return Drawable of getBlob from sqlDataBase
     */
    public Drawable intoDrawable(byte[] b,Context context){
        ByteArrayInputStream is = new ByteArrayInputStream(b);
        return new BitmapDrawable(context.getResources(),BitmapFactory.decodeStream(is));
    }

    private void onError(Exception e){
        Log.w(TAG,e.toString());
    }

    public boolean isParseFinished() {
        return this.isParseFinished;
    }
}

