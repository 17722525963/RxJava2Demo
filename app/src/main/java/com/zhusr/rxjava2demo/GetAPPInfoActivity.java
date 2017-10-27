package com.zhusr.rxjava2demo;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.trello.rxlifecycle2.components.RxActivity;
import com.zhusr.rxjava2demo.adapter.AppInfoAdapter;
import com.zhusr.rxjava2demo.entity.AppInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class GetAPPInfoActivity extends RxActivity {

    private static final String TAG = "GetAPPInfoActivity";

    @BindView(R.id.recy_appinfo)
    RecyclerView recyclerView;

    private AppInfoAdapter appInfoAdapter;

    private PackageManager manager;

    private List<AppInfo> mDatas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_appinfo);

        ButterKnife.bind(this);

        manager = this.getPackageManager();

        Observable.create(new ObservableOnSubscribe<ApplicationInfo>() {//获取已安装package信息
            @Override
            public void subscribe(@NonNull ObservableEmitter<ApplicationInfo> e) throws Exception {
                List<ApplicationInfo> infoList = getApplicationInfoList(manager);
                for (ApplicationInfo info : infoList) {
                    e.onNext(info);
                }
                e.onComplete();
            }

        }).filter(new Predicate<ApplicationInfo>() {//过滤操作，过滤系统应用
            @Override
            public boolean test(@NonNull ApplicationInfo applicationInfo) throws Exception {
                return (applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) <= 0;
            }
        })
                .map(new Function<ApplicationInfo, AppInfo>() {//将获取到的信息转换为自己需要的信息数据
            @Override
            public AppInfo apply(@NonNull ApplicationInfo applicationInfo) throws Exception {
                AppInfo appInfo = new AppInfo();
                appInfo.setName(applicationInfo.loadLabel(manager).toString());
                appInfo.setPackageName(applicationInfo.packageName);
                appInfo.setIcon(applicationInfo.loadIcon(manager));
                return appInfo;
            }

        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AppInfo>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull AppInfo appInfo) {
                        Log.i(TAG, "onNext: " + appInfo.toString());
                        mDatas.add(appInfo);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.i(TAG, "onError: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.i(TAG, "onComplete: " + mDatas.size());
                        appInfoAdapter = new AppInfoAdapter(R.layout.item_appinfo, mDatas);
                        recyclerView.setLayoutManager(new LinearLayoutManager(GetAPPInfoActivity.this));
                        recyclerView.setAdapter(appInfoAdapter);
                    }
                });


    }


    private List<ApplicationInfo> getApplicationInfoList(final PackageManager pm) {
        return pm.getInstalledApplications(PackageManager.GET_UNINSTALLED_PACKAGES);
    }
}
