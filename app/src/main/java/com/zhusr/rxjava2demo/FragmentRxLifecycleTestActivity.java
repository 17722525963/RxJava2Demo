package com.zhusr.rxjava2demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zhusr.rxjava2demo.frag.RxLifeFragment;

public class FragmentRxLifecycleTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_rx_lifecycle_test);


        if (findViewById(R.id.content) != null) {

            if (savedInstanceState != null) {
                return;
            }

            // Create an instance of ExampleFragment
            RxLifeFragment rxLifeFragment = new RxLifeFragment();

            rxLifeFragment.setArguments(getIntent().getExtras());

            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.content, rxLifeFragment).commit();
        }
    }
}
