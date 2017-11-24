package com.example.windows10now.muathe24h.util;

import android.content.Context;
import com.example.windows10now.muathe24h.model.JsonResult;
import com.example.windows10now.muathe24h.model.RegisterMDResult;
import com.google.gson.Gson;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Windows 10 Now on 11/18/2017.
 */

public class JsonUtil {
//    public static JsonResult getResult(String result) throws JSONException {
//        JSONObject jsonObject = new JSONObject(result);
//        int repCode = jsonObject.getInt("RepCode");
//        String message = jsonObject.getString("Message");
////        JSONArray jsonArray = jsonObject.getJSONArray("Data");
//        JsonResult jsonResult = new JsonResult();
//        jsonResult.setRepCode(repCode);
//        jsonResult.setMessage(message);
////        jsonResult.setList(jsonArray);
//        return jsonResult;
//    }
    public static JsonResult getResult(String jsonInString){
        Gson gson = new Gson();
//        String jsonInString = "{\"RepCode\":\"0\",\"Message\":\"OK\"}";
        JsonResult mJsonResult = gson.fromJson(jsonInString, JsonResult.class);
        return mJsonResult;
    }
}
