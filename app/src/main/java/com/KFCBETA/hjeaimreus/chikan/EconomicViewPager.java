package com.KFCBETA.hjeaimreus.chikan;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
                this.finish();
                overridePendingTransition(R.anim.left_in,R.anim.right_out);
                break;
            case R.id.setting:
                break;
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

        private DataBaseHelper dataBaseHelper;
        private ArrayList<ArrayList<String>> economicInput;
        private String tmp;
        private String tmp_title;
        private int file_count;

        private static final String TAG = "ViewFragment";


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
        {
            View view_viewpager = inflater.inflate(R.layout.fragment_economic_view_pager,container,false);
            Bundle args = getArguments();

            dataBaseHelper = new DataBaseHelper(getActivity());
            economicInput = dataBaseHelper.getIntNews();

            file_count = economicInput.get(0).size();

            Log.w(TAG,economicInput.get(0).get(0));
            int chooice = args.getInt(index);

            //read article from the file
            tmp_title = economicInput.get(0).get(chooice);
            tmp = economicInput.get(1).get(chooice);
            ((TextView)view_viewpager.findViewById(R.id.economic_title)).setText(tmp_title);
            ((TextView)view_viewpager.findViewById(R.id.economic_article)).setText(tmp);

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
}
