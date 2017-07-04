package com.example.karthik.justsearch;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;
     ViewPager viewPager;
    ViewGroup containerAppBar;
    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
        containerAppBar= (ViewGroup) findViewById(R.id.appBarcontainer);
        setSupportActionBar(toolbar);
        AnimationUtil.animateToolbar(containerAppBar);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(new MyFragment(getSupportFragmentManager()));
         tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("Home"));
        tabLayout.addTab(tabLayout.newTab().setText("Virudhunagar"));
        tabLayout.addTab(tabLayout.newTab().setText("Sivakasi"));
        tabLayout.addTab(tabLayout.newTab().setText("Thiruthangal"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        final HorizontalScrollView horizontalScrollView= (HorizontalScrollView) findViewById(R.id.horzitonal);
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorAccent));

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
               if(tabLayout.getSelectedTabPosition()==2)
               {
                   horizontalScrollView.fullScroll(HorizontalScrollView.FOCUS_RIGHT);
               }
                if(tabLayout.getSelectedTabPosition()==1){
                    horizontalScrollView.fullScroll(HorizontalScrollView.FOCUS_LEFT);
                }

                viewPager.setCurrentItem(tabLayout.getSelectedTabPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        }



    public class MyFragment extends FragmentPagerAdapter {
        MyFragment(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment f = null;
            if (position == 0) {
                f = new FragmentH();
            }
            if (position == 1) {
                f = new FragmentV();
            }
            if (position == 2) {
                f = new FragmentS();
            }
            if (position == 3) {
                f = new FragmentT();
            }

            return f;

        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if (position == 0) {
                return "Home";
            }
            if (position == 1) {
                return "Virudhunagar";
            }
            if (position == 2) {
                return "Sivakasi";
            }
            if (position == 3) {
                return "Thiruthangal";
            }


            return null;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.about)
        {

            Intent i=new Intent(MainActivity.this,AddressDisplayActivity.class);
            i.putExtra("s",true);
            startActivity(i);

        }
        return super.onOptionsItemSelected(item);
    }


}

