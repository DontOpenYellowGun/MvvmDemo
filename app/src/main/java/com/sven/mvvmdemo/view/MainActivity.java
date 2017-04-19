package com.sven.mvvmdemo.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import com.sven.mvvmdemo.R;
import com.sven.mvvmdemo.databinding.ActivityMainBinding;
import com.sven.mvvmdemo.util.DensityUtil;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setViewmodel(new MainActivityViewModel(MainActivity.this));
        binding.recyclerview.addItemDecoration(new HorizontalDividerItemDecoration.Builder(MainActivity.this)
                .color(ContextCompat.getColor(MainActivity.this, R.color.transparent))
                .size(DensityUtil.dip2px(8))
                .build());
    }
}
