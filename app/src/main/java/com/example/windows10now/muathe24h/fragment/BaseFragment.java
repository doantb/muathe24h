package com.example.windows10now.muathe24h.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.text.ParseException;

/**
 * Created by Windows 10 Now on 11/13/2017.
 */

public abstract class BaseFragment extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mRoot = inflater.inflate(layoutId(), container, false);
        try {
            init(mRoot, savedInstanceState);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return mRoot;
    }

    protected abstract int layoutId();

    protected abstract void init(View mRoot, Bundle savedInstanceState) throws ParseException;
}
