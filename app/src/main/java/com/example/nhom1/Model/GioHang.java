package com.example.nhom1.Model;

public class GioHang {
    int MaGioHang;
    int maSanPham;
    String imgSP;
    String tenSP;
    int soLuong;
    String size;
    String mau;
    double donGia;



    public GioHang(int maGioHang, int maSanPham, int soLuong, String size, String mau, double donGia) {
        MaGioHang = maGioHang;
        this.maSanPham = maSanPham;
        this.soLuong = soLuong;
        this.size = size;
        this.mau = mau;
        this.donGia = donGia;
    }



    public GioHang(int maGioHang, int maSanPham, String imgSP, String tenSP, int soLuong, String size, String mau, double donGia) {

        MaGioHang = maGioHang;
        this.maSanPham = maSanPham;
        this.imgSP = imgSP;
        this.tenSP = tenSP;
        this.soLuong = soLuong;
        this.size = size;
        this.mau = mau;
        this.donGia = donGia;
    }

    public String getImgSP() {
        return imgSP;
    }

    public void setImgSP(String imgSP) {
        this.imgSP = imgSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public int getMaGioHang() {
        return MaGioHang;
    }

    public void setMaGioHang(int maGioHang) {
        MaGioHang = maGioHang;
    }

    public int getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(int maSanPham) {
        this.maSanPham = maSanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getMau() {
        return mau;
    }

    public void setMau(String mau) {
        this.mau = mau;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }
}
