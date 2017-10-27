package com.zhusr.rxjava2demo.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhusr.rxjava2demo.R;
import com.zhusr.rxjava2demo.entity.AppInfo;

import java.util.List;

/**
 * Created by zsr on 2017/10/27.
 */

public class AppInfoAdapter extends BaseQuickAdapter<AppInfo, BaseViewHolder> {

    public AppInfoAdapter(@LayoutRes int layoutResId, @Nullable List<AppInfo> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AppInfo item) {
        helper.setText(R.id.appinfo_name, item.getName())
                .setText(R.id.appinfo_packagename, item.getPackageName())
                .setImageDrawable(R.id.appinfo_icon, item.getIcon());
    }
}
