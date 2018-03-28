package com.yizhen.testui.sqlite;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Administrator on 2018/3/27.
 */

public class DBHelper extends SQLiteOpenHelper {


    private static int VERSION = 5;

    public DBHelper(Context context) {
        super(context, DBConstant.DBNAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table "+ DBConstant.T_TRANSATIONS
                + " ("+ DBConstant.TS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + DBConstant.TS_CONTENT +" TEXT, "
                + DBConstant.TS_PRICE + " TEXT, "
                + DBConstant.TS_DATE + " TEXT);";
        Log.e("zhen", "sql="+sql);
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.e("zhen", "执行=onUpgrade");
        String sql3 = "drop table if exists " + DBConstant.T_TRANSATIONS + ";";
        db.execSQL(sql3);
        onCreate(db);
    }
}
