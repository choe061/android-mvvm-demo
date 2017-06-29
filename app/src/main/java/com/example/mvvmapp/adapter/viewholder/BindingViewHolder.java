package com.example.mvvmapp.adapter.viewholder;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by choi on 2017. 6. 25..
 * Generic으로 선언 시 모든 RecyclerViewAdapter에서 공통으로 사용할 수 있다.
 */

public class BindingViewHolder<T> extends RecyclerView.ViewHolder {

    private final T binding;

    public BindingViewHolder(View itemView) {
        super(itemView);
        this.binding = (T) DataBindingUtil.bind(itemView);
    }

    public T binding() {
        return binding;
    }
}
