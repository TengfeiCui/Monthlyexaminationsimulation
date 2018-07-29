package com.bwie.monthlyexaminationsimulation.model;

import android.content.Context;

import com.bwie.monthlyexaminationsimulation.bean.HomeBean;
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
public class HomeModel {
    private Context context;
    private String url="https://www.zhaoapi.cn/home/getHome";
    public void getData(final IModel iModel){
        RetrofitWork retrofitWork =RetrofitWork.getRetrofitWork();
        RetrofitService retrofitService = retrofitWork.createRequest(RetrofitService.class);
        Observable<HomeBean> homeBeanObservable = retrofitService.getHome();
        homeBeanObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HomeBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HomeBean homeBean) {
                            iModel.getSuccess(homeBean);
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
        void getSuccess(HomeBean homeBean);
    }
}
