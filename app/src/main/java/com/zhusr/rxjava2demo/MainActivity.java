package com.zhusr.rxjava2demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.orhanobut.logger.Logger;
import com.zhusr.rxjava2demo.entity.Person;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.observables.GroupedObservable;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
    }

    @OnClick({R.id.search, R.id.throttle, R.id.rxlifecycle_fragment, R.id.rx_merge, R.id.rx_interval, R.id.rx_getappinfo})
    public void click(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.search:
                intent.setClass(this, EditTextSearchActivity.class);
                break;
            case R.id.throttle:
                intent.setClass(this, ThrottleActivity.class);
                break;
            case R.id.rxlifecycle_fragment:
                intent.setClass(this, FragmentRxLifecycleTestActivity.class);
                break;
            case R.id.rx_merge:
                intent.setClass(this, MergeActivity.class);
                break;
            case R.id.rx_interval:
                intent.setClass(this, IntervalActivity.class);
                break;
            case R.id.rx_getappinfo:
                intent.setClass(this, GetAPPInfoActivity.class);
                break;
        }
        startActivity(intent);
    }

    @OnClick({R.id.scan, R.id.groupBy})
    public void button(View v) {
        switch (v.getId()) {
            case R.id.scan:
                scan();
                break;
            case R.id.groupBy:
                groupBy();
                break;
        }
    }

    private void groupBy() {
        Toast.makeText(this, "暂未理解~", Toast.LENGTH_SHORT).show();
    }

    private void scan() {
        Observable.just(1, 2, 3, 4, 5, 6, 77, 88, 99)
                .scan(new BiFunction<Integer, Integer, Integer>() {
                    @Override
                    public Integer apply(@NonNull Integer integer, @NonNull Integer integer2) throws Exception {
                        return integer + integer2;//操作规则
                    }
                }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Integer integer) {
                Logger.d(String.valueOf(integer));
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

}
