package com.example.quanlytinhluong.Interface.TotalSalary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.quanlytinhluong.Adapter.AdapterLuongNV;
import com.example.quanlytinhluong.Adapter.AdapterThongKe;
import com.example.quanlytinhluong.Database.DBNhanVien;
import com.example.quanlytinhluong.Model.ThongKe;
import com.example.quanlytinhluong.R;

import java.util.ArrayList;

public class MainActivity_LuongNV extends AppCompatActivity {

    ListView lvLuongNV;
    ArrayList<ThongKe> thongke = new ArrayList<>();
    AdapterLuongNV adapterLuongNV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_luong_nv);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        lvLuongNV = findViewById(R.id.lvLuongNV);
        String manv = getIntent().getExtras().getString("manv");
        final DBNhanVien dbNhanVien = new DBNhanVien(getApplicationContext());
        thongke = dbNhanVien.layDSLuongNV(manv);

        adapterLuongNV = new AdapterLuongNV(this, R.layout.custom_luong_nv, thongke);
        lvLuongNV.setAdapter(adapterLuongNV);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
        }
        return true;
    }
}