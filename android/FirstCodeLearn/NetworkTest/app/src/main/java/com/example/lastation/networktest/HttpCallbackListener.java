package com.example.lastation.networktest;

/**
 * Created by lastation on 2017/2/21.
 */

public interface HttpCallbackListener {
    void onFinish(String response);
    void onError(Exception e);
}
