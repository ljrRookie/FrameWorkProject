package com.ljr.frameworkproject.adapter;


import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ljr.frameworkproject.R;
import com.ljr.frameworkproject.bean.GirlBean;

import java.util.List;

public class GirlAdapter extends BaseQuickAdapter<GirlBean, BaseViewHolder> {
    public GirlAdapter(int layoutResId, @Nullable List<GirlBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GirlBean item) {
        ImageView imageView = helper.getView(R.id.iv_image);
        Glide.with(mContext).load(item.icon).apply(new RequestOptions().centerCrop()).into(imageView);
    }
}
