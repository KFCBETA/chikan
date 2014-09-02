package com.KFCBETA.hjeaimreus.chikan;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


public class NewsViewPager extends FragmentActivity {

    News_viewPaperAdapter news_viewAdapter;
    ViewPager viewPager;
    private int file_count;
    private ArrayList<String> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_view_pager);

        Bundle bundle = this.getIntent().getExtras();
        int position = bundle.getInt("position");
        file_count = bundle.getInt("file_count");

        news_viewAdapter = new News_viewPaperAdapter(getSupportFragmentManager());

        viewPager = (ViewPager)findViewById(R.id.news_pager);
        viewPager.setAdapter(news_viewAdapter);
        viewPager.setCurrentItem(position);

        ActionBar actionBar = getActionBar();
        actionBar.setTitle("News");
        actionBar.setHomeButtonEnabled(true);

        arrayList = new ArrayList<String>();
        for(int i=0;i<file_count;i++)
        {
            arrayList.add(Integer.toString(i+1));
        }
    }


    public class News_viewPaperAdapter extends FragmentStatePagerAdapter
    {
        public News_viewPaperAdapter(FragmentManager fm)
        {
            super(fm);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {

            android.support.v4.app.Fragment fragment_viewPager = new ViewFragment();
            Bundle args = new Bundle();

            args.putInt(ViewFragment.index,position);
            fragment_viewPager.setArguments(args);
            return fragment_viewPager;
        }

        @Override
        public int getCount() {
            return 6;
        }

        @Override
        public CharSequence getPageTitle(int position)
        {
            return arrayList.get(position);
        }
    }

    public static class ViewFragment extends android.support.v4.app.Fragment
    {
        public static final String index = "index";

        private DataBaseHelper dataBaseHelper;
        private ArrayList<ArrayList<String>> NewsInput;
        private String tmp;
        private String tmp_title;
        private int file_count;

        private static final String TAG = "ViewFragment";


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
        {
            View view_viewpager = inflater.inflate(R.layout.fragment_news_view_pager,container,false);
            Bundle args = getArguments();

            //dataBaseHelper = new DataBaseHelper(getActivity());
            //NewsInput = dataBaseHelper.getIntNews();

            //file_count = NewsInput.get(0).size();

            int chooice = args.getInt(index);

            //read article from the file
            //tmp_title = NewsInput.get(0).get(chooice);
            //tmp = NewsInput.get(1).get(chooice);
            tmp = Integer.toString(chooice);
            tmp_title = Integer.toString(chooice);
            ((TextView)view_viewpager.findViewById(R.id.news_title)).setText(tmp_title);
            ((TextView)view_viewpager.findViewById(R.id.news_article)).setText(tmp);

            return view_viewpager;
        }
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
//            case R.id.setting:
//                break;
        }

        return super.onOptionsItemSelected(menu);
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
}
