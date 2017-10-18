package com.zhusr.rxjava2demo;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.jakewharton.rxbinding2.view.RxView;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public class ThrottleActivity extends RxAppCompatActivity {

    @BindView(R.id.throttle)
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_throttle);

        ButterKnife.bind(this);

        RxView.clicks(button)
                .throttleFirst(1000, TimeUnit.MILLISECONDS)
                .compose(this.bindUntilEvent(ActivityEvent.STOP))//自动取消订阅  在调用生命周期的对应生命周期取消订阅  此处调用是在onCreate() 方法，因此取消订阅是在onDestroy()中
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Object o) {
                        Toast.makeText(ThrottleActivity.this, "点了我一下！" + System.currentTimeMillis(), Toast.LENGTH_SHORT).show();
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
