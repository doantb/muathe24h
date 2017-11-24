package com.example.windows10now.muathe24h.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Windows 10 Now on 11/18/2017.
 */

public class RegisterMDResult {
    @SerializedName("RepCode")//RepCode là thuộc tính trong json được trả về
    public int RepCode;//RepCode này là thuộc tính của APIResult,
    //khai bao như này là để nó map RepCode của json vào RepCode của object

    @SerializedName("Message")
    public String Message;


    @SerializedName("Data")
    public Object Data;

    public RegisterMDResult() {
    }

    public RegisterMDResult(int repCode, String message, Object data) {
        RepCode = repCode;
        Message = message;
        Data = data;
    }

    public int getRepcode() {
        return RepCode;
    }

    public void setRepcode(int repcode) {
        RepCode = repcode;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public Object getData() {
        return Data;
    }

    public void setData(Object data) {
        Data = data;
    }

    @Override
    public String toString() {
        return "APIResult{" +
                "RepCode=" + RepCode +
                ", Message='" + Message + '\'' +
                ", Data=" + Data +
                '}';
    }
}
