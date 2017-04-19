package com.sven.mvvmdemo.net;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.sven.mvvmdemo.model.Weather;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Sven on 2017/2/6.
 */

public class Http {

    private static final int DEFAULT_TIMEOUT = 10;
    private static final String BASE_URL = "http://www.weather.com.cn/data/";
    private static Http instance = null;
    private final Retrofit mRetrofit;

    public static Http getInstance() {
        if (instance == null) {
            synchronized (Http.class) {
                if (instance == null) {
                    instance = new Http();
                }
            }
        }
        return instance;
    }

    private Http() {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder()
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true);

        mRetrofit = new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();
    }

    /*获取城市的天气*/
    public Observable<Weather.WeatherinfoBean> getWeather(String cityId) {
        return mRetrofit.create(WeatherService.class)
                .getWeather(cityId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<Weather, Weather.WeatherinfoBean>() {
                    @Override
                    public Weather.WeatherinfoBean apply(Weather weather) throws Exception {
                        return weather.getWeatherinfo();
                    }
                });
    }
}
