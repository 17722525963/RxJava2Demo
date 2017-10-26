package com.zhusr.rxjava2demo;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.trello.rxlifecycle2.components.RxActivity;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class IntervalActivity extends RxActivity {

    @BindView(R.id.interval)
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interval);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.interval)
    public void click() {
        Observable.interval(1, TimeUnit.SECONDS)//间隔1秒上游发送一次事件
                .take(10)//限制只取前10个事件
                .subscribeOn(Schedulers.io())
                .map(new Function<Long, Long>() {//转换
                    @Override
                    public Long apply(@NonNull Long aLong) throws Exception {
                        return 9 - aLong;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        button.setClickable(false);
                        button.setBackgroundColor(Color.GRAY);
                        button.setTextColor(Color.BLACK);
                    }
                })
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Long aLong) {
                        button.setText(aLong + "秒后重新发送");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        button.setClickable(true);
                        button.setBackgroundColor(Color.RED);
                        button.setTextColor(Color.WHITE);
                        button.setText("点击获取验证码");
                    }
                });
    }


}
