package com.example.mvvmapp.viewmodel.user;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.util.Log;

import com.android.databinding.library.baseAdapters.BR;
import com.example.mvvmapp.model.domain.User;
import com.example.mvvmapp.model.repository.api.UserApi;
import com.example.mvvmapp.network.ApiCallback;
import com.example.mvvmapp.viewmodel.BaseViewModel;

import java.util.ArrayList;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import retrofit2.Response;

/**
 * Created by choi on 2017. 6. 27..
 */

public class UserViewModel extends BaseObservable implements BaseViewModel {

    private static final String TAG = UserViewModel.class.getName();

    public final ObservableField<String> login = new ObservableField<>();
    public final ObservableField<String> id = new ObservableField<>();
    public final ObservableField<String> avatar_url = new ObservableField<>();

    public final ObservableField<String> name = new ObservableField<>();
    public final ObservableField<String> company = new ObservableField<>();
    public final ObservableField<Integer> followers = new ObservableField<>();
    public final ObservableField<Integer> following = new ObservableField<>();
    public final ObservableField<String> created_at = new ObservableField<>();
    public final ObservableField<String> updated_at = new ObservableField<>();

    private UserApi userApi;
    private String userID;
    private CompositeDisposable compositeDisposable;

    public UserViewModel(String login, String id, String avatar_url) {
        this.login.set(login);
        this.id.set(id);
        this.avatar_url.set(avatar_url);
    }

    public UserViewModel(UserApi userApi, String userID) {
        this.userApi = userApi;
        this.userID = userID;
    }

    public UserViewModel(UserApi userApi, String login, String id, String avatar_url, String name,
                         String company, int followers, int following,
                         String created_at, String updated_at) {
        this.userApi = userApi;

        this.login.set(login);
        this.id.set(id);
        this.avatar_url.set(avatar_url);

        this.name.set(name);
        this.company.set(company);
        this.followers.set(followers);
        this.following.set(following);
        this.created_at.set(created_at);
        this.updated_at.set(updated_at);
    }

    @Override
    public void onCreate() {
        this.compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void onResume() {
        requestGetGithubUser(userID);
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onDestroy() {
        compositeDisposable.dispose();
    }

    private void requestGetGithubUser(String userID) {
        Disposable disposable = userApi.requestGetGithubUser(userID, new ApiCallback<Response<User>>() {
            @Override
            public void onSuccess(Response<User> model) {
                Log.d(TAG, String.valueOf(model));
                changeUsersDataSet(model.body());
            }

            @Override
            public void onError(String msg) {
                Log.e(TAG, msg);
//                view.showToast("유저 정보를 가져오지 못했습니다.");
            }
        });
        compositeDisposable.add(disposable);
    }

    private void changeUsersDataSet(User user) {
        this.login.set(user.getLogin());
        this.id.set(String.valueOf(user.getId()));
        this.avatar_url.set(user.getAvatar_url());

        this.name.set(user.getName());
        this.company.set(user.getCompany());
        this.followers.set(user.getFollowers());
        this.following.set(user.getFollowing());
        this.created_at.set(user.getCreated_at());
        this.updated_at.set(user.getUpdated_at());

        notifyPropertyChanged(BR.user);
    }
}
