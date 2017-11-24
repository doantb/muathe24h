package com.example.windows10now.muathe24h.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import com.example.windows10now.muathe24h.R;
import com.example.windows10now.muathe24h.util.Constant;
import com.vansuita.gaussianblur.GaussianBlur;

/**
 * Created by Windows 10 Now on 11/22/2017.
 */

public class ProfileUserActivity extends BaseActivity {
    private String token;
    private ImageView bgHeader;

    @Override
    protected void onResume() {
        super.onResume();
        if (isLogin()) {
            showEditButton(mOnProfileClickListener);
        } else {
            showActionRight(mOnLoginClickListener, getString(R.string.login));
        }
    }

    private View.OnClickListener mOnLoginClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(ProfileUserActivity.this, LoginActivity.class));
        }
    };
    private View.OnClickListener mOnProfileClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(token);
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.activity_profile_user;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        initHeader();
        bgHeader = findViewById(R.id.bg_header);
    }

    @Override
    protected void initVariable(Bundle savedInstanceState) {
        showEditButton(mOnProfileClickListener);
        Intent intent = getIntent();
        token = intent.getStringExtra(Constant.ACCESS_TOKEN);
        setTitleActionBar("Thông tin tài khoản");
    }

    private void startActivity(String token) {
        Intent intent = new Intent(ProfileUserActivity.this, EditProfileActivity.class);
        intent.putExtra(Constant.ACCESS_TOKEN, token);
        startActivity(intent);
    }

    private void setBackgroundBlur() {
        Bitmap blurredBitmap = GaussianBlur.with(this)
                .radius(25)
                .render(R.drawable.bg1);
        bgHeader.setImageBitmap(blurredBitmap);
    }
}
