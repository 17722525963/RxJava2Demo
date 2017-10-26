package com.zhusr.rxjava2demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.components.RxActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public class MergeActivity extends RxActivity {

    private List<String> s1 = new ArrayList<>();
    private List<String> s2 = new ArrayList<>();
    private List<Integer> s3 = new ArrayList<>();

    Observable<List<String>> o1;
    Observable<List<String>> o2;
    Observable<List<Integer>> o3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merge);

        ButterKnife.bind(this);

        s1.add("张三丰");
        s1.add("张丰");
        s1.add("张三");

        s2.add("李四");
        s2.add("李四无");
        s2.add("李四六");
        s2.add("李四器");

        s3.add(1);
        s3.add(2);
        s3.add(3);

        o1 = Observable.just(s1);
        o2 = Observable.just(s2);
        o3 = Observable.just(s3);
    }


    @OnClick(R.id.merge)
    public void merge() {
//        Observable.merge(o1, o2, o3).subscribe(new Observer<List<? extends Serializable>>() {
//            @Override
//            public void onSubscribe(@NonNull Disposable d) {
//
//            }
//
//            @Override
//            public void onNext(@NonNull List<? extends Serializable> serializables) {
//                for (int i = 0; i < serializables.size(); i++) {
//                    Log.i("???", "onNext: " + serializables.get(i));
//                }
//            }
//
//            @Override
//            public void onError(@NonNull Throwable e) {
//
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        });


        Observable.concat(o1, o2, o3)
                .subscribe(new Observer<List<? extends Serializable>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull List<? extends Serializable> serializables) {
                        for (int i = 0; i < serializables.size(); i++) {
                            Log.i("???", "onNext: " + serializables.get(i));
                        }
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
