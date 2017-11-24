package com.example.windows10now.muathe24h.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import com.example.windows10now.muathe24h.R;
import com.example.windows10now.muathe24h.adapter.ChangeCardAdapter;
import java.text.ParseException;

/**
 * Created by Windows 10 Now on 11/13/2017.
 */

public class HomeFragment extends BaseFragment {
    private ViewPager vpHome;
    private ChangeCardAdapter mCardAdapter;
    private TabLayout mTabLayout;
    @Override
    protected int layoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void init(View mRoot, Bundle savedInstanceState) throws ParseException {
        vpHome = mRoot.findViewById(R.id.vp_home);
        mCardAdapter = new ChangeCardAdapter(getActivity().getSupportFragmentManager());
        mTabLayout = mRoot.findViewById(R.id.tab_layout);
        initVariables(mRoot,savedInstanceState);
    }
    private void initVariables(View mRoot, Bundle savedInstanceState) {
        vpHome.setAdapter(mCardAdapter);
        mTabLayout.setupWithViewPager(vpHome);
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        vpHome.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.setTabsFromPagerAdapter(mCardAdapter);
    }
}
