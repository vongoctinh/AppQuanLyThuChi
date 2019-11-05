package com.poly.appquanlythuchi.Model;

public class KhoanChi {
    private int id;
    private String khoanchi;
    private String ngaychi;
    private String sotien;

    public KhoanChi(int id, String khoanchi, String ngaychi, String sotien) {
        this.id = id;
        this.khoanchi = khoanchi;
        this.ngaychi = ngaychi;
        this.sotien = sotien;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKhoanchi() {
        return khoanchi;
    }

    public void setKhoanchi(String khoanchi) {
        this.khoanchi = khoanchi;
    }

    public String getNgaychi() {
        return ngaychi;
    }

    public void setNgaychi(String ngaychi) {
        this.ngaychi = ngaychi;
    }

    public String getSotien() {
        return sotien;
    }

    public void setSotien(String sotien) {
        this.sotien = sotien;
    }
}
