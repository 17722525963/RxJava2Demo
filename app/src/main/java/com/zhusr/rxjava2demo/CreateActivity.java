package com.zhusr.rxjava2demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

public class CreateActivity extends AppCompatActivity {

    private static final String TAG = "CreateActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        //Just 操作符，参数为Observable 携带的参数类型
        Observable<String> stringObservable = Observable.just("333", "3");

        //Create 操作符
        Observable<String> stringObservable1 = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> e) throws Exception {
                e.onNext("哈哈哈哈");
                e.onNext("哈哈哈哈1");
                e.onNext("哈哈哈哈3");
                e.onComplete();
            }
        });

        //传递数据不能为null  -->  NullPointerException
        Observable<String> stringObservable2 = Observable.just(null);

        String[] ss = {"3", "2", "1"};
        //fromArray : 将数组中的数据转化为Observable发送出去
        Observable<String> stringObservable3 = Observable.fromArray(ss);


        stringObservable2.subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.i(TAG, "accept: " + s);
            }
        });


    }
}
