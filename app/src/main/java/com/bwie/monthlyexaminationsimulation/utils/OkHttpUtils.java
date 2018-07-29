package com.bwie.monthlyexaminationsimulation.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * author:Created by Administrator on 2018/7/26.
 */
public class OkHttpUtils {
    private static OkHttpUtils okHttpUtils;
    private Context context;
    private final Handler handler;
    private OkHttpClient okHttpClient;

    private OkHttpUtils(Context context){
        this.context =context;
        handler = new Handler(Looper.getMainLooper());
        initOk();
    }

    private void initOk() {
        okHttpClient = new OkHttpClient.Builder()
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(1000,TimeUnit.MICROSECONDS)
                .connectTimeout(5,TimeUnit.SECONDS)
                .build();
    }
    public static OkHttpUtils getInstance(Context context){
        if(okHttpUtils==null){
            synchronized (OkHttpUtils.class){
                if(okHttpUtils==null){
                    okHttpUtils=new OkHttpUtils(context);
                }
            }
        }
        return okHttpUtils;
    }
    public void getData(String  url, final IcallBack icallBack){
        final Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
                okHttpClient.newCall(request).enqueue(new Callback() {

                    private String result;

                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if(response.isSuccessful()){
                            if(response.code()==200){
                                result = response.body().string();
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        icallBack.Data(result);
                                    }
                                });
                            }
                        }

                    }
                });
    }
    public interface IcallBack{
        void Data(String result);
    }
}
