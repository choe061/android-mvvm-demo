package com.example.mvvmapp.model.repository.request;


import com.example.mvvmapp.model.repository.api.RepositoryApi;
import com.example.mvvmapp.model.domain.Repository;
import com.example.mvvmapp.network.ApiCallback;
import com.example.mvvmapp.network.NetModule;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

/**
 * Created by choi on 2017. 6. 22..
 */

public class RepositoryApiRequest implements RepositoryApi {

    @Override
    public Disposable requestGetUserRepository(String userID, ApiCallback<Response<ArrayList<Repository>>> callback) {
        Observable<Response<ArrayList<Repository>>> repos = NetModule.getHttpService().getUserRepository(userID);
        return repos.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(callback::onSuccess, throwable -> callback.onError(throwable.getMessage()));
    }
}
