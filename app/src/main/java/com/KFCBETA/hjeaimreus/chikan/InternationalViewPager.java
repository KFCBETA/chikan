package com.KFCBETA.hjeaimreus.chikan;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
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

import fragment.InternationalFragment;


public class InternationalViewPager extends FragmentActivity {

    International_viewPaperAdapter international_viewAdapter;
    ViewPager viewPager;
    private ActionBarDrawerToggle internationalViewPager;

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


        internationalViewPager = new ActionBarDrawerToggle(
                this,
                null,
                R.drawable.ic_drawer,
                R.string.drawer_open,
                R.string.drawer_close){

            public void onDrawerClosed(View view){
                invalidateOptionsMenu();
            }
            public void onDrawerOpen(View view) {
                invalidateOptionsMenu();
            }
        };
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menu)
    {
        switch(menu.getItemId())
        {
            case android.R.id.home:
                Intent upIntent = new Intent(this, DrawerActivity.class);
                startActivity(upIntent);

            return true;
        }

        return super.onOptionsItemSelected(menu);
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
