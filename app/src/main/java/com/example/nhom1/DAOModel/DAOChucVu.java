package com.example.nhom1.DAOModel;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.example.nhom1.Model.ChucVu;
import com.example.nhom1.database.DbHelper;

import java.util.ArrayList;
import java.util.List;

public class DAOChucVu {
    private SQLiteDatabase database;
    DbHelper dbHelper;

    public DAOChucVu(Context context) {
        dbHelper = new DbHelper(context);
        database = dbHelper.getWritableDatabase();
        database = dbHelper.getReadableDatabase();
    }

    public List<ChucVu> getAllChucVu() {
        String sql = "SELECT * FROM ChucVu";
        return getData(sql);
    }

    public List<ChucVu> getData(String sql, String... selectionAGrs) {
        ArrayList<ChucVu> list = new ArrayList<>();
        Cursor cursor = database.rawQuery(sql, selectionAGrs);
        while (cursor.moveToNext()) {
            int id_chucVu = cursor.getInt(0);
            String tenChucVu = cursor.getString(1);
            list.add(new ChucVu(id_chucVu, tenChucVu));
        }
        return list;
    }
}
