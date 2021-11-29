package com.example.kbmobile.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class database extends SQLiteOpenHelper {

    Context context;
    private static final String dbname = "masterakun.db";
    private static final int dbversion = 1;
    private static final String tbl_mhs_ = "master_akun";
    private static final String tbl_mhs_id = "_id";
    private static final String tbl_mhs_nama = "nama";
    private static final String tbl_mhs_nim = "nim";
    private static final String tbl_mhs_umur = "umur";

    public database(@Nullable Context context) {
        super(context, dbname, null, dbversion);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String create_table_mhs_ = "CREATE TABLE IF NOT EXISTS " + tbl_mhs_ + " ( "
                + tbl_mhs_id + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + tbl_mhs_nama + " VARCHAR,"
                + tbl_mhs_nim + " VARCHAR,"
                + tbl_mhs_umur + " VARCHAR);";
        try {
            sqLiteDatabase.execSQL(create_table_mhs_);
            Toast.makeText(context, "Database Berhasil Dibuat",
                    Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(context, "Database Gagal Dibuat",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + tbl_mhs_);
        onCreate(sqLiteDatabase);
    }

    public void insertData(String nama, String nim, String umur) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(tbl_mhs_nama, nama);
        cv.put(tbl_mhs_nim, nim);
        cv.put(tbl_mhs_umur, umur);
        long hasil = sqLiteDatabase.insert(tbl_mhs_, null, cv);
        if (hasil == -1) {
            Toast.makeText(context, "Gagal Menyimpan Data",
                    Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Berhasil Menyimpan Data",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public Cursor SemuaMahasiswa(){
        String sql_mahasiswa = "SELECT * FROM " + tbl_mhs_;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(sql_mahasiswa, null);
        }

        return  cursor;
    }

    public void updataMahasiswa(String id_row, String nama, String nim, String umur){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(tbl_mhs_nama,nama);
        cv.put(tbl_mhs_nim,nim);
        cv.put(tbl_mhs_umur,umur);
        long hasil = db.update(tbl_mhs_,cv,"_id=?",new String[]{id_row});
        if(hasil == -1){
            Toast.makeText(context,"Gagal Menyimpan Data Update", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context,nama, Toast.LENGTH_SHORT).show();
        }
    }

    public void DeleteMahasiswa(String id_row){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(tbl_mhs_, "_id=?", new String[]{id_row});

        if(result == -1){
            Toast.makeText(context, "Data AKUN Gagal dihapus", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Data AKUN Berhasil dihapus", Toast.LENGTH_SHORT).show();
        }
    }



}
