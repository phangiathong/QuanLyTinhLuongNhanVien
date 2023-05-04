package com.example.quanlytinhluong.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.quanlytinhluong.Model.NhanVien;
import com.example.quanlytinhluong.Model.PhongBan;

import java.util.ArrayList;

public class DBNhanVien {

    DBHelper dbHelper;

    public DBNhanVien(Context context) {
        this.dbHelper = new DBHelper(context);
    }

    public void Them(NhanVien nhanVien) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("manv", nhanVien.getMaNV());
        values.put("tennv", nhanVien.getTenNV());
        values.put("ngaysinh", nhanVien.getNgaySinh());
        values.put("gioitinh", nhanVien.getGioiTinh());
        values.put("mapb", nhanVien.getMaPhong());
        values.put("hesoluong", nhanVien.getBacLuong());
        values.put("imagenv", nhanVien.getImage());
        db.insert("NhanVien", null, values);
        db.close();
    }

    public String layMaPhong(String tenPhong) {
        String maPhong = "";
        String sql = "SELECT mapb FROM PhongBan WHERE tenpb LIKE \"%" + tenPhong + "%\" ";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        try {
            cursor.moveToFirst();
            do {
                PhongBan phongBan = new PhongBan();
                phongBan.setMaPhong(cursor.getString(0));
                maPhong = phongBan.getMaPhong();
            }
            while (cursor.moveToNext());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return maPhong;
    }

    public boolean checkMaNhanVien(String manv) {
        boolean check = false;
        String sql = "SELECT count(*) FROM NhanVien WHERE manv LIKE \""+manv+"\" ";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        int count  = cursor.getInt(0);
        if(count > 0) {
            check = true;
        }
        return check;
    }

    public ArrayList<NhanVien> LayDSNhanVien() {
        ArrayList<NhanVien> data = new ArrayList<>();
        String sql = "select * from NhanVien";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        try {
            cursor.moveToFirst();
            do {
                NhanVien nhanVien = new NhanVien();
                nhanVien.setMaNV(cursor.getString(0));
                nhanVien.setTenNV(cursor.getString(1));
                nhanVien.setNgaySinh(cursor.getString(2));
                nhanVien.setGioiTinh(cursor.getString(3));
                nhanVien.setMaPhong(cursor.getString(4));
                nhanVien.setBacLuong(cursor.getString(5));
                nhanVien.setImage(cursor.getBlob(6));
                Log.d("nv", nhanVien + "");
                data.add(nhanVien);
            }
            while (cursor.moveToNext());
            db.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return data;
    }

    public boolean checkXoaNhanVienChamCong(String maNV) {
        boolean check = false;
        String sql = "SELECT count(*) FROM ChamCong WHERE manv LIKE \"%" + maNV + "%\" ";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        if (count > 0) {
            check = true;
        }
        return check;
    }

    public boolean checkXoaNhanVienTamUng(String maNV) {
        boolean check = false;
        String sql = "SELECT count(*) FROM TamUng WHERE manv LIKE \"%" + maNV + "%\" ";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        if (count > 0) {
            check = true;
        }
        return check;
    }

    public ArrayList<NhanVien> LayNhanVien(String manv) {
        ArrayList<NhanVien> data = new ArrayList<>();
        String sql = "select * from NhanVien where manv ='" + manv + "'";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        try {
            cursor.moveToFirst();
            do {
                NhanVien nhanVien = new NhanVien();
                nhanVien.setMaNV(cursor.getString(0));
                nhanVien.setTenNV(cursor.getString(1));
                nhanVien.setNgaySinh(cursor.getString(2));
                nhanVien.setGioiTinh(cursor.getString(3));
                nhanVien.setMaPhong(cursor.getString(4));
                nhanVien.setBacLuong(cursor.getString(5));
                nhanVien.setImage(cursor.getBlob(6));
                data.add(nhanVien);
            }
            while (cursor.moveToNext());
            db.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }


        return data;
    }

    public void Sua(NhanVien nhanVien) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("manv", nhanVien.getMaNV());
        values.put("tennv", nhanVien.getTenNV());
        values.put("ngaysinh", nhanVien.getNgaySinh());
        values.put("gioitinh", nhanVien.getGioiTinh());
        values.put("mapb", nhanVien.getMaPhong());
        values.put("hesoluong", nhanVien.getBacLuong());
        values.put("imagenv", nhanVien.getImage());
        db.update("NhanVien", values, "manv ='" + nhanVien.getMaNV() + "'", null);
        db.close();
    }

    public void Xoa(NhanVien nhanVien) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete("NhanVien", "manv ='" + nhanVien.getMaNV() + "'", null);
        db.close();
    }
}
