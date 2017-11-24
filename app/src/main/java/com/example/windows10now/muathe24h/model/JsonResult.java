package com.example.windows10now.muathe24h.model;

import java.util.List;

/**
 * Created by Windows 10 Now on 11/18/2017.
 */

public class JsonResult {
    private int repCode;
    private String message;
    private List mList;

    public JsonResult(int repCode, String message, List list) {
        this.repCode = repCode;
        this.message = message;
        mList = list;
    }

    public JsonResult() {
    }

    public int getRepCode() {
        return repCode;
    }

    public void setRepCode(int repCode) {
        this.repCode = repCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List getList() {
        return mList;
    }

    public void setList(List list) {
        mList = list;
    }
}
