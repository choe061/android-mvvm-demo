package com.example.mvvmapp.model.api;

import com.example.mvvmapp.model.domain.Repository;
import com.example.mvvmapp.network.ApiCallback;

import java.util.ArrayList;

import io.reactivex.disposables.Disposable;
import retrofit2.Response;

/**
 * Created by choi on 2017. 6. 22..
 */

public interface RepositoryApi {
    Disposable requestGetUserRepository(String userID, ApiCallback<Response<ArrayList<Repository>>> callback);
}
