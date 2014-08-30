package com.KFCBETA.hjeaimreus.chikan;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;


public class ChikanActivity extends Activity{
    private ParseCategories parseCategories;
    private ArrayList<ArrayList<String>> intNews;
    private byte[] image;
    private DataBaseHelper dataBaseHelper;
    private static final String TAG = "ChikanActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        ConnectivityManager CM = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = CM.getActiveNetworkInfo();

        if(info == null)
        {
            Log.w("network","null");
        }
        else
        {
            dataBaseHelper = new DataBaseHelper(this);
            parseCategories = new ParseCategories();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    intNews = parseCategories.getIntNews();
                    image = parseCategories.getIntNewsPics();
                }
            }).start();
            while(!parseCategories.isParseFinished()){
            }
            dataBaseHelper.addIntNews(intNews);
        }


        super.onCreate(savedInstanceState);
        Intent intent = new Intent();

        intent.setClass(ChikanActivity.this, DrawerActivity.class);

        startActivity(intent);
        ChikanActivity.this.finish();
    }


}
