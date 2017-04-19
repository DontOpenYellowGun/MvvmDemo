package com.sven.mvvmdemo.view;

import android.content.Context;
import android.databinding.ObservableArrayList;
import android.util.Log;

import com.kelin.mvvmlight.base.ViewModel;
import com.sven.mvvmdemo.R;
import com.sven.mvvmdemo.model.Weather;
import com.sven.mvvmdemo.net.Http;
import com.sven.mvvmdemo.net.HttpObserver;

import me.tatarka.bindingcollectionadapter.ItemView;

/**
 * Created by Sven on 2017/4/19.
 */

public class MainActivityViewModel implements ViewModel {

//    Context（上下文）
//    Model (数据模型Bean)
//    Data Field （数据绑定）
//    Command （命令绑定）
//    Child ViewModel （子ViewModel）

    private Context mContext;
    public final ItemView itemView = ItemView.of(com.kelin.mvvmlight.BR.viewmodel, R.layout.item_recyclerview);
    public final ObservableArrayList<Weather.WeatherinfoBean> itemModels = new ObservableArrayList<>();

    public MainActivityViewModel(Context mContext) {
        this.mContext = mContext;
        initData();
    }

    private void initData() {
        Http.getInstance()
                .getWeather("101040100")
                .subscribe(new HttpObserver<Weather.WeatherinfoBean>() {
                    @Override
                    public void forceNext(Weather.WeatherinfoBean value) {
                        for (int i = 0; i < 100; i++) {
                            itemModels.add(value);
                        }
                        Log.e("MainActivity", value.toString());
                    }
                });
    }
}
