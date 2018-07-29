package com.bwie.monthlyexaminationsimulation.presenter;

        import com.bwie.monthlyexaminationsimulation.bean.Shopping_cart;
        import com.bwie.monthlyexaminationsimulation.model.Shopping_Cart_Model;
        import com.bwie.monthlyexaminationsimulation.view.Shopping_Cart_View;

/**
 * author:Created by Administrator on 2018/7/26.
 */
public class Shopping_Cart_Presenter {
    private Shopping_Cart_View shopping_cart_views;
    private final Shopping_Cart_Model shopping_cart_model;

    public Shopping_Cart_Presenter(Shopping_Cart_View shopping_cart_view){
        this.shopping_cart_views =shopping_cart_view;
        shopping_cart_model = new Shopping_Cart_Model();
    }
    public void Home(){
        shopping_cart_model.getData(new Shopping_Cart_Model.IModel() {
            @Override
            public void getSuccess(Shopping_cart shopping_cart) {
                shopping_cart_views.getSuccess(shopping_cart);
            }
        });
    }
}
