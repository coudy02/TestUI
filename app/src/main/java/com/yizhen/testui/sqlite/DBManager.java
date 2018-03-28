package com.yizhen.testui.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.yizhen.testui.recyclerView.BillItem;

/**
 * Created by Administrator on 2018/3/28.
 */

public class DBManager {

    private DBHelper mHelper;

    public DBManager(Context context) {

        mHelper = new DBHelper(context); // 创建数据库和表

    }

    /**
     * 增加数据
     * @param values
     * @return
     */
    public long insert(ContentValues values){
        //拿数据库写的权限；
        SQLiteDatabase db = mHelper.getWritableDatabase();
//        insert(String table, String nullColumnHack, ContentValues values)
        return db.insert(DBConstant.T_TRANSATIONS, null, values); // 假如 values 为null 的时候，才会调用 nullColumnHack
    }

    /**
     * 增加数据
     * @param item
     * @return
     */
    public long insert(BillItem item){
        //拿数据库写的权限；
        SQLiteDatabase db = mHelper.getWritableDatabase();
//        insert(String table, String nullColumnHack, ContentValues values)
        ContentValues values = new ContentValues();
        values.put(DBConstant.TS_CONTENT, item.content);
        values.put(DBConstant.TS_PRICE, item.price);
        values.put(DBConstant.TS_DATE, item.date);
        return db.insert(DBConstant.T_TRANSATIONS, null, values); // 假如 values 为null 的时候，才会调用 nullColumnHack
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public int delete(int id){
        //拿数据库写的权限；
        SQLiteDatabase db = mHelper.getWritableDatabase();
//        delete(String table, String whereClause, String[] whereArgs)
        String whereClause = DBConstant.TS_ID + "=?";
        String[] whereArgs = new String[]{id+""};
        return db.delete(DBConstant.T_TRANSATIONS, whereClause, whereArgs);
    }

    /**
     * 修改
     * @param id
     * @param values
     * @return
     */
    public int modify(int id, ContentValues values){
        SQLiteDatabase db = mHelper.getWritableDatabase();
//        update(String table, ContentValues values, String whereClause, String[] whereArgs)
        String whereClause = DBConstant.TS_ID + "=?"; // ? 占位符
        String[] whereArgs = new String[]{id+""};
        return db.update(DBConstant.T_TRANSATIONS, values, whereClause, whereArgs);
    }

    /**
     * 查询一个
     * @param id
     * @return
     */
    public Cursor queryOne(int id){
        SQLiteDatabase db = mHelper.getReadableDatabase();
//        public Cursor rawQuery(String sql, String[] selectionArgs)
        String sql = "select * from "+ DBConstant.T_TRANSATIONS + "where "+ DBConstant.TS_ID + "=?";
        String[] selectionArgs = new String[]{id+""};
        return db.rawQuery(sql, selectionArgs);
    }

    /**
     * 查询全部
     * @return
     */
    public Cursor queryAll(){
        SQLiteDatabase db = mHelper.getReadableDatabase();
//        public Cursor rawQuery(String sql, String[] selectionArgs)
        String sql = "select * from "+ DBConstant.T_TRANSATIONS;
        return db.rawQuery(sql, null);
    }

}
