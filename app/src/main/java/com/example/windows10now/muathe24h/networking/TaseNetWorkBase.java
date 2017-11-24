package com.example.windows10now.muathe24h.networking;

import android.content.Context;
import android.content.SharedPreferences;
import com.example.windows10now.muathe24h.util.Constant;

/**
 * Created by Windows 10 Now on 11/22/2017.
 */

public abstract class TaseNetWorkBase<T extends Object> {
    private Context mContext;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private String session;

    public TaseNetWorkBase(Context mContext) {
        this.mContext = mContext;
        sharedPreferences = mContext.getSharedPreferences(Constant.MuaThe24h_SHARED_PREFRENCES,
                Context.MODE_PRIVATE);
        session = sharedPreferences.getString(Constant.ACCESS_TOKEN, "");
        editor = sharedPreferences.edit();
    }

    protected void saveAccessToken(String token) {
        editor.putString(Constant.ACCESS_TOKEN, token);
        editor.apply();
    }

    protected void removeAccessToken() {
        editor.putString(Constant.ACCESS_TOKEN, "");
        editor.apply();
    }
}
