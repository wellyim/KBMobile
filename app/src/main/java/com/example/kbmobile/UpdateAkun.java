package com.example.kbmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kbmobile.database.database;

public class UpdateAkun extends AppCompatActivity {

    Button simpan, hapus;
    EditText input_nama, input_nim, input_umur;
    String idmhs, namamhs, nimmhs, umurmhs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_akun);

        simpan = findViewById(R.id.simpan);
        hapus = findViewById(R.id.hapus);
        input_nama = findViewById(R.id.input_nama);
        input_nim = findViewById(R.id.input_nim);
        input_umur = findViewById(R.id.input_umur);
        getIntentData();

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                namamhs = input_nama.getText().toString().trim();
                nimmhs  = input_nim.getText().toString().trim();
                umurmhs = input_umur.getText().toString().trim();
                database mydb = new database(UpdateAkun.this);
                mydb.updataMahasiswa(idmhs,namamhs,nimmhs,umurmhs);

                Intent updt = new Intent(UpdateAkun.this, DaftarAkun.class);
                startActivity(updt);
            }
        });

        hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database mydb = new database(UpdateAkun.this);
                mydb.DeleteMahasiswa(idmhs);

                Intent updt = new Intent(UpdateAkun.this, DaftarAkun.class);
                startActivity(updt);
            }
        });
    }
    private void getIntentData() {
        if(getIntent().hasExtra("idmhs") && getIntent().hasExtra("namamhs") && getIntent().hasExtra("nimmhs") && getIntent().hasExtra("umurmhs")){
            idmhs   = getIntent().getStringExtra("idmhs");
            namamhs = getIntent().getStringExtra("namamhs");
            nimmhs  = getIntent().getStringExtra("nimmhs");
            umurmhs = getIntent().getStringExtra("umurmhs");

            input_nama.setText(namamhs);
            input_nim.setText(nimmhs);
            input_umur.setText(umurmhs);

        }else{
            Toast.makeText(UpdateAkun.this, "Data Tidak Ditemukan",Toast.LENGTH_SHORT).show();
        }
    }
}