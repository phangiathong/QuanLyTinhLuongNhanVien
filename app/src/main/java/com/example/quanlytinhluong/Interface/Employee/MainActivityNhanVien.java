package com.example.quanlytinhluong.Interface.Employee;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.quanlytinhluong.Adapter.AdapterNhanVien;
import com.example.quanlytinhluong.Database.DBNhanVien;
import com.example.quanlytinhluong.Interface.MainActivityHome;
import com.example.quanlytinhluong.Model.NhanVien;
import com.example.quanlytinhluong.R;

import java.util.ArrayList;

public class MainActivityNhanVien extends AppCompatActivity {

    Button btnThem;
    ListView lvDanhSachNV;
    AdapterNhanVien adapterNV;
    ArrayList<NhanVien> dataNV = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnThem=findViewById(R.id.btnThem);
        lvDanhSachNV = findViewById(R.id.lvNhanVien);

        HienThiDL();
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddNhanVien.class);
                startActivity(intent);
            }
        });
    }

    private void HienThiDL() {
        DBNhanVien dbNhanVien = new DBNhanVien(this);
        dataNV = dbNhanVien.LayDSNhanVien();
        adapterNV =new AdapterNhanVien(MainActivityNhanVien.this, R.layout.custom_nhanvien, dataNV);
        adapterNV.notifyDataSetChanged();
        lvDanhSachNV.setAdapter(adapterNV);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_actionbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id==R.id.logout) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivityNhanVien.this);
            builder.setTitle("Thông báo");
            builder.setMessage("Bạn có muốn thoát không ?");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            AlertDialog alertDialog1 = builder.create();
            alertDialog1.show();
        }else {
            Intent intent = new Intent(MainActivityNhanVien.this, MainActivityHome.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}