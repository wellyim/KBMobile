package com.example.kbmobile;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.kbmobile.database.database;

import java.util.ArrayList;

public class DaftarAkun extends AppCompatActivity {

    database myDb;
    Button tambah;
    ArrayList<String> _id, nama, nim, umur;
    ListView listMahasiwa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_akun);
        listMahasiwa = findViewById(R.id.listMahasiwa);
        tambah = findViewById(R.id.tambah);


        myDb = new database(DaftarAkun.this);
        _id = new ArrayList<>();
        nama = new ArrayList<>();
        nim = new ArrayList<>();
        umur = new ArrayList<>();
        displayData();

        ListAkunAdapter ListAkunAdapter = new ListAkunAdapter(DaftarAkun.this,this, _id, nama, nim, umur);
        listMahasiwa.setAdapter(ListAkunAdapter);

        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DaftarAkun.this, MainActivity.class);
                startActivity(i);
            }
        });


    }

    private void displayData() {
        Cursor cursor = myDb.SemuaMahasiswa();
        if(cursor.getCount() == 0){
            Toast.makeText(this,"Data Mahasiswa Kosong!", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                _id.add(cursor.getString(0));
                nama.add(cursor.getString(1));
                nim.add(cursor.getString(2));
                umur.add(cursor.getString(3));
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            recreate();
        }
    }
}