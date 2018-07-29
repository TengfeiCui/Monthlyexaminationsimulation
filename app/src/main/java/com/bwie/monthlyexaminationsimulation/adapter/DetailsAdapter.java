package com.bwie.monthlyexaminationsimulation.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bwie.monthlyexaminationsimulation.R;
import com.bwie.monthlyexaminationsimulation.bean.DetailsBean;
import com.bwie.monthlyexaminationsimulation.view.Main2Activity;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;

import java.util.ArrayList;
import java.util.List;

/**
 * author:Created by Administrator on 2018/7/27.
 */
public class DetailsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ImageView imageView;
    private DetailsBean.DataBean data;
    private Main2Activity main2Activity;
    private List<String> list;

    public DetailsAdapter(DetailsBean.DataBean data, Main2Activity main2Activity) {
        this.data = data;
        this.main2Activity = main2Activity;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 1) {
            View inflate = LayoutInflater.from(main2Activity).inflate(R.layout.details_banner_layout, parent, false);
            imageView = inflate.findViewById(R.id.details_imageview);
            MyViewHodlder1 myViewHodlder1 = new MyViewHodlder1(inflate);
            return myViewHodlder1;
        } else if (viewType == 2) {
            View inflate = LayoutInflater.from(main2Activity).inflate(R.layout.detalis_chile_layout, parent, false);
            MyViewHodlder2 myViewHodlder2 = new MyViewHodlder2(inflate);
            return myViewHodlder2;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyViewHodlder1) {
            String[] split = data.getImages().split("\\|");
            List<String> image = new ArrayList<>();
            for (int i = 0; i < split.length; i++) {
                image.add(split[i]);
                ((MyViewHodlder1) holder).xBanner.setData(image, null);
            }
            ((MyViewHodlder1) holder).xBanner.setmAdapter(new XBanner.XBannerAdapter() {
                @Override
                public void loadBanner(XBanner banner, Object model, View view, int position) {
                    String[] split = data.getImages().split("\\|");
                    Glide.with(main2Activity).load(split[position]).into(imageView);
                }
            });
            ((MyViewHodlder1) holder).xBanner.setPageTransformer(Transformer.Default);
        } else if (holder instanceof MyViewHodlder2) {
            ((MyViewHodlder2) holder).textView1.setText(data.getPrice() + "");
            ((MyViewHodlder2) holder).textView2.setText(data.getTitle());
            ((MyViewHodlder2) holder).textView3.setText(data.getSubhead());
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    public class MyViewHodlder1 extends RecyclerView.ViewHolder {

        private final XBanner xBanner;

        public MyViewHodlder1(View itemView) {
            super(itemView);
            xBanner = itemView.findViewById(R.id.details_banner);
        }
    }

    public class MyViewHodlder2 extends RecyclerView.ViewHolder {

        private final TextView textView1;
        private final TextView textView2;
        private final TextView textView3;

        public MyViewHodlder2(View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.details_price);
            textView2 = itemView.findViewById(R.id.details_title);
            textView3 = itemView.findViewById(R.id.details_jieshao);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 1;
        } else {
            return 2;
        }
    }
}
