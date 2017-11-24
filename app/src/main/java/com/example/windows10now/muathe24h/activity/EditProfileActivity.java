package com.example.windows10now.muathe24h.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.example.windows10now.muathe24h.R;

/**
 * Created by Windows 10 Now on 11/23/2017.
 */

public class EditProfileActivity extends BaseActivity implements View.OnClickListener {
    private LinearLayout llPhone;
    private LinearLayout llEmail;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_edit_profille;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        initHeader();
        llPhone = findViewById(R.id.ll_edit_profile_phone);
        llEmail = findViewById(R.id.ll_edit_profile_email);
    }

    @Override
    protected void initVariable(Bundle savedInstanceState) {
        llEmail.setOnClickListener(this);
        llPhone.setOnClickListener(this);
        setTitleActionBar("Sửa thông tin");
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isLogin()) {
            showCheckButton(mOnProfileClickListener);
        } else {
            showActionRight(mOnLoginClickListener, getString(R.string.login));
        }
    }

    private View.OnClickListener mOnLoginClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(EditProfileActivity.this, LoginActivity.class));
        }
    };
    private View.OnClickListener mOnProfileClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(EditProfileActivity.this, HomeActivity.class));
        }
    };

    private void creatDialog(String sTittle,String sTextView) {
        LayoutInflater layoutInflater = getLayoutInflater();
        View dialog = layoutInflater.inflate(R.layout.dialog_edit_profile, null, false);
        EditText edtDialog = dialog.findViewById(R.id.edt_result_dialog);
        TextView txtDialog = dialog.findViewById(R.id.txt_title_dialog);
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle(sTittle);
        txtDialog.setText(sTextView);
        alert.setView(dialog);
        alert.setCancelable(false);
        alert.setNegativeButton("Quay lại", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getBaseContext(), "Cancel clicked", Toast.LENGTH_SHORT).show();
            }
        });

        alert.setPositiveButton("Thay đổi", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // code for matching password
            }
        });
        AlertDialog dialog1 = alert.create();
        dialog1.show();
        //        Dialog alertDialog = new Dialog(this);
        //        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //        alertDialog.setContentView(R.layout.dialog_edit_profile);
        //        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //        alertDialog.setTitle(sTittle);
        //        alertDialog.show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_edit_profile_phone:
                creatDialog("Nhập số điện thoại","Số điện thoại");
                break;
            case R.id.ll_edit_profile_email:
                creatDialog("Nhập email","Email");
                break;
        }
    }
}
