package com.example.windows10now.muathe24h.configretrofit;

import com.example.windows10now.muathe24h.model.RegisterMDResult;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Windows 10 Now on 11/16/2017.
 */

public interface InterfaceAPI {
    //api login
    @POST("v2/PayCard/LoginV2")
    Call<String> login(@Query("userName") String username, @Query("password") String password);

    //api register
    @POST("v2/PayCard/Register")
    Call<RegisterMDResult> register(@Query("fName") String username, @Query("phone") String phone,
            @Query("email") String email, @Query("passwd") String passwd);

    //api login with gmail
    @POST("v2/PayCard/DangNhapGoogle")
    Call<RegisterMDResult> loginWithGoogle(@Query("Token") String Token,
            @Query("FullName") String FullName, @Query("PassWord") String PassWord);
}
