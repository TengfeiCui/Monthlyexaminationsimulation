package com.bwie.monthlyexaminationsimulation.adapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bwie.monthlyexaminationsimulation.MainActivity;
import com.bwie.monthlyexaminationsimulation.R;
import com.bwie.monthlyexaminationsimulation.bean.HomeBean;
import com.bwie.monthlyexaminationsimulation.view.Main2Activity;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;

import java.util.List;

/**
 * author:Created by Administrator on 2018/7/26.
 */
public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<HomeBean.DataBean.BannerBean> list;
    private MainActivity mainActivity;
    private ImageView imageView;
    private List<HomeBean.DataBean.TuijianBean.ListBeanX> list1;
    private  List<HomeBean.DataBean.MiaoshaBean.ListBean> listBeans;
    private List<HomeBean.DataBean.FenleiBean> fenlei;
    public HomeAdapter(List<HomeBean.DataBean.BannerBean> list, List<HomeBean.DataBean.MiaoshaBean.ListBean> listBeans, List<HomeBean.DataBean.FenleiBean> fenlei, List<HomeBean.DataBean.TuijianBean.ListBeanX> list1, MainActivity mainActivity) {
        this.list =list;
        this.mainActivity=mainActivity;
        this.listBeans =listBeans;
        this.fenlei= fenlei;
        this.list1=list1;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType==1){
            View inflate = LayoutInflater.from(mainActivity).inflate(R.layout.banner_layout, parent, false);
            imageView = inflate.findViewById(R.id.xbanner_imageview);
            MyViewHolder1 myViewHolder1 =new MyViewHolder1(inflate);
            return myViewHolder1;
        }else if(viewType==2){
            View inflate = LayoutInflater.from(mainActivity).inflate(R.layout.fenlei_layout, parent, false);
            MyViewHolder2 myViewHolder2 =new MyViewHolder2(inflate);
            return myViewHolder2;
        }else if(viewType==3){
            View inflate = LayoutInflater.from(mainActivity).inflate(R.layout.miaosha_layout,parent, false);
            MyViewHolder3 myViewHolder3 =new MyViewHolder3(inflate);
            return myViewHolder3;
        }else if(viewType==4){
            View inflate = LayoutInflater.from(mainActivity).inflate(R.layout.tuijian_layout, parent, false);
            MyViewHolder4 myViewHolder4 = new MyViewHolder4(inflate);
            return myViewHolder4;
        }
        return null;

    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int position) {
        Log.d("icon",list.get(position).getIcon());
        if(holder instanceof MyViewHolder1){

            ((MyViewHolder1) holder).xBanner.setData(list,null);
            ((MyViewHolder1) holder).xBanner.setmAdapter(new XBanner.XBannerAdapter() {
                @Override
                public void loadBanner(XBanner banner, Object model, View view, int position) {
                    Glide.with(mainActivity).load(list.get(position).getIcon()).into(imageView);
                }
            });
            ((MyViewHolder1) holder).xBanner.setPageTransformer(Transformer.Default);
        }else if(holder instanceof MyViewHolder2){
            MSRecycler msRecycler =new MSRecycler(fenlei);
            GridLayoutManager gridLayoutManager =new GridLayoutManager(mainActivity,2);
            gridLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            ((MyViewHolder2) holder).recyclerView.setLayoutManager(gridLayoutManager);
            ((MyViewHolder2) holder).recyclerView.setAdapter(msRecycler);
        }else if(holder instanceof MyViewHolder3){
            Recycler3Adapter recycler3Adapter =new Recycler3Adapter(listBeans);
          LinearLayoutManager linearLayoutManager =new LinearLayoutManager(mainActivity);
          linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            ((MyViewHolder3) holder).miaosha_recycler.setLayoutManager(linearLayoutManager);
            ((MyViewHolder3) holder).miaosha_recycler.setAdapter(recycler3Adapter);
        }else if(holder instanceof MyViewHolder4){
            TJRecycler tjRecycler =new TJRecycler(list1);
            GridLayoutManager gridLayoutManager =new GridLayoutManager(mainActivity,2);
            gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            ((MyViewHolder4) holder).recyclerView.setLayoutManager(gridLayoutManager);
            ((MyViewHolder4) holder).recyclerView.setAdapter(tjRecycler);
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    class MyViewHolder1 extends RecyclerView.ViewHolder{

        private final XBanner xBanner;

        public MyViewHolder1(View itemView) {
            super(itemView);
            xBanner = itemView.findViewById(R.id.xbanner);
        }
    }
    class MyViewHolder2 extends RecyclerView.ViewHolder{

        private final RecyclerView recyclerView;

        public MyViewHolder2(View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.fenlei_recycler);
        }
    }
    class MyViewHolder3 extends RecyclerView.ViewHolder{

        private final RecyclerView miaosha_recycler;

        public MyViewHolder3(View itemView) {
            super(itemView);
            miaosha_recycler = itemView.findViewById(R.id.miaosha_recycler);
        }
    }
    class MyViewHolder4 extends RecyclerView.ViewHolder{


        private final RecyclerView recyclerView;

        public MyViewHolder4(View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.tuijian_recycler);
        }
    }


    @Override
    public int getItemViewType(int position) {
        if(position==0){
            return 1;
        }else if(position==1){
            return 2;
        }else if(position==2){
            return 3;
        }else{
            return 4;
        }
    }
     class MSRecycler extends RecyclerView.Adapter<MSRecycler.MyViewHolder>{
        private List<HomeBean.DataBean.FenleiBean> fenleiBeans;
         public MSRecycler(List<HomeBean.DataBean.FenleiBean> fenleiBeans) {
             this.fenleiBeans=fenleiBeans;
         }

         @NonNull
         @Override
         public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
             View inflate = LayoutInflater.from(mainActivity).inflate(R.layout.fenlei_chile_layout, parent, false);
             MyViewHolder myViewHolder =new MyViewHolder(inflate);
             return myViewHolder;
         }

         @Override
         public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
                Glide.with(mainActivity).load(fenleiBeans.get(position).getIcon()).into(holder.imageView);
                holder.textView.setText(fenleiBeans.get(position).getName());
         }

         @Override
         public int getItemCount() {
             return fenleiBeans==null?0:fenleiBeans.size();
         }

         public class MyViewHolder extends RecyclerView.ViewHolder{

             private final ImageView imageView;
             private final TextView textView;

             public MyViewHolder(View itemView) {
                    super(itemView);
                 imageView = itemView.findViewById(R.id.chile_image);
                 textView = itemView.findViewById(R.id.chile_text);
                }
            }

    }
    class Recycler3Adapter extends RecyclerView.Adapter<Recycler3Adapter.MyViewHolder>{
        private List<HomeBean.DataBean.MiaoshaBean.ListBean> listBeans;
        public Recycler3Adapter(List<HomeBean.DataBean.MiaoshaBean.ListBean> listBeans) {
            this.listBeans =listBeans;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View inflate = LayoutInflater.from(mainActivity).inflate(R.layout.miaosha_chile_layout, parent, false);
            MyViewHolder myViewHolder =new MyViewHolder(inflate);
            return myViewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            String[] split = listBeans.get(position).getImages().split("\\|");
            Glide.with(mainActivity).load(split[0]).into(holder.miaosha_iamge);
        }

        @Override
        public int getItemCount() {
            return listBeans==null?0:listBeans.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder{

            private final ImageView miaosha_iamge;

            public MyViewHolder(View itemView) {
                super(itemView);
                miaosha_iamge = itemView.findViewById(R.id.miaosha_image);
            }
        }
    }
    class TJRecycler extends RecyclerView.Adapter<TJRecycler.MyViewHolder>{
        private List<HomeBean.DataBean.TuijianBean.ListBeanX> list1;
        public TJRecycler(List<HomeBean.DataBean.TuijianBean.ListBeanX> list1) {
            this.list1=list1;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View inflate = LayoutInflater.from(mainActivity).inflate(R.layout.tuijian_chile_layout, parent, false);
            MyViewHolder myViewHolder =new MyViewHolder(inflate);
            return myViewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

            String[] split = list1.get(position).getImages().split("\\|");
            Glide.with(mainActivity).load(split[0]).into(holder.imageView);
                holder.textView.setText(list1.get(position).getTitle());
                holder.imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pid = list1.get(position).getPid();
                        Intent intent =new Intent(mainActivity, Main2Activity.class);
                        intent.putExtra("pid",pid+"");
                        mainActivity.startActivity(intent);
                    }
                });
        }

        @Override
        public int getItemCount() {
            return list1==null?0:list.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder{

            private final ImageView imageView;
            private final TextView textView;

            public MyViewHolder(View itemView) {
                super(itemView);
                imageView = itemView.findViewById(R.id.tuijian_iamge);
                textView = itemView.findViewById(R.id.tuijian_text);
            }
        }
    }
}
