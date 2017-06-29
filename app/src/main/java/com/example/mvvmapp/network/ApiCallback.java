package com.example.mvvmapp.network;

/**
 * Created by choi on 2017. 6. 8..
 */

public interface ApiCallback<M> {

    void onSuccess(M model);

    void onError(String msg);
}
