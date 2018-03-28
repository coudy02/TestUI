package com.yizhen.testui.recyclerView;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yizhen.testui.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/3/26.
 */

public class BillAdapter extends RecyclerView.Adapter<BillAdapter.VH> {

    ArrayList<BillItem> list_bill;

    public BillAdapter(ArrayList<BillItem> list_bill){
        this.list_bill = list_bill;
        Log.e("zhen", "list_bill = "+ list_bill.size());
        }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bill, parent, false);

        Log.e("zhen", "view = "+ v);

        return new VH(v);
    }

    @Override
    public void onBindViewHolder(VH vh, int position) {
        BillItem item = list_bill.get(position);
        Log.e("zhen", "item.content = "+ item.content);
        vh.tv_content.setText(item.content);
        vh.tv_price.setText(item.price+"");
        vh.tv_date.setText(item.date);

    }

    @Override
    public int getItemCount() {

        return list_bill.size();
    }

    class VH extends RecyclerView.ViewHolder {

        private TextView tv_content;
        private TextView tv_price;
        private TextView tv_date;

        public VH(View v) {
            super(v);
            tv_content = v.findViewById(R.id.tv_content);
            tv_price = v.findViewById(R.id.tv_price);
            tv_date = v.findViewById(R.id.tv_date);
        }
    }

}
