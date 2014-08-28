package com.KFCBETA.hjeaimreus.chikan;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public class InternationalViewPager extends FragmentActivity {

    International_viewPaperAdapter international_viewAdapter;
    ViewPager viewPager;
    private int file_count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_international_view_pager);
        Bundle bundle = this.getIntent().getExtras();
        int position = bundle.getInt("position");
        file_count = bundle.getInt("file_count");

        international_viewAdapter = new International_viewPaperAdapter(getSupportFragmentManager());

        viewPager = (ViewPager)findViewById(R.id.international_pager);
        viewPager.setAdapter(international_viewAdapter);
        viewPager.setCurrentItem(position);

        ActionBar actionBar = getActionBar();
        actionBar.setTitle("International");
        actionBar.setHomeButtonEnabled(true);


    }

    public class International_viewPaperAdapter extends FragmentStatePagerAdapter
    {
        public International_viewPaperAdapter(FragmentManager fm)
        {
            super(fm);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {

            android.support.v4.app.Fragment fragment_viewPager = new ViewFragment();
            Bundle args = new Bundle();

            Log.w("position",""+position);

            args.putInt(ViewFragment.index,position);
            fragment_viewPager.setArguments(args);
            return fragment_viewPager;
        }

        @Override
        public int getCount() {
            return file_count;
        }

        private ArrayList<String> arrayList = new ArrayList<String>();
        @Override
        public CharSequence getPageTitle(int position)
        {
            for(int i=0;i<file_count;i++)
            {
                arrayList.add(Integer.toString(i+1));
            }

            return arrayList.get(position);
        }
    }

    public static class ViewFragment extends android.support.v4.app.Fragment
    {
        public static final String index = "index";
        InputStream file_in;
        private int file_count = 2;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
        {
            View view_viewpager = inflater.inflate(R.layout.fragment_international_view_pager,container,false);
            Bundle args = getArguments();
            int chooice = args.getInt(index);

            //read article from the file
            String title_number;
            title_number = Integer.toString(chooice+1) + ".txt";
            int size;
            byte[] buffer;
            try {
                file_in = getResources().getAssets().open(title_number);
                size = file_in.available();
            } catch (IOException e) {
                size = 1;
                e.printStackTrace();
            }
            buffer = new byte[size];
            try {
                if(file_in != null)
                {
                    file_in.read(buffer);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            String tmp = new String(buffer);

            //read title from the file
            InputStream file_in_title = null;
            title_number = Integer.toString(chooice+1) + "_title.txt";

            try {
                file_in_title = getResources().getAssets().open(title_number);
                size = file_in_title.available();
            } catch (IOException e) {
                size = 0;
                e.printStackTrace();
            }
            buffer = new byte[size];
            try {
                if(file_in_title != null)
                {
                    file_in_title.read(buffer);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            String tmp_title = new String(buffer);


            ((TextView)view_viewpager.findViewById(R.id.international_title)).setText(tmp_title);
            ((TextView)view_viewpager.findViewById(R.id.international_article)).setText(tmp);

            return view_viewpager;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        ActionBar bar = getActionBar();
        bar.setDisplayHomeAsUpEnabled(true);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem menu)
    {
        switch(menu.getItemId())
        {
            case android.R.id.home:
                this.finish();
                overridePendingTransition(R.anim.left_in,R.anim.right_out);
                break;
            case R.id.setting:
                break;
        }

        return super.onOptionsItemSelected(menu);
    }

    @Override
    public boolean onKeyDown(int KeyCode, KeyEvent event)
    {
        if(KeyCode == KeyEvent.KEYCODE_BACK)
        {
            this.finish();
            overridePendingTransition(R.anim.left_in,R.anim.right_out);

            Log.w("key", "back");
            return true;
        }
        return super.onKeyDown(KeyCode, event);
    }
}
