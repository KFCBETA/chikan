package com.KFCBETA.hjeaimreus.chikan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;


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
