package com.example.nhom1.DAOModel;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;


import com.example.nhom1.Model.SanPham;
import com.example.nhom1.Model.TheLoai;
import com.example.nhom1.database.DbHelper;

import java.util.ArrayList;

public class DAOSanPham {
    private SQLiteDatabase database;
    DbHelper dbHelper;

    public DAOSanPham(Context context) {
        dbHelper = new DbHelper(context);
        database = dbHelper.getWritableDatabase();
        database = dbHelper.getReadableDatabase();
    }

    public void insertData(String image, String TenSanPham, double Price, int MaLoai, String MoTa, int SoLuongSP) {
        String sql = "INSERT INTO SanPham VALUES (NULL, ?,?,?,?,?,?)";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1, image);
        statement.bindString(2, TenSanPham);
        statement.bindDouble(3, Price);
        statement.bindLong(4, MaLoai);
        statement.bindString(5, MoTa);
        statement.bindString(6,SoLuongSP+"");
        statement.executeInsert();
    }

    public void updateSanPham(String image, String TenSanPham, double Price, int MaLoai, String MoTa, int id, int SoLuongSP) {
        String sql = "UPDATE SanPham SET image = ?, TenSanPham = ?, Price = ?, MaLoai = ?, MoTa = ?,SoLuongSP=? WHERE MaSanPham =?";
        SQLiteStatement statement = database.compileStatement(sql);

        statement.bindString(1, image);
        statement.bindString(2, TenSanPham);
        statement.bindDouble(3, Price);
        statement.bindLong(4, MaLoai);
        statement.bindString(5, MoTa);
        statement.bindString(6, SoLuongSP+"");
        statement.bindDouble(7, (double) id);
        statement.execute();
        database.close();
    }

    public void deleteData(int id) {

        String sql = "DELETE FROM SanPham WHERE MaSanPham = ?";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindDouble(1, (double) id);
        statement.execute();
        database.close();
    }

    public ArrayList<SanPham> getAllProduct(int rdoCheck) {
        String sql = null;
        if (rdoCheck == 0) {
            sql = "SELECT * FROM SanPham";
        }
        if (rdoCheck == 1) {
            sql = "SELECT * FROM SanPham ORDER BY Price ASC";
        }
        if (rdoCheck == 2) {
            sql = "SELECT * FROM SanPham ORDER BY MaLoai ASC";
        }
        return getData(sql);
    }

    public SanPham getSPofMaSP(int maSp) {
        String sql = "Select * FROM SanPham WHERE SanPham.MaSanPham = ?";
        ArrayList<SanPham> list = getData(sql, String.valueOf(maSp));
        return list.get(0);
    }

    public ArrayList<SanPham> getSPofTL(int maLoai) {
        String sql = "Select * FROM SanPham WHERE SanPham.MaLoai = ?";
        ArrayList<SanPham> list = getData(sql, String.valueOf(maLoai));
        return list;
    }

    public ArrayList<SanPham> getData(String sql, String... selectionAGrs) {
        ArrayList<SanPham> list = new ArrayList<>();
        Cursor cursor = database.rawQuery(sql, selectionAGrs);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String image = cursor.getString(1);
            String name = cursor.getString(2);
            double price = cursor.getDouble(3);
            int maLoai = cursor.getInt(4);
            String moTa = cursor.getString(5);
            int soLuongSP=cursor.getInt(6);
            list.add(new SanPham(id, image, name, price, maLoai, moTa, soLuongSP));
        }
        return list;
    }
    // giam số lượng sản phẩm sau khi thanh toán
    @SuppressLint("Range")
    public void giamSoLuongSanPham(int maSanPham, int soLuongMua) {
        // Lấy số lượng hiện tại
        int soLuongHienTai = 0;
        String query = "SELECT SoLuongSP FROM SanPham WHERE MaSanPham = " + maSanPham;
        Cursor cursor = database.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            soLuongHienTai = cursor.getInt(cursor.getColumnIndex("SoLuongSP"));
        }
        cursor.close();

        // Kiểm tra xem có đủ số lượng để giảm không
        if (soLuongHienTai >= soLuongMua) {
            // Giảm số lượng đi
            int soLuongMoi = soLuongHienTai - soLuongMua;

            // Cập nhật số lượng trong cơ sở dữ liệu
            ContentValues values = new ContentValues();
            values.put("SoLuongSP", soLuongMoi);
            database.update("SanPham", values, "MaSanPham = ?", new String[]{String.valueOf(maSanPham)});
        }

        database.close();
    }

//    DAO LOẠI SẢN PHẨM

    //    Thêm Loại Sản phẩm
    public boolean addLSP(TheLoai theLoai) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tenLoai", theLoai.getTenLoai());
        long check = sqLiteDatabase.insert("THELOAI", null, contentValues);
        if (check == -1) {
            return false;
        } else {
            return true;
        }
    }

    //    Lấy danh sách Loại Sản phẩm
    public ArrayList<TheLoai> getDSLSP() {
        ArrayList<TheLoai> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM THELOAI", null);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                int maLoai = cursor.getInt(0);
                String tenLoai = cursor.getString(1);
                list.add(new TheLoai(maLoai, tenLoai));
            } while (cursor.moveToNext());
        }
        return list;
    }

    // Tìm kiếm sản phẩm

}
