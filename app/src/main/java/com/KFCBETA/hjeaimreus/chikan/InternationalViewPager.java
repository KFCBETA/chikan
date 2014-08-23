package com.KFCBETA.hjeaimreus.chikan;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;


public class InternationalViewPager extends FragmentActivity {

    International_viewPaperAdapter international_viewAdapter;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_international_view_pager);
        Bundle bundle = this.getIntent().getExtras();
        int position = bundle.getInt("position");

        international_viewAdapter = new International_viewPaperAdapter(getSupportFragmentManager());

        viewPager = (ViewPager)findViewById(R.id.international_pager);
        viewPager.setAdapter(international_viewAdapter);
        viewPager.setCurrentItem(position);

        ActionBar actionBar = getActionBar();
        actionBar.setTitle("International");
        actionBar.setHomeButtonEnabled(true);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menu)
    {
        switch(menu.getItemId())
        {
            case android.R.id.home:
                //Intent upIntent = new Intent(this, DrawerActivity_test.class);
                //startActivity(upIntent);
                this.finish();
                overridePendingTransition(R.anim.left_in,R.anim.right_out);
            return true;
        }

        return super.onOptionsItemSelected(menu);
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

            args.putInt(ViewFragment.index,position+1);
            fragment_viewPager.setArguments(args);
            return fragment_viewPager;
        }

        @Override
        public int getCount() {
            return 7;
        }

        @Override
        public CharSequence getPageTitle(int position)
        {
            return "" + (position+1);
        }
    }

    public static class ViewFragment extends android.support.v4.app.Fragment
    {
        public static final String index = "index";
        InputStream file_in;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
        {
            View view_viewpager = inflater.inflate(R.layout.fragment_international_view_pager,container,false);
            Bundle args = getArguments();
            int chooice = args.getInt(index);

            String title_number;
            title_number = Integer.toString(chooice) + ".txt";

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


            InputStream file_in_title = null;
            title_number = Integer.toString(chooice) + "_title.txt";

            try {
                file_in_title = getResources().getAssets().open(title_number);
                size = file_in_title.available();
            } catch (IOException e) {
                size = 1;
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
}
