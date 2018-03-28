package com.yizhen.testui.sqlite;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.yizhen.testui.R;
import com.yizhen.testui.recyclerView.BillAdapter;
import com.yizhen.testui.recyclerView.BillItem;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */

public class SQLiteActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_sql_add;
    private Button btn_sql_modify;
    private Button btn_sql_delete;
    private RecyclerView rv_query_list;
    private BillAdapter mAdapter;
    ArrayList<BillItem> list_bill = null;

    private DBManager mManager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);

        mManager = new DBManager(this);

        btn_sql_add = (Button) findViewById(R.id.btn_sql_add);
        btn_sql_add.setOnClickListener(this);
        btn_sql_modify = (Button) findViewById(R.id.btn_sql_modify);
        btn_sql_modify.setOnClickListener(this);
        btn_sql_delete = (Button) findViewById(R.id.btn_sql_delete);
        btn_sql_delete.setOnClickListener(this);

        rv_query_list = (RecyclerView) findViewById(R.id.rv_query_list);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_sql_add:{
                setData();
                Cursor cursor = mManager.queryAll();
                list_bill =  getData(cursor);
                mAdapter = new BillAdapter(list_bill);
//        rv_transations.setLayoutManager(new LinearLayoutManager(this));// 用全限定类名 或者 当前的方法
                rv_query_list.setAdapter(mAdapter);

                break;
            }
            case R.id.btn_sql_modify:{

                break;
            }
            case R.id.btn_sql_delete:{

                break;
            }
        }
    }

    private ArrayList<BillItem> getData(Cursor cursor){

        ArrayList<BillItem> list = new ArrayList<BillItem>();

        if(cursor.moveToFirst()){
            while(cursor.moveToNext()){
                BillItem item = new BillItem();
                item.content = cursor.getString(cursor.getColumnIndex(DBConstant.TS_CONTENT));
                item.price = cursor.getString(cursor.getColumnIndex(DBConstant.TS_PRICE));
                item.date = cursor.getString(cursor.getColumnIndex(DBConstant.TS_DATE));
                list.add(item);
            }
        } else {
            Toast.makeText(this, "没有数据耶", Toast.LENGTH_SHORT).show();
        }
        return list;
    }

    private void setData(){
//        if(list_bill == null){
//            list_bill = new ArrayList<BillItem>();
//        }

        for (int i = 0; i < 5; i++){
            BillItem item = new BillItem();
            item.content = "橙汁_" + i;
            item.price = 50.22 + "";
            item.date = "2013-01-02 18:20:22";
            mManager.insert(item);
//            list_bill.add(item);
        }
        for (int i = 5; i < 10; i++){
            BillItem item = new BillItem();
            item.content = "橙汁_" + i;
            item.price = 50.22 + "";
            item.date = "2013-02-02 18:20:22";
            mManager.insert(item);
//            list_bill.add(item);
        }

    }

}
