package com.bwie.monthlyexaminationsimulation.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bwie.monthlyexaminationsimulation.MainActivity;
import com.bwie.monthlyexaminationsimulation.R;
import com.bwie.monthlyexaminationsimulation.adapter.DetailsAdapter;
import com.bwie.monthlyexaminationsimulation.bean.AddShopping_Bean;
import com.bwie.monthlyexaminationsimulation.bean.DetailsBean;
import com.bwie.monthlyexaminationsimulation.presenter.AddPresenter;
import com.bwie.monthlyexaminationsimulation.presenter.DetailsPresenter;

public class Main2Activity extends AppCompatActivity implements DetailsView,AddView {

    private RecyclerView recyclerView;
    private Button button;
    private int pids;
    private Button button1;
    private ImageView details_fanhui;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        final Intent intent = getIntent();
        String pid = intent.getStringExtra("pid");
        pids = Integer.valueOf(pid);
        recyclerView = findViewById(R.id.details_recyclerview);
        button1 = findViewById(R.id.detalis_shopping);
        details_fanhui = findViewById(R.id.details_fanhui);
        details_fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 =new Intent(Main2Activity.this, MainActivity.class);
                startActivity(intent1);
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 =new Intent(Main2Activity.this,Main3Activity.class);
                startActivity(intent1);
            }
        });
        button1 = findViewById(R.id.detalis_button);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddPresenter addPresenter = new AddPresenter(Main2Activity.this);
                addPresenter.Home(pids);
            }
        });

        Log.d("pidd",pid+"============");
        DetailsPresenter detailsPresenter = new DetailsPresenter(Main2Activity.this);
        detailsPresenter.Home(pids);

    }

    @Override
    public void getSuccess(DetailsBean detailsBean) {
        DetailsBean.DataBean data = detailsBean.getData();
        DetailsAdapter detailsAdapter =new DetailsAdapter(data,this);
        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(Main2Activity.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(detailsAdapter);
}

    @Override
    public void getSuccess(AddShopping_Bean addShopping_bean) {
        if(addShopping_bean.getCode().equals("0")){
            Toast.makeText(Main2Activity.this,"加入购物车成功",Toast.LENGTH_SHORT).show();
            Intent intent =new Intent(Main2Activity.this,Main3Activity.class);
                startActivity(intent);
        }
    }
}
