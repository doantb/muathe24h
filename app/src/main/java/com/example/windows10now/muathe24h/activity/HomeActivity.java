package com.example.windows10now.muathe24h.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.example.windows10now.muathe24h.R;
import com.example.windows10now.muathe24h.fragment.ChangerCardFragment;
import com.example.windows10now.muathe24h.fragment.HomeFragment;
import com.example.windows10now.muathe24h.util.Constant;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    private DrawerLayout mDrawer;
    private NavigationView mNavigationView;
    private View mHeaderViewNav;
    private ActionBarDrawerToggle drawerToggle;
    private Toolbar toolbar;
    private FragmentManager fragmentManager;
    private String token;
    private LinearLayout llHeaderNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        token = intent.getStringExtra(Constant.KEY_TOKEN);
        setContentView(R.layout.activity_home);
        initView();
        fragmentManager = getSupportFragmentManager();
        drawerToggle = setupDrawerToggle();
        setSupportActionBar(toolbar);

        // Tie DrawerLayout events to the ActionBarToggle
        mDrawer.addDrawerListener(drawerToggle);
        setupDrawerContent(mNavigationView);
        getChangeCardFragment();
        llHeaderNav.setOnClickListener(this);
    }
    private void getChangeCardFragment(){
        ChangerCardFragment changerCard = new ChangerCardFragment();
        fragmentManager.beginTransaction().replace(R.id.flContent,changerCard,ChangerCardFragment.class.getName()).commit();
    }

    private void initView() {
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        mNavigationView = (NavigationView) findViewById(R.id.nvView);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        mHeaderViewNav = mNavigationView.getHeaderView(0);
        llHeaderNav = mHeaderViewNav.findViewById(R.id.ll_nav_header_view);
    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        // NOTE: Make sure you pass in a valid toolbar reference.  ActionBarDrawToggle() does not require it
        // and will not render the hamburger icon without it.
        return new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.drawer_open,
                R.string.drawer_close);
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // The action bar home/up action should open or close the drawer.
//        switch (item.getItemId()) {
//            case android.R.id.home:
//                mDrawer.openDrawer(GravityCompat.START);
//                return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        drawerToggle.onConfigurationChanged(newConfig);
    }

    public void selectDrawerItem(MenuItem menuItem) {
        // Create a new fragment and specify the fragment to show based on nav item clicked
        Fragment fragment = null;
        Class fragmentClass;
        switch (menuItem.getItemId()) {
            case R.id.nav_home_fragment:
                fragmentClass = HomeFragment.class;
                break;
            case R.id.nav_discount_fragment:
                fragmentClass = HomeFragment.class;
                break;
            case R.id.nav_info_bank_fragment:
                fragmentClass = HomeFragment.class;
                break;
            case R.id.nav_contact_fragment:
                fragmentClass = HomeFragment.class;
                break;
            case R.id.nav_logout_fragment:
                fragmentClass = HomeFragment.class;
                break;
            default:
                fragmentClass = HomeFragment.class;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Insert the fragment by replacing any existing fragment
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();

        // Highlight the selected item has been done by NavigationView
        menuItem.setChecked(true);
        // Set action bar title
        setTitle(menuItem.getTitle());
        // Close the navigation drawer
        mDrawer.closeDrawers();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ll_nav_header_view:
                Intent intent = new Intent(this,ProfileUserActivity.class);
                startActivity(intent);
                break;
        }
    }
}
