package com.example.lastation.servicebestpractice;

/**
 * Created by lastation on 2017/3/27.
 */

public interface DownloadListener {
    void onProgress(int progress);

    void onSuccess();

    void onFailed();

    void onPaused();

    void onCanceled();
}
