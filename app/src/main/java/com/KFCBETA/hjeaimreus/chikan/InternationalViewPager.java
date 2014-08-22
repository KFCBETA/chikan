package com.KFCBETA.hjeaimreus.chikan;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


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

            switch(position)
            {
                case 0:
                    return "Russian and Ukraine";
                case 1:
                    return "Egypt tourist buses";
                default:
                    return "" + (position+1);
            }
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

            int chooice = args.getInt(index);

            switch(chooice)
            {
                case 1:
                    ((TextView)view_viewpager.findViewById(R.id.international_title)).setText("Russian aid convoy into Ukraine called 'direct invasion'");
                    ((TextView)view_viewpager.findViewById(R.id.international_article)).setText("Kiev, Ukraine (CNN) -- A Ukrainian official accused Russia of directly invading the country under the guise of humanitarian aid Friday, after Ukraine's military said that part of a Russian aid convoy violated an deal by entering Ukraine without Red Cross monitors.\n" +
                            "\n" +
                            "Russia sent 34 trucks from an aid convoy into eastern Ukraine after Russian and Ukrainian customs officials cleared them under the assumption that the International Committee of the Red Cross would be with them, the Ukrainian military said.\n" +
                            "\n" +
                            "But the Red Cross said it was no longer with the convoy because of the \"volatile security situation,\" a reference to fighting between pro-Russian rebels and Ukrainian forces.\n" +
                            "\n" +
                            "The unaccompanied trucks effectively constitutes a Russian invasion of Ukraine, said Valentyn Nalyvaychenko, the head of Ukraine's security service.\n" +
                            "\n" +
                            "\"We call this a direct invasion for the first time under cynical cover of the Red Cross,\" Nalyvaychenko said Friday.\n" +
                            "\n" +
                            "The news was the latest flashpoint in tensions between Moscow and Kiev, which for months has accused Russia of sending supplies into Ukraine to support pro-Russian rebels in Ukraine's east.\n" +
                            "Photos: Crisis in Ukraine Photos: Crisis in Ukraine\n" +
                            "Army: Civilian convoy attacked in Ukraine\n" +
                            "Ukrainian refugees flee to Russia\n" +
                            "\n" +
                            "Ukraine, as of now, doesn't plan to use force against the convoy, though Kiev suspects that the trucks' supplies will be given to rebels, Nalyvaychenko said.\n" +
                            "\n" +
                            "Meanwhile, another 90 vehicles are headed toward the Ukrainian border, according to Col. Andriy Lysenko, spokesman for Ukraine's National Defense and Security Council. Ukrainian border guards, customs officers and Red Cross representatives have not been given access to that group, Lysenko said.\n" +
                            "\n" +
                            "He said that the Ukrainian side had proposed talks with Russia over the humanitarian aid but that Moscow had refused.\n" +
                            "\n" +
                            "The trucks were part of a larger convoy that left the Moscow area last week, with Russia insisting that it be allowed to send aid to civilians -- many of them Russian speakers -- affected by the months of fighting in eastern Ukraine. Aid groups say the battles have left thousands without access to water, electricity and proper medical aid.\n" +
                            "\n" +
                            "Ukraine, concerned that Russia might try to smuggle military supplies, stalled the trucks for days on the Russian side of the border.\n" +
                            "\n" +
                            "But Ukrainian officials acknowledged Sunday that the convoy of more than 260 Russian vehicles was, in fact, carrying humanitarian aid.\n" +
                            "\n" +
                            "Red Cross monitors were supposed to accompany the initial 34 vehicles but decided not to do so because they did not get the security guarantees they needed, the agency said.\n" +
                            "\n" +
                            "Russia's Foreign Ministry said the first group of trucks was headed toward Luhansk, one of two Ukrainian regions at the center of the conflict.\n" +
                            "\n" +
                            "Russian state news agency Itar-Tass said the initial convoy carried food and essential items for people in the region.\n" +
                            "\n" +
                            "Ukraine's National Security and Defense Council said Friday that the customs service had granted access for 34 vehicles, 34 people and 268,020 kilograms of Russian humanitarian aid.\n" +
                            "\n" +
                            "In a statement Friday, Russia's Foreign Ministry accused Ukraine of using pretexts in the days since to hold up the movement of the convoy while escalating its attacks on pro-Russia rebels in the Donetsk and Luhansk regions.\n" +
                            "\n" +
                            "The ministry claimed that Ukrainian leaders were deliberately delaying the delivery of the humanitarian aid until there was no one left to deliver it to.\n" +
                            "\n" +
                            "It said that it was ready for Red Cross staffers to accompany the Russian convoy and help with the distribution of aid.\n" +
                            "\n" +
                            "Still, Ukraine's government has suspicions. Nalyvaychenko said he believes the neither the trucks nor the drivers are civilian. The drivers, he said, received Russian passports only two weeks ago -- days before Moscow started sending the convoy to the border.\n" +
                            "\n" +
                            "The ongoing fighting -- sparked last year by a political crisis over whether Ukraine would seek closer ties with Europe or Russia -- has left more than 2,000 people dead and nearly 5,000 wounded in eastern Ukraine since mid-April, according to estimates from U.N. officials.\n" +
                            "\n" +
                            "Four Ukrainian soldiers have been killed over the past 24 hours, Ukraine's National Security and Defense Council said Friday.");
                    break;
                case 2:
                    ((TextView)view_viewpager.findViewById(R.id.international_title)).setText("Report: At least 38 killed after tourist buses crash in Egypt");
                    ((TextView)view_viewpager.findViewById(R.id.international_article)).setText("(CNN) -- At least 38 people were killed and 41 injured early Friday when two tourist buses crashed in northeastern Egypt, the country's semi-official news website Al-Ahram Online reported.\n" +
                            "\n" +
                            "The two buses in the governorate of South Sinai were carrying an estimated 80 passengers, including 4 foreign tourists, the report said.\n" +
                            "\n" +
                            "Search-and-rescue teams are still working to recover casualties at the scene, said Mohamed Lasheen, the undersecretary of the Ministry of Health in South Sinai.");
                default:
                    break;
            }
            return view_viewpager;
        }
    }
}
