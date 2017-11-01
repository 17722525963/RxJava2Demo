package com.zhusr.rxjava2demo;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.jakewharton.rxbinding2.widget.RxTextView;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

public class EditTextSearchActivity extends RxAppCompatActivity {

    private static final String TAG = "EditTextSearchActivity";

    @BindView(R.id.editSearch)
    EditText search;

    @BindView(R.id.tvShow)
    TextView show;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_text_search);

        ButterKnife.bind(this);

        RxTextView.textChanges(search)
                .debounce(500, TimeUnit.MILLISECONDS)
//                .throttleWithTimeout(20, TimeUnit.MILLISECONDS)
                .subscribeOn(AndroidSchedulers.mainThread())
                .filter(new Predicate<CharSequence>() {//过滤操作
                    @Override
                    public boolean test(@NonNull CharSequence charSequence) throws Exception {
                        return charSequence.length() > 0;//过滤字符长度大于0的数据
                    }
                })
//                .flatMap(new Function<CharSequence, ObservableSource<List<String>>>() {//获取数据,并返回一个Observable
//                    @Override
//                    public ObservableSource<List<String>> apply(@NonNull CharSequence charSequence) throws Exception {
//                        //模拟查询数据
//                        List<String> list = new ArrayList<String>();
//                        list.add("a");
//                        list.add("aa");
//                        Log.i(TAG, "apply: " + Thread.currentThread().getName());
//                        return Observable.just(list);
//                    }
//                })
                .switchMap(new Function<CharSequence, ObservableSource<List<String>>>() {//与flatMap类似 但它发射的是最近的一次数据Observable
                    @Override
                    public ObservableSource<List<String>> apply(@NonNull CharSequence charSequence) throws Exception {
                        //模拟查询数据
                        List<String> list = new ArrayList<>();
                        list.add("a");
                        list.add("aa");
                        Log.i(TAG, "apply: " + Thread.currentThread().getName());
                        return Observable.just(list);
                    }
                })
//                .subscribeOn(Schedulers.io())
                .compose(this.<List<String>>bindUntilEvent(ActivityEvent.PAUSE))//手动设置在Activity onPause的时候取消订阅
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<String>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull List<String> strings) {
                        for (String s : strings) {
                            Log.i(TAG, "onNext: " + s);
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
