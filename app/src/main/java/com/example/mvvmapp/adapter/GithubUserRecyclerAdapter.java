package com.example.mvvmapp.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.RequestManager;
import com.example.mvvmapp.R;
import com.example.mvvmapp.adapter.viewholder.BindingViewHolder;
import com.example.mvvmapp.databinding.RvMainItemBinding;
import com.example.mvvmapp.view.UserDialog;
import com.example.mvvmapp.viewmodel.user.UserViewModel;
import com.example.mvvmapp.widget.OnItemClickListener;

import java.util.ArrayList;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by choi on 2017. 6. 25..
 */

public class GithubUserRecyclerAdapter extends RecyclerView.Adapter<BindingViewHolder<RvMainItemBinding>> {

    private static final String TAG = GithubUserRecyclerAdapter.class.getName();

    @SuppressLint("StaticFieldLeak")
    private static RequestManager requestManager;

    private ArrayList<UserViewModel> users = new ArrayList<>();
    private Context context;

    public GithubUserRecyclerAdapter(RequestManager requestManager) {
        GithubUserRecyclerAdapter.requestManager = requestManager;
    }

    @Override
    public BindingViewHolder<RvMainItemBinding> onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context)
                .inflate(R.layout.rv_main_item, parent, false);
        return new BindingViewHolder<>(view);
    }

    @Override
    public void onBindViewHolder(BindingViewHolder<RvMainItemBinding> holder, int position) {
        holder.binding().setUser(users.get(position));
        holder.binding().setListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final UserDialog userDialog = new UserDialog(context, String.valueOf(v.getTag()));
                userDialog.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface dialog) {
                    }
                });
                userDialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    //notifyDataSetChanged()를 호출하면 화면 깜빡임 문제가 생길 수 있다.
    //notifyItemInserted()를 사용한다.
    public void addItems(ArrayList<UserViewModel> users) {
        this.users.addAll(users);
        notifyItemRangeInserted(0, this.users.size() - 1);
    }

    @BindingAdapter({"imageUrl", "error"})
    public static void loadImage(ImageView avartar_url, String url, Drawable errorDrawable) {
        requestManager.load(url)
                .error(errorDrawable)
                .bitmapTransform(new CropCircleTransformation(avartar_url.getContext()))
                .into(avartar_url);
    }
}
