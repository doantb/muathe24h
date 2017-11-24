package com.example.windows10now.muathe24h.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.example.windows10now.muathe24h.fragment.ChangerCardFragment;

/**
 * Created by Windows 10 Now on 11/13/2017.
 */

public class ChangeCardAdapter extends FragmentStatePagerAdapter {
    public ChangeCardAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return ChangerCardFragment.newInstance();
            case 1:
                return ChangerCardFragment.newInstance();
            case 2:
                return ChangerCardFragment.newInstance();
            case 3:
                return ChangerCardFragment.newInstance();
            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position) {
            case 0:
                title = "Mua nhiều thẻ";
                break;
            case 1:
                title = "Nạp tiền điện thoại nhanh";
                break;
            case 2:
                title = "Nạp thẻ Viettel chậm";
                break;
            case 3:
                title = "Nạp thẻ Viettel";
                break;
        }
        return title;
    }
}
