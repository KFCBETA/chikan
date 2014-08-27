package com.KFCBETA.hjeaimreus.chikan;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import fragment.EconomicFragment;
import fragment.EntertainmentFragment;
import fragment.InternationalFragment;
import fragment.NewsFragment;
import fragment.ScienceFragment;
import fragment.SportFragment;


public class DrawerActivity extends Activity {
    DataBaseHelper dataBaseHelper;
    @Override
    protected void onDestroy() {
        super.onDestroy();
        dataBaseHelper = new DataBaseHelper(this);
        dataBaseHelper.deleteTable();
    }

    private CharSequence mainTitle;
    private CharSequence mainDrawerTitle;

    private String[] SectionTitle;
    private int[] SectionIcon = new int[] {R.drawable.ic_action_accept, R.drawable.ic_action_accept, R.drawable.ic_action_accept, R.drawable.ic_action_accept,
            R.drawable.ic_action_accept, R.drawable.ic_action_accept};
    private String[] SectionCount = new String[] {"10", "2", "3", "4", "5", "6"};

    private DrawerLayout mainDrawerLayout;
    private ListView mainListView;
    private ActionBarDrawerToggle mainDrawertoggle;
    private LinearLayout mainDrawer;
    private List<HashMap<String, String>> mainList;
    private SimpleAdapter mainAdapter;

    final private String SECTIONICON = "icon";
    final private String SECTIONTITLE = "title";
    final private String SECTIONCOUNT = "count";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);

        SectionTitle = getResources().getStringArray(R.array.section_name);
        mainTitle = getTitle();
        mainListView = (ListView)findViewById(R.id.drawer_list);

        mainDrawer = (LinearLayout)findViewById(R.id.drawer);

        mainList = new ArrayList<HashMap<String, String>>();
        for(int i=0;i<6;i++)
        {
            HashMap<String, String> tmp_map = new HashMap<String, String>();
            tmp_map.put(SECTIONICON, Integer.toString(SectionIcon[i]));
            tmp_map.put(SECTIONTITLE, SectionTitle[i]);
            tmp_map.put(SECTIONCOUNT, SectionCount[i]);
            mainList.add(tmp_map);
        }
        String[] from = {SECTIONICON, SECTIONTITLE, SECTIONCOUNT};
        int[] to = {R.id.sectionicon , R.id.sectiontitle, R.id.sectioncount};
        mainDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);

        mainAdapter = new SimpleAdapter(this,mainList,R.layout.drawer_layout,from,to);

        mainDrawertoggle = new ActionBarDrawerToggle(this,mainDrawerLayout,
                R.drawable.ic_drawer,
                R.string.drawer_open,
                R.string.drawer_close){

            public void onDrawerClosed(View view)
            {
                getActionBar().setTitle(mainTitle);
                invalidateOptionsMenu();
            }
            public void onDrawerOpen(View view)
            {
                getActionBar().setTitle(mainDrawerTitle);
                invalidateOptionsMenu();
            }
        };

        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        mainDrawerLayout.setDrawerListener(mainDrawertoggle);
        mainListView.setOnItemClickListener(new DrawerItemClickListener());

        mainListView.setAdapter(mainAdapter);

        if(savedInstanceState == null)
        {
            selectItem(0);
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

        mainListView.setItemChecked(position, true);
        setTitle(SectionTitle[position]);
        mainDrawerLayout.closeDrawer(mainDrawer);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //action when the action bar is clicked
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(mainDrawertoggle.onOptionsItemSelected(item))
        {
            return true;
        }

        switch(item.getItemId())
        {
            case R.id.setting:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }



    public void setTitle(CharSequence title)
    {
        mainTitle = title;
        getActionBar().setTitle(mainTitle);
    }
    @Override
    protected void onPostCreate(Bundle savedInstanceState)
    {
        super.onPostCreate(savedInstanceState);
        mainDrawertoggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mainDrawertoggle.onConfigurationChanged(newConfig);
    }

}
