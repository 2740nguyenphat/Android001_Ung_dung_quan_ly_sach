package com.example.trannguyenphat.database.model;

public class NguoiDung {
    public NguoiDung(String ten_nguoi_dung, String mat_khau, String vai_tro) {
        this.ten_nguoi_dung = ten_nguoi_dung;
        this.mat_khau = mat_khau;
        this.vai_tro = vai_tro;
    }
    public NguoiDung(){

    }

    private String ten_nguoi_dung;

    public String getTen_nguoi_dung() {
        return ten_nguoi_dung;
    }

    public void setTen_nguoi_dung(String ten_nguoi_dung) {
        this.ten_nguoi_dung = ten_nguoi_dung;
    }

    public String getMat_khau() {
        return mat_khau;
    }

    public void setMat_khau(String mat_khau) {
        this.mat_khau = mat_khau;
    }

    public String getVai_tro() {
        return vai_tro;
    }

    public void setVai_tro(String vai_tro) {
        this.vai_tro = vai_tro;
    }

    private String mat_khau;
    private String vai_tro;
}
