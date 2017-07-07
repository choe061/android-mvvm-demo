package com.example.mvvmapp.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.example.mvvmapp.R;
import com.example.mvvmapp.databinding.ActivityMainUserDialogBinding;
import com.example.mvvmapp.model.domain.User;
import com.example.mvvmapp.model.repository.request.UserApiRequest;
import com.example.mvvmapp.viewmodel.user.UserViewModel;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by choi on 2017. 6. 22..
 */

public class UserDialog extends Dialog {

    private Context context;
    private UserViewModel userViewModel;
    private String userID;

    public UserDialog(Context context, String userID) {
        super(context);
        this.context = context;
        this.userID = userID;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userViewModel = new UserViewModel(new UserApiRequest(), userID);
        userViewModel.onCreate();
        ActivityMainUserDialogBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(context), R.layout.activity_main_user_dialog, null, false);
        setContentView(binding.getRoot());
        binding.setUser(userViewModel);
        binding.setView(this);

        userViewModel.onResume();
    }

    public void startRepositoryActivity(View view) {
//        Intent intent = new Intent(getContext(), RepositoryActivity.class);
//        intent.putExtra("userID", userID);
//        getContext().startActivity(intent);
    }

    public void cancelClick(View view) {
        userViewModel.onDestroy();
        this.cancel();
    }

    @BindingAdapter({"imageUrl", "error"})
    public static void loadImage(ImageView avartar_url, String url, Drawable errorDrawable) {
        MainActivity.requestManager.load(url)
                .error(errorDrawable)
                .bitmapTransform(new CropCircleTransformation(avartar_url.getContext()))
                .into(avartar_url);
    }

}
