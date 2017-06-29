package com.example.mvvmapp.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;

/**
 * Created by choi on 2017. 6. 27..
 */

public class UserViewModel extends BaseObservable {

    public final ObservableField<String> login = new ObservableField<>();
    public final ObservableField<String> id = new ObservableField<>();
    public final ObservableField<String> avatar_url = new ObservableField<>();

    public UserViewModel(String login, String id, String avatar_url) {
        this.login.set(login);
        this.id.set(id);
        this.avatar_url.set(avatar_url);
    }

}
