package com.example.nhom1.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    public static final String DB_NAME="duan1";
    public static final int DB_VERSION=1;
    public DbHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableLoaiSanPham = "CREATE TABLE LOAISANPHAM(id_loaisp Integer PRIMARY KEY AUTOINCREMENT, ten_loaisp Text);";
        db.execSQL(createTableLoaiSanPham);
        db.execSQL("INSERT INTO LOAISANPHAM VALUES(1, 'ao ret'), (2, 'ao'), (3, 'quan');");

        String createTableUser = "CREATE TABLE USER(id_user Integer PRIMARY KEY AUTOINCREMENT, ten_user Text, password text, sdt Text, diachi Text);";
        db.execSQL(createTableUser);

        String createTableSanPham = "CREATE TABLE SANPHAM(id_sp Integer PRIMARY KEY AUTOINCREMENT, id_loaisp Integer REFERENCES LOAISANPHAM(id_loaisp), anh_sp BLOG, ten_sp Text, mota_sp Text, giatien_sp double );";
        db.execSQL(createTableSanPham);

        String createTableGioHang = "CREATE TABLE GIOHANG(id_giohang Integer, id_sp Integer REFERENCES SANPHAM(id_sp), soluong Integer, kichco Text, dongia double);";
        db.execSQL(createTableGioHang);

        String createTableHoaDon = "CREATE TABLE HOADON(id_hoadon Integer PRIMARY KEY AUTOINCREMENT, id_user Integer REFERENCES LOAISANPHAM(id_user), id_giohang Integer, tenkhachhang Text, ngayinhoadon Text);";
        db.execSQL(createTableHoaDon);

        String createTableChiTietHoaDon = "CREATE TABLE CHTIETDONHANG(id_chitiet Integer PRIMARY KEY AUTOINCREMENT, id_hoadon Integer REFERENCES HOADON(id_hoadon), id_user Integer REFERENCES USER(id_user), ten_user Text, tenkhachhang Text, ngayinhoadon text,id_sp Integer, soluong Integer, kichco Text, dongia double, tongtien double);";
        db.execSQL(createTableChiTietHoaDon);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropLoaiSP ="drop table if exists LOAISANPHAM";
        db.execSQL(dropLoaiSP);
        String dropUser = "drop table if exists USER";
        db.execSQL(dropUser);
        String dropSanPham = "drop table if exists SANPHAM";
        db.execSQL(dropSanPham);
        String dropGioHang = "drop table if exists GIOHANG";
        db.execSQL(dropGioHang);
        String dropHoaDon = "drop table if exists HOADON";
        db.execSQL(dropHoaDon);
        String dropHoaDonChiTiet = "drop table if exists CHTIETDONHANG";
        db.execSQL(dropHoaDonChiTiet);
    }
}
