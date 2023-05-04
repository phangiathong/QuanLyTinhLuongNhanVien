package com.example.quanlytinhluong.Database;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import androidx.viewpager.widget.PagerAdapter;

public class DBAccount {
    DBHelper dbHelper;

    public DBAccount(Context context) {
        this.dbHelper = new DBHelper(context);
    }

    public void themAccount(String username, String password, String phone, onClickListenerRs onClickListener) {
        String sql = "Select * from Account where username = '" + username + "'";
        if (dbHelper.getReadableDatabase().rawQuery(sql, null).getCount() == 0) {
            String sqlInsert = "Insert into Account values ('" + username + "','" + password + "','" + phone + "')";
            dbHelper.getWritableDatabase().execSQL(sqlInsert);
            onClickListener.success();
        } else {
            onClickListener.fail();
        }
    }

    public void checkLogin(String username, onClickListener password) {
        String sql = "Select password from Account where username = '" + username + "'";
        Cursor cursor = dbHelper.getReadableDatabase().rawQuery(sql, null);
        if (cursor.getCount() == 0) {
            password.fail();
        } else {
            cursor.moveToFirst();
            password.success(cursor.getString(0));
        }
    }

    public void checkPhone(String phone, onClickListenerForgot onClickListener){
        String sql = "Select password from Account where phone = '" + phone + "'";
        Cursor cursor = dbHelper.getReadableDatabase().rawQuery(sql,null);
        if (cursor.getCount() == 0) {
            onClickListener.fail();
            Log.d("loi","Loi");
        }else {
            cursor.moveToFirst();
            onClickListener.success(cursor.getString(0));
            Log.d("ok","ok");
        }
    }

    public interface onClickListenerRs {
        void success();
        void fail();
    }

    public interface onClickListener {
        void success(String pass);
        void fail();
    }

    public interface onClickListenerForgot {
        void success(String pass);
        void fail();
    }
}
