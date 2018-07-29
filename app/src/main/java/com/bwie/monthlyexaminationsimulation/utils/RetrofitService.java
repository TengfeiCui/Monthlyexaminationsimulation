package com.bwie.monthlyexaminationsimulation.utils;

import com.bwie.monthlyexaminationsimulation.bean.AddShopping_Bean;
import com.bwie.monthlyexaminationsimulation.bean.DetailsBean;
import com.bwie.monthlyexaminationsimulation.bean.HomeBean;
import com.bwie.monthlyexaminationsimulation.bean.Shopping_cart;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * author:Created by Administrator on 2018/7/26.
 */
public interface RetrofitService {
        @POST("home/getHome")
        Observable<HomeBean> getHome();
        @POST("product/getProductDetail")
        Observable<DetailsBean> getDetails(@Query("pid") int pids );
        @POST("product/addCart")
        Observable<AddShopping_Bean> getAdd(@Query("uid")int uid, @Query("pid") int pids);
        @POST("product/getCarts")
        Observable<Shopping_cart> getShopping(@Query("uid")int uid);
}
