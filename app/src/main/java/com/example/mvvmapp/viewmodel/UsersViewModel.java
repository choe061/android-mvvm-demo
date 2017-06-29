package com.example.mvvmapp.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.ObservableArrayList;
import android.util.Log;

import com.example.mvvmapp.BR;
import com.example.mvvmapp.model.api.UserApi;
import com.example.mvvmapp.model.domain.User;
import com.example.mvvmapp.network.ApiCallback;

import java.util.ArrayList;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import retrofit2.Response;

/**
 * Created by choi on 2017. 6. 26..
 * Android Architecture Components Basic Sample의 설명 중 일부
 * BaseViewModel objects don't have references to activities, fragments, or Android views.
 * 뷰모델은 액티비티, 프래그먼트 같은 안드로이드 뷰들에 대한 레퍼런스를 가지지 않는다. (MVP와 가장 큰 차이점)
 *
 * 데이터가 변경될때 이를 알려주는 기능을 데이터 객체에 부여한다.
 */

public class UsersViewModel extends BaseObservable implements BaseViewModel {

    private static final String TAG = UsersViewModel.class.getName();

    private ObservableArrayList<UserViewModel> users = new ObservableArrayList<>();
    private UserApi userApi;
    private CompositeDisposable compositeDisposable;

    public UsersViewModel(UserApi userApi) {
        this.userApi = userApi;
        this.compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onResume() {
        Disposable disposable = userApi.requestGetGithubUsers(new ApiCallback<Response<ArrayList<User>>>() {
            @Override
            public void onSuccess(Response<ArrayList<User>> model) {
                Log.e(TAG, String.valueOf(model.body()));
                changeUsersDataSet(model.body());
            }

            @Override
            public void onError(String msg) {
                Log.e(TAG, msg);
            }
        });
        compositeDisposable.add(disposable);
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onDestroy() {
        compositeDisposable.dispose();
    }

    private void changeUsersDataSet(ArrayList<User> users) {
        for (User user : users) {
            this.users.add(new UserViewModel(user.getLogin(), String.valueOf(user.getId()), user.getAvatar_url()));
        }
        notifyPropertyChanged(BR.user);
    }

    public ObservableArrayList<UserViewModel> getUsers() {
        return users;
    }

    public void setUsers(ObservableArrayList<UserViewModel> users) {
        this.users = users;
    }
}
