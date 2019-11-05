package com.poly.appquanlythuchi.Model;

public class KhoanThu {
    private int Id;
    private String tenthu;
    private String ngaythu;
    private Integer sotien;

    public KhoanThu(int id, String tenthu, String ngaythu, Integer sotien) {
        Id = id;
        this.tenthu = tenthu;
        this.ngaythu = ngaythu;
        this.sotien = sotien;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTenthu() {
        return tenthu;
    }

    public void setTenthu(String tenthu) {
        this.tenthu = tenthu;
    }

    public String getNgaythu() {
        return ngaythu;
    }

    public void setNgaythu(String ngaythu) {
        this.ngaythu = ngaythu;
    }

    public Integer getSotien() {
        return sotien;
    }

    public void setSotien(Integer sotien) {
        this.sotien = sotien;
    }
}
