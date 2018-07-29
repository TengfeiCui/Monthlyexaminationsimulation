package com.bwie.monthlyexaminationsimulation.model;

import android.content.Context;

import com.bwie.monthlyexaminationsimulation.bean.Shopping_cart;
import com.bwie.monthlyexaminationsimulation.utils.RetrofitService;
import com.bwie.monthlyexaminationsimulation.utils.RetrofitWork;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * author:Created by Administrator on 2018/7/26.
 */
public class Shopping_Cart_Model {
    private Context context;
    private int uid=2248;
    private String url="http://www.zhaoapi.cn/product/getCarts";
    public void getData(final IModel iModel){
        RetrofitWork retrofitWork = RetrofitWork.getRetrofitWork();
        RetrofitService retrofitService = retrofitWork.createRequest(RetrofitService.class);
        Observable<Shopping_cart> shopping_cartObservable = retrofitService.getShopping(uid);
               shopping_cartObservable.subscribeOn(Schedulers.newThread())
               .observeOn(AndroidSchedulers.mainThread())
                       .subscribe(new Observer<Shopping_cart>() {
                           @Override
                           public void onSubscribe(Disposable d) {

                           }

                           @Override
                           public void onNext(Shopping_cart shopping_cart) {
                                            iModel.getSuccess(shopping_cart);
                           }

                           @Override
                           public void onError(Throwable e) {

                           }

                           @Override
                           public void onComplete() {

                           }
                       });

    }
    public interface IModel{
        void getSuccess(Shopping_cart shopping_cart);
    }
}
