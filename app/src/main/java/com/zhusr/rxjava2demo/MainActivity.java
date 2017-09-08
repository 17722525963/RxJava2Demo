package com.zhusr.rxjava2demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
    }

    @OnClick({R.id.search, R.id.throttle})
    public void click(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.search:
                intent.setClass(this, EditTextSearchActivity.class);
                break;
            case R.id.throttle:
                intent.setClass(this, ThrottleActivity.class);
                break;
        }
        startActivity(intent);
    }

}