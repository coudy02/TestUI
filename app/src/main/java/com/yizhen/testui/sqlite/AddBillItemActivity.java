package com.yizhen.testui.sqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.yizhen.testui.R;

public class AddBillItemActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edt_content;
    private EditText edt_price;
    private EditText edt_date;
    private Button btn_submit;
    private Button btn_submit_more;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bill_item);

        edt_content = (EditText) findViewById(R.id.edt_content);
        edt_price = (EditText) findViewById(R.id.edt_price);
        edt_date = (EditText) findViewById(R.id.edt_date);
        btn_submit = (Button) findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(this);
        btn_submit_more = (Button) findViewById(R.id.btn_submit_more);
        btn_submit_more.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_submit:{

                break;
            }
            case R.id.btn_submit_more:{


                break;
            }
        }
    }
}
