package com.KFCBETA.hjeaimreus.chikan;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import fragment.EconomicFragment;
import fragment.EntertainmentFragment;
import fragment.InternationalFragment;
import fragment.NewsFragment;
import fragment.ScienceFragment;
import fragment.SportFragment;


public class DrawerActivity extends FragmentActivity {

    private DrawerLayout mainDrawerLayout;
    private ListView mainDrawerList;
    private ActionBarDrawerToggle mainDrawerToggle;


    private CharSequence mainDrawerTitle;
    private CharSequence mTitle;
    private String[] sectionTitle;

    @Override
    protected  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);

        //ParseCategories parseCategories = new ParseCategories();
        //ArrayList<String> titles = (ArrayList<String>)parseCategories.getNavigationDrawerList().get(0);

        mTitle = getTitle();
        sectionTitle = getResources().getStringArray(R.array.section_name);
        mainDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        mainDrawerList = (ListView)findViewById(R.id.left_drawer);

        mainDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

        mainDrawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item,sectionTitle));
        mainDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);


        mainDrawerToggle = new ActionBarDrawerToggle(
                this,
                mainDrawerLayout,
                R.drawable.ic_drawer,
                R.string.drawer_open,
                R.string.drawer_close){

            public void onDrawerClosed(View view)
            {
                getActionBar().setTitle(mTitle);
                invalidateOptionsMenu();
            }
            public void onDrawerOpen(View view) {
                getActionBar().setTitle(mainDrawerTitle);
                invalidateOptionsMenu();
            }
        };
        mainDrawerLayout.setDrawerListener(mainDrawerToggle);

        if(savedInstanceState == null)
        {
            selectItem(0);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
        boolean drawerOpen = mainDrawerLayout.isDrawerOpen(mainDrawerList);
        menu.findItem(R.id.action_setting).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    //action when the action bar is clicked
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(mainDrawerToggle.onOptionsItemSelected(item))
        {
            return true;
        }

        switch(item.getItemId())
        {
            case R.id.action_setting:

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener
    {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id)
        {
            selectItem(position);
        }
    }

    private void selectItem(int position)
    {
        FragmentTransaction mf = getFragmentManager().beginTransaction();

        switch(position)
        {
            case 0:
                Fragment fragment_News = new NewsFragment();
                mf.replace(R.id.content_frame,fragment_News);
                mf.commit();
                break;
            case 1:
                Fragment fragment_International = new InternationalFragment();
                mf.replace(R.id.content_frame,fragment_International);
                mf.commit();
                break;
            case 2:
                Fragment fragment_Economic = new EconomicFragment();
                mf.replace(R.id.content_frame,fragment_Economic);
                mf.commit();
                break;
            case 3:
                Fragment fragment_Science = new ScienceFragment();
                mf.replace(R.id.content_frame, fragment_Science);
                mf.commit();
                break;
            case 4:
                Fragment fragment_Sport = new SportFragment();
                mf.replace(R.id.content_frame, fragment_Sport);
                mf.commit();
                break;
            case 5:
                Fragment fragment_Entertainment = new EntertainmentFragment();
                mf.replace(R.id.content_frame, fragment_Entertainment);
                mf.commit();
                break;
        }

        mainDrawerList.setItemChecked(position, true);
        setTitle(sectionTitle[position]);
        mainDrawerLayout.closeDrawer(mainDrawerList);
    }

    @Override
    public void setTitle(CharSequence title)
    {
        mTitle = title;
        getActionBar().setTitle(mTitle);
    }
    @Override
    protected void onPostCreate(Bundle savedInstanceState)
    {
        super.onPostCreate(savedInstanceState);
        mainDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mainDrawerToggle.onConfigurationChanged(newConfig);
    }

}
