package com.example.windows10now.muathe24h.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.example.windows10now.muathe24h.R;
import com.example.windows10now.muathe24h.configretrofit.ConfigRetrofitApi;
import com.example.windows10now.muathe24h.configretrofit.InterfaceAPI;
import com.example.windows10now.muathe24h.model.JsonResult;
import com.example.windows10now.muathe24h.model.RegisterMDResult;
import com.example.windows10now.muathe24h.model.RegisterModel;
import com.example.windows10now.muathe24h.util.JsonUtil;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Windows 10 Now on 11/18/2017.
 */

public class RegisterActivity extends BaseActivity implements View.OnClickListener{
    private static final int RC_SIGN_IN = 0;
    private EditText edtUser;
    private EditText edtPhone;
    private EditText edtEmail;
    private EditText edtPassReg;
    private EditText edtPassRegAgain;
    private Button btnRegister;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        initHeader();
        edtUser = findViewById(R.id.edtUser);
        edtPhone = findViewById(R.id.edtPhone);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassReg = findViewById(R.id.edtPassReg);
        edtPassRegAgain = findViewById(R.id.edtpassRegAgain);
        btnRegister = findViewById(R.id.btnRegisterReg);
        btnRegister.setOnClickListener(this);
        imgBack.setOnClickListener(this);
    }

    @Override
    protected void initVariable(Bundle savedInstanceState) {

    }

    private void checkRegister() {
        String user = edtUser.getText().toString();
        String phone = edtPhone.getText().toString();
        String email = edtEmail.getText().toString();
        String pass = edtPassReg.getText().toString();
        String passAgain = edtPassRegAgain.getText().toString();
        if (user.equals("") || user == null) {
            edtUser.setError("Vui lòng nhập họ tên");
        } else if (phone.equals("") || phone == null) {
            edtPhone.setError("Vui lòng nhập số điện thoại");
        } else if (!validEmail(email)) {
            edtEmail.setError("Vui lòng nhập đúng định dạng email");
        } else if (pass.equals("") || pass == null) {
            edtPassReg.setError("Vui lòng nhập password");
        } else if (passAgain.equals("") || passAgain == null) {
            edtPassRegAgain.setError("Vui lòng nhập lại password");
        } else if (!passAgain.equals(pass)) {
            Toast.makeText(RegisterActivity.this, "Mật khẩu nhập lại không khớp", Toast.LENGTH_LONG)
                    .show();
        } else {
            RegisterModel registerModel = new RegisterModel(user, phone, email, pass);
            registerRetrofit(registerModel);
        }
    }

    private boolean validEmail(String email) {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (email.matches(emailPattern)) {
            return true;
        } else {
            return false;
        }
    }

    private void registerRetrofit(RegisterModel registerModel) {
        Retrofit retrofit = ConfigRetrofitApi.getInstance();
        //
        retrofit.create(InterfaceAPI.class)
                .register(registerModel.getUsername(), registerModel.getPhoneNumber(),
                        registerModel.getEmail(), registerModel.getPass())
                .enqueue(new Callback<RegisterMDResult>() {
                    @Override
                    public void onResponse(Call<RegisterMDResult> call,
                            Response<RegisterMDResult> response) {
                        Log.e("RegisterMDResult ",response.body().toString());
                    }

                    @Override
                    public void onFailure(Call<RegisterMDResult> call, Throwable t) {

                    }
                });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnRegisterReg :
                checkRegister();
                break;
            case R.id.imgBack :
                finish();
                break;
        }
    }
}
