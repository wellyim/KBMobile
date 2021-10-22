package com.example.kbmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.kbmobile.database.database;

public class MainActivity extends AppCompatActivity {

    Button Simpan, lihatdata;
    EditText input_nama,input_nim,input_umur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Simpan = findViewById(R.id.simpan);
        input_nama = findViewById(R.id.nama_mhs);
        input_nim = findViewById(R.id.nim_mhs);
        input_umur = findViewById(R.id.umur_mhs);
        lihatdata = findViewById(R.id.Liat_list);

        Simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nama = input_nama.getText().toString();
                String nim =  input_nim.getText().toString();
                String umur = input_umur.getText().toString();
                database db = new database(MainActivity.this);
                db.insertData(nama,nim,umur);
            }
        });

        lihatdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, DaftarAkun.class);
                startActivity(i);
            }
        });
    }
}