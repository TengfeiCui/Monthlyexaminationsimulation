package com.bwie.monthlyexaminationsimulation.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwie.monthlyexaminationsimulation.MainActivity;
import com.bwie.monthlyexaminationsimulation.R;
import com.bwie.monthlyexaminationsimulation.adapter.Shopping_Cart_Adapter;
import com.bwie.monthlyexaminationsimulation.bean.Shopping_cart;
import com.bwie.monthlyexaminationsimulation.presenter.Shopping_Cart_Presenter;

import java.util.List;

public class Main3Activity extends AppCompatActivity implements Shopping_Cart_View{

    private ExpandableListView expandableListView;
    private ImageView shopping_fanhui;
    private TextView shopping_heji;
    private Shopping_Cart_Adapter shopping_cart_adapter;
    private double p=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        shopping_heji = findViewById(R.id.shopping_heji);
        expandableListView = findViewById(R.id.shopping_cart_listview);
        shopping_fanhui = findViewById(R.id.shopping_fanhui);
        shopping_heji.setText(p+"");
        shopping_fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Main3Activity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        Shopping_Cart_Presenter shopping_cart_presenter = new Shopping_Cart_Presenter(Main3Activity.this);
        shopping_cart_presenter.Home();
    }

    @Override
    public void getSuccess(Shopping_cart shopping_cart) {
        List<Shopping_cart.DataBean> data = shopping_cart.getData();
        shopping_cart_adapter = new Shopping_Cart_Adapter(data,Main3Activity.this);

        expandableListView.setAdapter(shopping_cart_adapter);
        int count = expandableListView.getCount();
        for(int i=0;i<count;i++){
            expandableListView.expandGroup(i);
        }
       shopping_cart_adapter.setOnGetSum(new Shopping_Cart_Adapter.OnGetSum() {
           @Override
           public void getPrice(double sum) {
               p=sum;
               Log.d("priceAll",sum+"=================");
               shopping_heji.setText(p+"");
           }
       });
    }
}
