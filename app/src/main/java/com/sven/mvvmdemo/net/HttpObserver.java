package com.sven.mvvmdemo.net;


import android.util.Log;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Sven on 2017/2/6.
 * 网络请求的观察者
 * 统一处理异常
 */

public abstract class HttpObserver<T> implements Observer<T> {


    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T value) {
        forceNext(value);
    }

    @Override
    public void onError(Throwable e) {
        /*这里做统一的异常处理*/
        Log.e("HttpObserver", e.getMessage());
    }

    @Override
    public void onComplete() {

    }

    public abstract void forceNext(T value);
}
