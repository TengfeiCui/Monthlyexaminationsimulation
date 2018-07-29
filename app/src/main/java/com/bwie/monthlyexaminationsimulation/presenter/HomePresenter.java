package com.bwie.monthlyexaminationsimulation.presenter;

import com.bwie.monthlyexaminationsimulation.bean.HomeBean;
import com.bwie.monthlyexaminationsimulation.model.HomeModel;
import com.bwie.monthlyexaminationsimulation.view.HomeView;

/**
 * author:Created by Administrator on 2018/7/26.
 */
public class HomePresenter {
    private HomeView homeViews;
    private final HomeModel homeModel;

    public HomePresenter(HomeView homeView){
        this.homeViews= homeView;
        homeModel = new HomeModel();
    }
    public void Home(){
        homeModel.getData(new HomeModel.IModel() {
            @Override
            public void getSuccess(HomeBean homeBean) {
                homeViews.getSuccess(homeBean);
            }
        });
    }
}
