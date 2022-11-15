package com.example.titulaundry.db_help;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.titulaundry.Dashboard.home_fragment;

import java.io.File;

public class Database_Tb_user extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "laundry.db";
    private static final int DATABASE_VERSION = 1;
    private SQLiteDatabase db;

    public Database_Tb_user(Context context) {
        super(context,DATABASE_NAME, null ,DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //table users
        String sql = "CREATE TABLE user(id integer primary key , nama text null, telp text null , email text null , password text null);";
        Log.d("Data","onCreate : "+sql);
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO user(id,nama,telp,email,password) VALUES('1','Puan Maharani','085851065295','puan@gmail.com','dprbangkit')";
        sqLiteDatabase.execSQL(sql);


        //table jasa
        String sql1 = "CREATE TABLE jasa(id_layanan text PRIMARY KEY, jenis_jasa text null,deskripsi text null,durasi text null , harga text null);";
        sqLiteDatabase.execSQL(sql1);
        Log.d("Data","onCreate : "+sql1);
        sql1 = "INSERT INTO jasa(id_layanan,jenis_jasa,deskripsi,durasi,harga) VALUES('js123','Cuci Kering','Cuci hingga kering tanpa setrika','1 hari','5000');";
        sqLiteDatabase.execSQL(sql1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS user");
        onCreate(db);
    }

    public Boolean checkUserNamePassword(String email , String password){
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("SELECT * FROM user WHERE email = ? AND password = ? ",new String[]{email,password});

        if (cursor.getCount()>0){
            return true;
        } else {
            return false;
        }
    }

    public boolean insertData(String id, String username, String telp, String email, String pw) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("id", id);
        cv.put("nama", username);
        cv.put("telp", telp);
        cv.put("email", email);
        cv.put("password", pw);
        long result = db.insert("user", null, cv);
        if (result == -1) {
            return false;
        } else {
            return true;
        }

    }


}