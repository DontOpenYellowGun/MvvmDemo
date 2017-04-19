package com.sven.mvvmdemo.net;

import com.sven.mvvmdemo.model.Weather;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Sven on 2017/2/6.
 */

public interface WeatherService {
    /**
     * 根据newsid获取对应的资讯数据
     * 如果不需要转换成Json数据,可以用了ResponseBody;
     *
     * @param cityId
     * @return call
     */
    @GET("cityinfo/{cityId}.html")
    Observable<Weather> getWeather(@Path("cityId") String cityId);
}
