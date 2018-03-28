package com.yizhen.testui.recyclerView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.yizhen.testui.R;


import java.util.ArrayList;

/**
 *  学习使用recycleView
 */

public class TRecycleViewActivity extends AppCompatActivity {

    private RecyclerView rv_transations;

    private BillAdapter mAdapter;

    ArrayList<BillItem> list_bill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trecycle_view);

        rv_transations = (RecyclerView) findViewById(R.id.rv_transactions);

        setData();

        mAdapter = new BillAdapter(list_bill);
//        rv_transations.setLayoutManager(new LinearLayoutManager(this));// 用全限定类名 或者 当前的方法
        rv_transations.setAdapter(mAdapter);

    }

    private void setData(){
        if(list_bill == null){
            list_bill = new ArrayList<BillItem>();
        }

        for (int i = 0; i < 5; i++){
            BillItem item = new BillItem();
            item.content = "橙汁_" + i;
            item.price = 50.22 + "";
            item.date = "2013-01-02 18:20:22";
            list_bill.add(item);
        }
        for (int i = 5; i < 10; i++){
            BillItem item = new BillItem();
            item.content = "橙汁_" + i;
            item.price = 50.22 + "";
            item.date = "2013-02-02 18:20:22";
            list_bill.add(item);
        }

    }

}
