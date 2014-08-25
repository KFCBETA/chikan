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
import java.util.ArrayList;


public class EconomicViewPager extends FragmentActivity {

    Economic_viewPaperAdapter economic_viewAdapter;
    ViewPager viewPager;
    private int file_count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_economic_view_pager);

        Bundle bundle = this.getIntent().getExtras();
        int position = bundle.getInt("position");
        file_count = bundle.getInt("file_count");

        economic_viewAdapter = new Economic_viewPaperAdapter(getSupportFragmentManager());

        viewPager = (ViewPager)findViewById(R.id.economic_pager);
        viewPager.setAdapter(economic_viewAdapter);
        viewPager.setCurrentItem(position);

        ActionBar actionBar = getActionBar();
        actionBar.setTitle("Economic");
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


    public class Economic_viewPaperAdapter extends FragmentStatePagerAdapter
    {
        public Economic_viewPaperAdapter(FragmentManager fm)
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

        private ParseCategories parseCategories;
        private ArrayList<ArrayList<String>> intNews;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
        {
            View view_viewpager = inflater.inflate(R.layout.fragment_economic_view_pager,container,false);
            Bundle args = getArguments();
            int chooice = args.getInt(index);

            parseCategories = new ParseCategories();
            intNews = new ArrayList<ArrayList<String>>();

            //read article from the file

            intNews = parseCategories.getIntNews();


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
//            String tmp = new String(buffer);
            String tmp = intNews.get(1).get(0);
            //read title from the file
            InputStream file_in_title = null;
            title_number = Integer.toString(chooice) + "_title.txt";

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
//            String tmp_title = new String(buffer);
            String tmp_title = intNews.get(0).get(0);

            ((TextView)view_viewpager.findViewById(R.id.economic_title)).setText(tmp_title);
            ((TextView)view_viewpager.findViewById(R.id.economic_article)).setText(tmp);

            return view_viewpager;
        }
    }
}
