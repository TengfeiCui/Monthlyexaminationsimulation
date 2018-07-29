package com.bwie.monthlyexaminationsimulation.model;

import android.content.Context;

import com.bwie.monthlyexaminationsimulation.bean.AddShopping_Bean;
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
public class AddModel {
    private Context context;
    private int uid = 2248;
    private String url="http://www.zhaoapi.cn/product/addCart";
    public void getData(int pids,final IModel iModel){
        RetrofitWork retrofitWork = RetrofitWork.getRetrofitWork();
        RetrofitService retrofitService = retrofitWork.createRequest(RetrofitService.class);
        Observable<AddShopping_Bean> addShopping_beanObservable = retrofitService.getAdd(uid,pids);
        addShopping_beanObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AddShopping_Bean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(AddShopping_Bean addShopping_bean) {
                            iModel.getSuccess(addShopping_bean);
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
        void getSuccess(AddShopping_Bean addShopping_bean);
    }
}
