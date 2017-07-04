package com.example.mvvmapp.view;

import android.app.Activity;
import android.app.Dialog;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
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

    private Activity activity;
    private UserViewModel userViewModel;
    private String userID;

    public UserDialog(@NonNull Activity activity) {
        super(activity);
        this.activity = activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userViewModel = new UserViewModel(new UserApiRequest());
        ActivityMainUserDialogBinding binding = DataBindingUtil.setContentView(activity, R.layout.activity_main_user_dialog);
        binding.setUser(userViewModel);
        binding.setView(this);
    }

    public void setDialogText(User user) {
//        userID = user.getLogin();
//        requestManager.load(user.getAvatar_url())
//                .placeholder(R.drawable.default_profile_img)
//                .bitmapTransform(new CropCircleTransformation(getContext()))
//                .into(profile_img);
    }

    public void startRepositoryActivity(View view) {
//        Intent intent = new Intent(getContext(), RepositoryActivity.class);
//        intent.putExtra("userID", userID);
//        getContext().startActivity(intent);
    }

    public void cancelClick(View view) {
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
