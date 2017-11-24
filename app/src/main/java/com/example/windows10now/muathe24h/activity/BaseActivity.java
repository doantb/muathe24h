package com.example.windows10now.muathe24h.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.example.windows10now.muathe24h.R;
import com.example.windows10now.muathe24h.util.Constant;

/**
 * Created by Windows 10 Now on 11/17/2017.
 */

public abstract class BaseActivity extends FragmentActivity {
    public ImageView imgBack;
    public ImageButton btnEdit;
    private ImageButton btnCheck;
    private TextView txtTitleActionBar;
    private TextView txtLoginActionBar;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(getLayoutId());
        initView(savedInstanceState);
        initVariable(savedInstanceState);
    }

    protected void initHeader() {
        imgBack = (ImageView) findViewById(R.id.imgBack);
        txtTitleActionBar = (TextView) findViewById(R.id.txt_title_action_bar);
        txtLoginActionBar = findViewById(R.id.txt_login_action_bar);
        btnEdit = findViewById(R.id.action_bar_btn_edit);
        btnCheck = findViewById(R.id.action_bar_btn_check);
        showBackButton();
        hideRightAction();
    }

    protected abstract int getLayoutId();

    protected abstract void initView(Bundle savedInstanceState);

    protected abstract void initVariable(Bundle savedInstanceState);

    protected boolean isLogin() {
        SharedPreferences sharedPreferences = getSharedPreferences();
        String token = sharedPreferences.getString(Constant.ACCESS_TOKEN, "");
        return !token.equalsIgnoreCase("");
    }

    public SharedPreferences getSharedPreferences() {
        return getSharedPreferences(Constant.MuaThe24h_SHARED_PREFRENCES, Context.MODE_PRIVATE);
    }

    protected void showShortToast(int errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }

    protected void showShortToast(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }

    protected void showBackButton() {
        imgBack.setVisibility(View.VISIBLE);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void hideRightAction() {
        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.action_bar_right);
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            viewGroup.getChildAt(i).setVisibility(View.GONE);
        }
    }

    protected void startActivity(Class<?> c) {
        Intent intent = new Intent(this, c);
        startActivity(intent);
    }

    protected void saveAccessToken(String token) {
        sharedPreferences =
                getSharedPreferences(Constant.MuaThe24h_SHARED_PREFRENCES, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putString(Constant.ACCESS_TOKEN, token);
        editor.apply();
    }

    protected void removeAccessToken() {
        sharedPreferences =
                getSharedPreferences(Constant.MuaThe24h_SHARED_PREFRENCES, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putString(Constant.ACCESS_TOKEN, "");
        editor.apply();
    }

    protected void showEditButton(View.OnClickListener onEditClickListener) {
        hideRightAction();
        btnEdit.setVisibility(View.VISIBLE);
        btnEdit.setOnClickListener(onEditClickListener);
    }

    protected void showCheckButton(View.OnClickListener onEditClickListener) {
        hideRightAction();
        btnCheck.setVisibility(View.VISIBLE);
        btnCheck.setOnClickListener(onEditClickListener);
    }

    protected void showActionRight(View.OnClickListener onLoginClickListener, String text) {
        hideRightAction();
        txtLoginActionBar.setText(text);
        txtLoginActionBar.setVisibility(View.VISIBLE);
        txtLoginActionBar.setOnClickListener(onLoginClickListener);
    }

    protected void setTitleActionBar(String sTitle) {
        txtTitleActionBar.setText(sTitle);
    }
}
