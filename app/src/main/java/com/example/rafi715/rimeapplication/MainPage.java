package com.example.rafi715.rimeapplication;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rafi715.rimeapplication.Calling.FragmentContact;
import com.example.rafi715.rimeapplication.Calling.PlaceCallActivity;
import com.example.rafi715.rimeapplication.Tabs.SlidingTabLayout;

import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabListener;

public class MainPage extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, MaterialTabListener {
    private ViewPager mPager;
    private SlidingTabLayout mTabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mPager= (ViewPager) findViewById(R.id.viewPager);
        mPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        mTabs= (SlidingTabLayout) findViewById(R.id.tabs);
        mTabs.setDistributeEvenly(true);
        mTabs.setCustomTabView(R.layout.custom_tab_view, R.id.tabText);
        mTabs.setViewPager(mPager);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Intent HomeNav=new Intent(this, NavHome.class);
            startActivity(HomeNav);
        }
        else if (id == R.id.nav_profile) {
            Intent ProfileNav=new Intent(this, NavProfile.class);
            startActivity(ProfileNav);
        }
        else if (id == R.id.nav_invitefriend) {
            Intent InviteFriendNav=new Intent(this, NavInviteFriend.class);
            startActivity(InviteFriendNav);
        }
        else if (id == R.id.nav_blacklist) {
            Intent BlackFriendNav=new Intent(this, NavBlackList.class);
            startActivity(BlackFriendNav);
        }
        else if (id == R.id.nav_mood) {
            Intent MoodNav=new Intent(this, NavMood.class);
            startActivity(MoodNav);
        }
        else if (id == R.id.nav_theme) {
            Intent ThemeNav=new Intent(this, NavTheme.class);
            startActivity(ThemeNav);
        }
        else if (id == R.id.nav_privacy) {
            Intent PrivacyNav=new Intent(this, NavPrivacy.class);
            startActivity(PrivacyNav);
        }
        else if (id == R.id.nav_setting) {
            Intent SettingNav=new Intent(this, NavSetting.class);
            startActivity(SettingNav);
        }
        else if (id == R.id.nav_logout) {
            Intent LogoutNav=new Intent(this, Login.class);
            startActivity(LogoutNav);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onTabSelected(MaterialTab tab) {

    }

    @Override
    public void onTabReselected(MaterialTab tab) {

    }

    @Override
    public void onTabUnselected(MaterialTab tab) {

    }

    public void onPlaceCallClick(View view) {
        Intent i = new Intent(getApplicationContext(), PlaceCallActivity.class);
        startActivity(i);
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {

        int icon[]={R.drawable.phone, R.drawable.message, R.drawable.contact, R.drawable.group};
        String[] tabText=getResources().getStringArray(R.array.tabs);

        public MyPagerAdapter(android.support.v4.app.FragmentManager fm) {
            super(fm);
            tabText=getResources().getStringArray(R.array.tabs);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int num) {
            switch (num){
                case 0:
                    return new FragmentPhone();
                case 1:
                    return new FragmentMessage();
                case 2:
                    return new FragmentContact();
                case 3:
                    return new FragmentGroup();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Drawable drawable=getResources().getDrawable(icon[position]);
            drawable.setBounds(0,0, 48,48);
            ImageSpan imageSpan=new ImageSpan(drawable);
            SpannableString spannableString=new SpannableString(" ");
            spannableString.setSpan(imageSpan, 0, spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            return spannableString;
        }
    }

    public static class MyFragment extends Fragment {
        private TextView textView;

        public static MyFragment getInstance(int position) {
            MyFragment myFragment = new MyFragment();
            Bundle args = new Bundle();
            args.getInt("Position ", position);
            myFragment.setArguments(args);
            return myFragment;
        }

        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View layout = inflater.inflate(R.layout.fragment_my, container, false);
            textView = (TextView) layout.findViewById(R.id.position);
            Bundle bundle = getArguments();
            if (bundle != null) {
                textView.setText("The page selected is " + bundle.getInt("position"));
            }
            return layout;
        }
    }
}
