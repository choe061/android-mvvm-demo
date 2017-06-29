package com.example.mvvmapp.viewmodel;

/**
 * Created by choi on 2017. 6. 26..
 */

public interface BaseViewModel {

    void onCreate();

    void onResume();

    void onPause();

    void onDestroy();
}
