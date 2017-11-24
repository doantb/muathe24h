package com.example.windows10now.muathe24h.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.example.windows10now.muathe24h.R;
import com.example.windows10now.muathe24h.configretrofit.ConfigGG;
import com.example.windows10now.muathe24h.configretrofit.ConfigRetrofitApi;
import com.example.windows10now.muathe24h.configretrofit.InterfaceAPI;
import com.example.windows10now.muathe24h.model.RegisterMDResult;
import com.example.windows10now.muathe24h.model.UserInfo;
import com.example.windows10now.muathe24h.networking.TaskGetGG;
import com.example.windows10now.muathe24h.util.BlurBuilder;
import com.example.windows10now.muathe24h.util.Constant;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.util.ArrayList;
import jp.wasabeef.blurry.Blurry;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.windows10now.muathe24h.networking.TaskGetGG.*;
import static com.example.windows10now.muathe24h.util.Constant.KEY_TOKEN;
import static com.example.windows10now.muathe24h.util.Constant.USER_INFO;

/**
 * Created by Windows 10 Now on 11/13/2017.
 */

public class LoginActivity extends BaseActivity
        implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {
    private EditText edtUser;
    private EditText edtPass;
    private Button btnLogin;
    private TextView txtForgotPass;
    private LinearLayout llRegisterNow;
    private RelativeLayout rlBgMain;
    private RegisterMDResult jsonResult;
    private ImageView imgLoginGG;
    private ImageView imgLoginFB;
    private GoogleApiClient mGoogleApiClient;
    private TaskGetGG mTaskGetGG;
    private static final int RC_SIGN_IN = 2017;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected int getLayoutId() {
        setStatusBarTranslucent(true);
        return R.layout.acitivity_login;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences =
                getSharedPreferences(Constant.MuaThe24h_SHARED_PREFRENCES, Context.MODE_PRIVATE);
        String token = sharedPreferences.getString(Constant.ACCESS_TOKEN, "");
        if (!token.equalsIgnoreCase("")) {
            Intent intent = new Intent(this, HomeActivity.class);
            intent.putExtra(KEY_TOKEN, token);
            startActivity(intent);
        }
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        edtUser = (EditText) findViewById(R.id.edt_user_name);
        edtPass = (EditText) findViewById(R.id.edt_password);
        btnLogin = (Button) findViewById(R.id.btn_login);
        txtForgotPass = (TextView) findViewById(R.id.txt_login_forget_pass);
        llRegisterNow = findViewById(R.id.ll_register_now);
        rlBgMain = findViewById(R.id.bg_main_login);
        imgLoginGG = findViewById(R.id.img_login_gg);
        imgLoginFB = findViewById(R.id.img_login_fb);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void initVariable(Bundle savedInstanceState) {
        Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bg_main);
        Bitmap blurredBitmap = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR1) {
            blurredBitmap = BlurBuilder.blur(this, originalBitmap);
        }
        rlBgMain.setBackground(new BitmapDrawable(getResources(), blurredBitmap));
        btnLogin.setOnClickListener(this);
        txtForgotPass.setOnClickListener(this);
        llRegisterNow.setOnClickListener(this);
        imgLoginGG.setOnClickListener(this);
    }

    protected void setStatusBarTranslucent(boolean makeTranslucent) {
        if (makeTranslucent) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        } else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    private void loginRetrofit(String email, String password) {
        Retrofit retrofit = ConfigRetrofitApi.getInstance();
        //
        retrofit.create(InterfaceAPI.class).login(email, password).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response != null) {
                    String token = response.body();
                    Log.e("token = ", token);
                    if (!token.equals("")) startActivity(HomeActivity.class);
                } else {
                    showShortToast("null");
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                if (checkCondition()) {
                    loginRetrofit(edtUser.getText().toString(), edtPass.getText().toString());
                }
                break;
            case R.id.ll_register_now:
                startActivity(RegisterActivity.class);
                break;
            case R.id.txt_login_forget_pass:
                break;
            case R.id.img_login_gg:
                mGoogleApiClient = getmGoogleApiClient(this);
                signIn(mGoogleApiClient, this);
                break;
        }
    }

    private void loginWithGG(final String token, String fullName, String passWord, String email) {
        Retrofit retrofit = ConfigRetrofitApi.getInstance();
        //
        Log.d("token", token + "");
        Log.d("token", email + "");
        Log.d("token", passWord + "");
        Log.d("token", fullName + "");
        retrofit.create(InterfaceAPI.class)
                .loginWithGoogle(token, fullName, passWord)
                .enqueue(new Callback<RegisterMDResult>() {
                    @Override
                    public void onResponse(Call<RegisterMDResult> call,
                            Response<RegisterMDResult> response) {
                        jsonResult = response.body();
                        Toast.makeText(LoginActivity.this, jsonResult.getRepcode() + "",
                                Toast.LENGTH_SHORT).show();
                        chooseRepCode(token);
                    }

                    @Override
                    public void onFailure(Call<RegisterMDResult> call, Throwable t) {

                    }
                });
    }

    private boolean checkCondition() {
        if (edtUser.getText().toString().equalsIgnoreCase("")) {
            showShortToast(R.string.toast_must_enter_user_name);
            return false;
        } else if (edtPass.getText().toString().equalsIgnoreCase("")) {
            showShortToast(R.string.toast_must_enter_password);
            return false;
        }
        return true;
    }

    public static void startActivity(Context context, UserInfo userInfos) {
        Intent intent = new Intent(context, LoginActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(USER_INFO, userInfos);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    private void ChangeFonts() {
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Gabriola.ttf");
        txtForgotPass.setTypeface(typeface);
    }

    private void chooseRepCode(String token) {
        switch (jsonResult.getRepcode()) {
            case 1:
                Intent intent = new Intent(this, HomeActivity.class);
                intent.putExtra(KEY_TOKEN, token);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        handleSignInResult(requestCode, data, this);
        Log.e("onActivityResult", "co vao day");
    }

    private void signIn(GoogleApiClient mGoogleApiClient,
            GoogleApiClient.OnConnectionFailedListener failedListener) {
        if (mGoogleApiClient == null) {
            getmGoogleApiClient(failedListener);
        }
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
        Log.e("signIn", "co vao day");
    }

    private GoogleApiClient getmGoogleApiClient(
            GoogleApiClient.OnConnectionFailedListener failedListener) {
        GoogleSignInOptions gso =
                new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(
                        getString(R.string.default_web_client_id)).requestEmail().build();
        return new GoogleApiClient.Builder(this).enableAutoManage(this /* Activity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
    }

    public void signOut(GoogleApiClient mGoogleApiClient, BaseActivity activity,
            GoogleApiClient.OnConnectionFailedListener failedListener) {
        if (mGoogleApiClient == null) {
            getmGoogleApiClient(failedListener);
        }
        Auth.GoogleSignInApi.signOut(mGoogleApiClient)
                .setResultCallback(new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        Log.e("status", status.getStatusMessage() + " " + status.toString());
                    }
                });
    }

    private void handleSignInResult(int requestCode, Intent data, Context context) {
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            final GoogleSignInAccount acct = result.getSignInAccount();

            if (result.isSuccess()) {
                mTaskGetGG = new TaskGetGG(context) {
                    @Override
                    protected void onPostExecute(String token) {
                        super.onPostExecute(token);
                        loginWithGG(token, acct.getDisplayName(), "", acct.getEmail());
                        saveAccessToken(token);
                        //                        Log.d("token",token);
                        //                        Log.d("token",acct.getDisplayName());
                    }
                };
                //                mTaskGetGG.setLoginGgResultInterface(this);
                mTaskGetGG.execute(acct.getEmail());
            }
        }
    }

    //    protected void saveAccessToken(String token) {
    //        editor.putString(Constant.ACCESS_TOKEN, token);
    //        editor.apply();
    //    }
    //
    //    protected void removeAccessToken() {
    //        editor.putString(Constant.ACCESS_TOKEN, "");
    //        editor.apply();
    //    }
}
