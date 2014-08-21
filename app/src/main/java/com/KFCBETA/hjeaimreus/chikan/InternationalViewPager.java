package com.KFCBETA.hjeaimreus.chikan;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


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

        viewPager = (ViewPager)findViewById(R.id.pager);
        viewPager.setAdapter(international_viewAdapter);
        viewPager.setCurrentItem(position);

    }

    public static class International_viewPaperAdapter extends FragmentStatePagerAdapter
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

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
        {
            View view_viewpager = inflater.inflate(R.layout.fragment_international_view_pager,container,false);
            Bundle args = getArguments();
            ((TextView)view_viewpager.findViewById(R.id.international_text)).setText(Integer.toString(args.getInt(index)));
            return view_viewpager;
        }
    }
}
