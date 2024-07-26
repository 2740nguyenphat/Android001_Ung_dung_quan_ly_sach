package com.example.trannguyenphat.database.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DocGia {
    public DocGia(int id_doc_gia, String ten_doc_gia, int ngay_sinh, String email, int so_dien_thoai, String gioi_tinh, String dia_chi, String ten_nguoi_dung) {
        this.id_doc_gia = id_doc_gia;
        this.ten_doc_gia = ten_doc_gia;
        this.ngay_sinh = ngay_sinh;
        this.email = email;
        this.so_dien_thoai = so_dien_thoai;
        this.gioi_tinh = gioi_tinh;
        this.dia_chi = dia_chi;
        this.ten_nguoi_dung = ten_nguoi_dung;
    }
    public DocGia(){

    }

    private int id_doc_gia;
    private  String ten_doc_gia;
    private long ngay_sinh;
    private  String email;
    private  int so_dien_thoai;

    public int getId_doc_gia() {
        return id_doc_gia;
    }

    public void setId_doc_gia(int id_doc_gia) {
        this.id_doc_gia = id_doc_gia;
    }

    public String getTen_doc_gia() {
        return ten_doc_gia;
    }

    public void setTen_doc_gia(String ten_doc_gia) {
        this.ten_doc_gia = ten_doc_gia;
    }

    public long getNgay_sinh() {
        return ngay_sinh;
    }

    public void setNgay_sinh(long ngay_sinh) {
        this.ngay_sinh = ngay_sinh;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getSo_dien_thoai() {
        return so_dien_thoai;
    }

    public void setSo_dien_thoai(int so_dien_thoai) {
        this.so_dien_thoai = so_dien_thoai;
    }

    public String getGioi_tinh() {
        return gioi_tinh;
    }

    public void setGioi_tinh(String gioi_tinh) {
        this.gioi_tinh = gioi_tinh;
    }

    public String getDia_chi() {
        return dia_chi;
    }

    public void setDia_chi(String dia_chi) {
        this.dia_chi = dia_chi;
    }

    public String getTen_nguoi_dung() {
        return ten_nguoi_dung;
    }

    public void setTen_nguoi_dung(String ten_nguoi_dung) {
        this.ten_nguoi_dung = ten_nguoi_dung;
    }

    private  String gioi_tinh;
    private String dia_chi;
    private String ten_nguoi_dung;

    public String getFormattedNgaySinh() {
        Date date = new Date(ngay_sinh * 1000L);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        return sdf.format(date);
    }
}
