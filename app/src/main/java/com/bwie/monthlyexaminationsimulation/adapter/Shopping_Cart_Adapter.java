package com.bwie.monthlyexaminationsimulation.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bwie.monthlyexaminationsimulation.R;
import com.bwie.monthlyexaminationsimulation.bean.Shopping_cart;
import com.bwie.monthlyexaminationsimulation.view.Main3Activity;

import java.util.List;

/**
 * author:Created by Administrator on 2018/7/28.
 */
public class Shopping_Cart_Adapter extends BaseExpandableListAdapter {
    private OnGetSum onGetSum;

    private List<Shopping_cart.DataBean> data;
    private Main3Activity main3Activity;
    public Shopping_Cart_Adapter(List<Shopping_cart.DataBean> data, Main3Activity main3Activity) {
        this.data = data;
        this.main3Activity = main3Activity;
    }

    @Override
    public int getGroupCount() {
        return data==null?0:data.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return data.get(groupPosition)==null?0:data.get(groupPosition).getList().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ViewHolder1 viewHolder1;
        if(convertView==null){
            convertView = View.inflate(main3Activity, R.layout.shopping_cart_group, null);
            viewHolder1=new ViewHolder1();
            viewHolder1.group_checkBox=convertView.findViewById(R.id.group_checkbox);
            viewHolder1.group_text = convertView.findViewById(R.id.group_text);
            convertView.setTag(viewHolder1);
        }else{
            viewHolder1 = (ViewHolder1) convertView.getTag();
        }

        viewHolder1.group_text.setText(data.get(groupPosition).getSellerName());
        viewHolder1.group_checkBox.setChecked(data.get(groupPosition).getGroupFlag());
        viewHolder1.group_checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CheckBox box = (CheckBox) v;
                boolean checked = box.isChecked();
                Toast.makeText(main3Activity,checked+"有数据",Toast.LENGTH_SHORT).show();

                data.get(groupPosition).setGroupFlag(checked);

                List<Shopping_cart.DataBean.ListBean> list = data.get(groupPosition).getList();
                for (int i = 0; i < list.size(); i++) {
                    //遍历集合给子条目的复选框选中状态设为父条目复选框的选中状态，父条目及其子条目的全选和反选，
                   list.get(i).setChileFlag(checked);
                }
                createSum();
                //调用刷新适配器的方法
                setNotifyDataSetChanged();
            }
        });

        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final ViewHolder2 viewHolder2;
        if(convertView==null){
            convertView = View.inflate(main3Activity,R.layout.shopping_cart_chile,null);
            viewHolder2 =new ViewHolder2();
            viewHolder2.chile_checkBox=convertView.findViewById(R.id.chile_checkbox);
            viewHolder2.chile_imageView = convertView.findViewById(R.id.shopping_cart_chile_imageview);
            viewHolder2.chile_title = convertView.findViewById(R.id.chile_title);
            viewHolder2.chile_price = convertView.findViewById(R.id.chile_price);
            viewHolder2.chile_text = convertView.findViewById(R.id.shopping_price_text);
            viewHolder2.button_jia = convertView.findViewById(R.id.shopping_jia);
            viewHolder2.button_jian = convertView.findViewById(R.id.shopping_jian);
            convertView.setTag(viewHolder2);
        }else{
            viewHolder2 = (ViewHolder2) convertView.getTag();
        }
        viewHolder2.chile_checkBox.setChecked(data.get(groupPosition).getList().get(childPosition).getChileFlag());
        viewHolder2.chile_text.setText(data.get(groupPosition).getList().get(childPosition).getNum()+"");
        String[] split = data.get(groupPosition).getList().get(childPosition).getImages().split("\\|");
        Glide.with(main3Activity).load(split[0]).into(viewHolder2.chile_imageView);
        viewHolder2.chile_title.setText(data.get(groupPosition).getList().get(childPosition).getTitle());
        viewHolder2.chile_price.setText("￥"+data.get(groupPosition).getList().get(childPosition).getPrice()+"");
        viewHolder2.button_jia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = viewHolder2.chile_text.getText().toString();
                int counts = Integer.valueOf(s);
                counts++;
                data.get(groupPosition).getList().get(childPosition).setNum(counts);
                viewHolder2.chile_text.setText(data.get(groupPosition).getList().get(childPosition).getNum()+"");
            }
        });
        viewHolder2.button_jian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = viewHolder2.chile_text.getText().toString();
                int counts = Integer.valueOf(s);
                counts--;
                data.get(groupPosition).getList().get(childPosition).setNum(counts);
                viewHolder2.chile_text.setText(data.get(groupPosition).getList().get(childPosition).getNum()+"");
            }
        });
        viewHolder2.chile_checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked1 = viewHolder2.chile_checkBox.isChecked();
                boolean flag = true;
                List<Shopping_cart.DataBean.ListBean> list = data.get(groupPosition).getList();

                list.get(childPosition).setChileFlag(checked1);
                createSum();
              //  cacuteTotalNum( );
                for (int i = 0; i < list.size(); i++) {
                    Boolean chileFlag = list.get(i).getChileFlag();
                 //   onGetUid.getUid(data.get(groupPosition).getList().get(childPosition).getPid());
                    if (chileFlag == false) {
                        flag = false;
                    }
                }
                data.get(groupPosition).setGroupFlag(flag);
                setNotifyDataSetChanged();
            }
        });
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
    public void createSum(){
         double sum=0;
         for(int i=0;i<data.size();i++){
             List<Shopping_cart.DataBean.ListBean> list = data.get(i).getList();
             for (int j = 0 ;j<list.size();j++){
                    if(list.get(j).getChileFlag()){
                       sum+=list.get(j).getNum()*list.get(j).getPrice();
                    }
             }
         }
         onGetSum.getPrice(sum);
    }
    class ViewHolder1{
        CheckBox group_checkBox;
        TextView group_text;
    }
    class ViewHolder2{
        CheckBox chile_checkBox;
        ImageView chile_imageView;
        TextView chile_title,chile_price,chile_text;
        Button button_jia,button_jian;
    }
    public void setNotifyDataSetChanged(){
        //刷新适配器
        notifyDataSetChanged();
    }
    public interface OnGetSum{
        void getPrice(double sum);
    }
    public void setOnGetSum(OnGetSum onGetSum){
        this.onGetSum = onGetSum;
    }
}
