package com.example.mvvmapp.model.repository.request;

import com.example.mvvmapp.model.repository.api.UserApi;
import com.example.mvvmapp.model.domain.User;
import com.example.mvvmapp.network.ApiCallback;
import com.example.mvvmapp.network.NetModule;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

/**
 * Created by choi on 2017. 6. 18..
 */

public class UserApiRequest implements UserApi {

    @Override
    public Disposable requestGetGithubUsers(ApiCallback<Response<ArrayList<User>>> callback) {
        Observable<Response<ArrayList<User>>> users = NetModule.getHttpService().getGithubUsers();
        return users.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(callback::onSuccess, throwable -> callback.onError(throwable.getMessage()));
    }

    @Override
    public Disposable requestGetGithubUser(String userID, ApiCallback<Response<User>> callback) {
        Observable<Response<User>> user = NetModule.getHttpService().getGithubUser(userID);
        return user.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(callback::onSuccess, throwable -> callback.onError(throwable.getMessage()));
    }
}
