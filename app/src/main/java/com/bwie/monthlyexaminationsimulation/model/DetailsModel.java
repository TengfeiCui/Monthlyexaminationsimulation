package com.bwie.monthlyexaminationsimulation.model;

import android.content.Context;

import com.bwie.monthlyexaminationsimulation.bean.DetailsBean;
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
public class DetailsModel {
    private Context context;
    private String url="https://www.zhaoapi.cn/product/getProductDetail";
    public void getData(int pids,final IModel iModel){
        RetrofitWork retrofitWork = RetrofitWork.getRetrofitWork();
        RetrofitService retrofitService = retrofitWork.createRequest(RetrofitService.class);
        Observable<DetailsBean> detailsBeanObservable = retrofitService.getDetails(pids);
        detailsBeanObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DetailsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DetailsBean detailsBean) {
                            iModel.getSuccess(detailsBean);
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
        void getSuccess(DetailsBean detailsBean);
    }
}
