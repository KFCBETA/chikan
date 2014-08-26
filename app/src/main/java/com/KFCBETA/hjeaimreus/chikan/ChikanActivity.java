package com.KFCBETA.hjeaimreus.chikan;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;


public class ChikanActivity extends Activity{
    private ParseCategories parseCategories;
    private ArrayList<ArrayList<String>> intNews;
    private DataBaseHelper dataBaseHelper;
    private static final String TAG = "ChikanActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        dataBaseHelper = new DataBaseHelper(this);
        parseCategories = new ParseCategories();
        new Thread(new Runnable() {
            @Override
            public void run() {
                intNews = parseCategories.getIntNews();
            }
        }).start();
        while(!parseCategories.isParseFinished()){
        }
        Log.w(TAG,""+intNews.size());
        dataBaseHelper.addIntNews(intNews);
        Log.w("log","activity_1");
        super.onCreate(savedInstanceState);
        Intent intent = new Intent();

        intent.setClass(ChikanActivity.this, DrawerActivity.class);

        startActivity(intent);
        ChikanActivity.this.finish();
    }


}
