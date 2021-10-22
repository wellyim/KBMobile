package com.example.kbmobile;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;

public class ListAkunAdapter extends BaseAdapter {

    Context context;
    ArrayList _id,nama,nim,umur;
    LayoutInflater inflter;
    LinearLayout mainLayout;
    Activity activity;

    public ListAkunAdapter(Activity activity,Context context, ArrayList _id, ArrayList nama, ArrayList nim, ArrayList umur) {
        this.context = context;
        this._id = _id;
        this.nama = nama;
        this.nim = nim;
        this.umur = umur;
        this.activity = activity;
        inflter = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return _id.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @SuppressLint({"ViewHolder", "InflateParams"})
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        TextView _id_txt, nama_mhs_txt, nim_mhs_txt, umur_mhs_txt;
        view = inflter.inflate(R.layout.akun_row, null);

        _id_txt = view.findViewById(R.id._id);
        nama_mhs_txt = view.findViewById(R.id.nama_mhs);
        nim_mhs_txt = view.findViewById(R.id.nim_mhs);
        umur_mhs_txt = view.findViewById(R.id.umur_mhs);
        mainLayout = view.findViewById(R.id.mainLayout);
        _id_txt.setText(String.valueOf(_id.get(i)));
        nama_mhs_txt.setText(String.valueOf(nama.get(i)));
        nim_mhs_txt.setText(String.valueOf(nim.get(i)));
        umur_mhs_txt.setText(String.valueOf(umur.get(i)));

        mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, _id.get(i).toString(), Toast.LENGTH_SHORT).show();
                Intent int_updatemhs = new Intent(context, UpdateAkun.class);
                int_updatemhs.putExtra("idmhs", String.valueOf(_id.get(i)));
                int_updatemhs.putExtra("namamhs", String.valueOf(nama.get(i)));
                int_updatemhs.putExtra("nimmhs", String.valueOf(nim.get(i)));
                int_updatemhs.putExtra("umurmhs", String.valueOf(umur.get(i)));
                activity.startActivityForResult(int_updatemhs, 1);
            }
        });

        return view;
    }
}
