package com.bwie.monthlyexaminationsimulation.utils;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * author:Created by Administrator on 2018/7/26.
 */
public class RetrofitWork {
    public static RetrofitWork retrofitWork = null;
    private Retrofit retrofit;
    public static RetrofitWork getRetrofitWork(){
        if(retrofitWork==null){
            retrofitWork=new RetrofitWork();
        }
        return retrofitWork;
    }
    public RetrofitWork(){
                retrofit = new Retrofit.Builder()
                        .baseUrl("https://www.zhaoapi.cn/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .build();
    }
    public <T> T createRequest(Class<T> clz){
        T t = retrofit.create(clz);
        return t;
    }
}
