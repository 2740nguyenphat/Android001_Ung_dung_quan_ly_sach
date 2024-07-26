package com.example.trannguyenphat.database.model;

public class LoaiSach {
    public int getId_loai_sach() {
        return id_loai_sach;
    }
    public LoaiSach(){

    }

    public void setId_loai_sach(int id_loai_sach) {
        this.id_loai_sach = id_loai_sach;
    }

    public String getTen_loai_sach() {
        return ten_loai_sach;
    }

    public void setTen_loai_sach(String ten_loai_sach) {
        this.ten_loai_sach = ten_loai_sach;
    }

    private int id_loai_sach;
    private String ten_loai_sach;

    public LoaiSach(int id_loai_sach, String ten_loai_sach) {
        this.id_loai_sach = id_loai_sach;
        this.ten_loai_sach = ten_loai_sach;
    }
    @Override
    public String toString() {
        return ten_loai_sach;
    }
}
