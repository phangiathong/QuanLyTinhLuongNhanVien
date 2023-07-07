package com.example.quanlytinhluong.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.quanlytinhluong.Model.ThongKe;
import com.example.quanlytinhluong.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class AdapterLuongNV extends ArrayAdapter {

    Context context;
    int resource;
    ArrayList<ThongKe> data;
    Locale localeVN = new Locale("vi", "VN");
    NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);

    public AdapterLuongNV(@NonNull Context context, int resource, ArrayList<ThongKe> data) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    private static class Holder {
        TextView tvThangLuongNV, tvTienCongNV, tvSoCongNV, tvTamUngNV, tvLuongCoBanNV, tvLuongThucLanhNV;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        AdapterLuongNV.Holder holder = null;
        if (view == null) {
            holder = new AdapterLuongNV.Holder();
            view = LayoutInflater.from(context).inflate(resource, null);

            holder.tvThangLuongNV = view.findViewById(R.id.tvThangLuongNV);
            holder.tvTienCongNV = view.findViewById(R.id.tvTienCongNV);
            holder.tvSoCongNV = view.findViewById(R.id.tvSoCongNV);
            holder.tvTamUngNV = view.findViewById(R.id.tvTamUngNV);
            holder.tvLuongCoBanNV = view.findViewById(R.id.tvLuongCoBanNV);
            holder.tvLuongThucLanhNV = view.findViewById(R.id.tvLuongThucLanhNV);

            view.setTag(holder);
        } else
            holder = (AdapterLuongNV.Holder) view.getTag();
        final ThongKe thongKe = data.get(position);
        holder.tvThangLuongNV.setText(thongKe.getNgayChamCong());
        holder.tvTienCongNV.setText(currencyVN.format(Integer.parseInt(thongKe.getHeSoLuong())));
        holder.tvSoCongNV.setText(thongKe.getSoNgayCong());

        String tamUngItem = thongKe.getTienTamUng();
        if (tamUngItem != null) {
            holder.tvTamUngNV.setText(currencyVN.format(Integer.parseInt(thongKe.getTienTamUng())));
        }else {
            tamUngItem = "0";
            holder.tvTamUngNV.setText(tamUngItem);
        }

        holder.tvLuongThucLanhNV.setText(thongKe.getLuongThucLanh());

        int luong = 0;
        int thucLanh = 0;
        int ngayCong = Integer.parseInt(thongKe.getSoNgayCong());
        int heSoLuong = Integer.parseInt(thongKe.getHeSoLuong());
        String tamUng = thongKe.getTienTamUng();

        luong = (heSoLuong * ngayCong);
        thongKe.setLuongCoBan(luong + "");
        holder.tvLuongCoBanNV.setText(currencyVN.format(Integer.parseInt(thongKe.getLuongCoBan())));

        if (tamUng != null) {
            int ung = Integer.parseInt(tamUng);
            thucLanh = luong - ung;
            Log.d("TamUng", thucLanh+"");

            thongKe.setLuongThucLanh(thucLanh + "");
        }else {
            tamUng = "0";
            int ung = Integer.parseInt(tamUng);
            thucLanh = luong - ung;

            Log.d("TamUng", thucLanh+"");
            thongKe.setLuongThucLanh(thucLanh + "");

        }

//        Locale localeVN = new Locale("vi", "VN");
//        currencyVN = NumberFormat.getCurrencyInstance(localeVN);

        holder.tvLuongThucLanhNV.setText(currencyVN.format(Integer.parseInt(thongKe.getLuongThucLanh())));

        return view;
    }


}
