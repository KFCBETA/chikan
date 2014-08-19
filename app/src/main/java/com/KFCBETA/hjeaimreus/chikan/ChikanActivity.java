package com.KFCBETA.hjeaimreus.chikan;

import android.app.Activity;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


public class ChikanActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Log.w("log","activity_1");
        super.onCreate(savedInstanceState);
        Intent intent = new Intent();

        intent.setClass(ChikanActivity.this, DrawerActivity.class);

        startActivity(intent);
        ChikanActivity.this.finish();
    }

}
