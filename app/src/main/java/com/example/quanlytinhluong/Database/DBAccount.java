package com.example.quanlytinhluong.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.viewpager.widget.PagerAdapter;

import com.example.quanlytinhluong.Model.NhanVien;

public class DBAccount {
    DBHelper dbHelper;

    public DBAccount(Context context) {
        this.dbHelper = new DBHelper(context);
    }

    public void themAccount(String sdt, String password, String manv, onClickListenerRs onClickListener) {
        String sql = "Select * from Account where sdt = '" + sdt + "'";
        if (dbHelper.getReadableDatabase().rawQuery(sql, null).getCount() == 0) {
            String sqlInsert = "Insert into Account values ('" + sdt + "','" + password + "','" + manv + "')";
            dbHelper.getWritableDatabase().execSQL(sqlInsert);
            onClickListener.success();
        } else {
            onClickListener.fail();
        }
    }

    public void checkLogin(String sdt, onClickListener password) {
        String sql = "Select password from Account where sdt = '" + sdt + "'";
        Cursor cursor = dbHelper.getReadableDatabase().rawQuery(sql, null);
        if (cursor.getCount() == 0) {
            password.fail();
        } else {
            cursor.moveToFirst();
            password.success(cursor.getString(0));
        }
    }

    //Xóa nhân viên thì phải xóa luôn account
    public void XoaAccount(NhanVien nhanVien) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete("Account", "manv ='" + nhanVien.getMaNV() + "'", null);
        db.close();
    }

    //Quên mật khẩu
    public void checkManv(String manv, onClickListenerForgot onClickListener){
        String sql = "Select password from Account where manv = '" + manv + "'";
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
