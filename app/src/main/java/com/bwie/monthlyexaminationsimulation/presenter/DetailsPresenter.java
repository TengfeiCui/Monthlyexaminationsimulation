package com.bwie.monthlyexaminationsimulation.presenter;

import com.bwie.monthlyexaminationsimulation.bean.DetailsBean;
import com.bwie.monthlyexaminationsimulation.model.DetailsModel;
import com.bwie.monthlyexaminationsimulation.view.DetailsView;


/**
 * author:Created by Administrator on 2018/7/26.
 */
public class DetailsPresenter {
    private DetailsView detailsViews;
    private final DetailsModel detailsModel;

    public DetailsPresenter(DetailsView detailsView){
      this.detailsViews = detailsView;
        detailsModel = new DetailsModel();
    }
    public void Home(int pids){
        detailsModel.getData(pids,new DetailsModel.IModel() {
            @Override
            public void getSuccess(DetailsBean detailsBean) {
                detailsViews.getSuccess(detailsBean);
            }
        });
    }
}
