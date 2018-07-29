package com.bwie.monthlyexaminationsimulation.presenter;

import com.bwie.monthlyexaminationsimulation.bean.AddShopping_Bean;
import com.bwie.monthlyexaminationsimulation.model.AddModel;
import com.bwie.monthlyexaminationsimulation.view.AddView;

/**
 * author:Created by Administrator on 2018/7/26.
 */
public class AddPresenter {
    private AddView addViews;
    private final AddModel addModel;

    public AddPresenter(AddView addView){
        this.addViews=addView;
        addModel = new AddModel();
    }
    public void Home(int pids){
        addModel.getData(pids,new AddModel.IModel() {
            @Override
            public void getSuccess(AddShopping_Bean addShopping_bean) {
                addViews.getSuccess(addShopping_bean);
            }
        });
    }
}
