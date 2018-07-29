package com.bwie.monthlyexaminationsimulation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.bwie.monthlyexaminationsimulation.adapter.HomeAdapter;
import com.bwie.monthlyexaminationsimulation.bean.HomeBean;
import com.bwie.monthlyexaminationsimulation.presenter.HomePresenter;
import com.bwie.monthlyexaminationsimulation.view.HomeView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements HomeView{

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_view);
        HomePresenter homePresenter =new HomePresenter(this);
        homePresenter.Home();

    }

    @Override
    public void getSuccess(HomeBean homeBean) {
        Log.d("home",homeBean.toString());
        HomeBean.DataBean data = homeBean.getData();
        HomeBean.DataBean.TuijianBean tuijian = data.getTuijian();
        List<HomeBean.DataBean.TuijianBean.ListBeanX> list1 = tuijian.getList();
        List<HomeBean.DataBean.FenleiBean> fenlei = data.getFenlei();
        HomeBean.DataBean.MiaoshaBean miaosha = data.getMiaosha();
        List<HomeBean.DataBean.MiaoshaBean.ListBean> list = miaosha.getList();
        List<HomeBean.DataBean.BannerBean> banner = data.getBanner();
        HomeAdapter homeAdapter =new HomeAdapter(banner,list,fenlei,list1,this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(homeAdapter);
    }
}
