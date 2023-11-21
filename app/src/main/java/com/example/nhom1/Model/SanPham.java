package com.example.nhom1.Model;

public class SanPham {
    int id;
    String image;
    String TenSanPham;
    Double Price;
    int MaLoai;
    String Mota;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SanPham(int id) {
        this.id = id;
    }



    public SanPham() {

    }

    public SanPham(int id, String image, String tenSanPham, Double price, int maLoai, String mota) {
        this.id = id;
        this.image = image;
        TenSanPham = tenSanPham;
        Price = price;
        MaLoai = maLoai;
        Mota = mota;
    }



    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTenSanPham() {
        return TenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        TenSanPham = tenSanPham;
    }

    public Double getPrice() {
        return Price;
    }

    public void setPrice(Double price) {
        Price = price;
    }

    public int getMaLoai() {
        return MaLoai;
    }

    public void setMaLoai(int maLoai) {
        MaLoai = maLoai;
    }

    public String getMota() {
        return Mota;
    }

    public void setMota(String mota) {
        Mota = mota;
    }
}
