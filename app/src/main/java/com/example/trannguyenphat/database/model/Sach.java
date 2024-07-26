package com.example.trannguyenphat.database.model;

import java.io.Serializable;

public class Sach implements Serializable {
    private int id_sach;
    private String ten_sach;
    private String tac_gia;
    private String nha_xuat_ban;
    private int loai_sach;
    private String tenLoaiSach;
    private int nam_xuat_ban;
    private String trang_thai;
    private String image;
    private String ghi_chu;

    public Sach() {
    }

    public Sach(int id_sach, String ten_sach, String tac_gia, String nha_xuat_ban, int loai_sach, int nam_xuat_ban, String trang_thai, String image, String ghi_chu) {
        this.id_sach = id_sach;
        this.ten_sach = ten_sach;
        this.tac_gia = tac_gia;
        this.nha_xuat_ban = nha_xuat_ban;
        this.loai_sach = loai_sach;
        this.nam_xuat_ban = nam_xuat_ban;
        this.trang_thai = trang_thai;
        this.image = image;
        this.ghi_chu = ghi_chu;
    }

    public int getId_sach() {
        return id_sach;
    }

    public void setId_sach(int id_sach) {
        this.id_sach = id_sach;
    }

    public String getTen_sach() {
        return ten_sach;
    }

    public void setTen_sach(String ten_sach) {
        this.ten_sach = ten_sach;
    }

    public String getTac_gia() {
        return tac_gia;
    }

    public void setTac_gia(String tac_gia) {
        this.tac_gia = tac_gia;
    }

    public String getNha_xuat_ban() {
        return nha_xuat_ban;
    }

    public void setNha_xuat_ban(String nha_xuat_ban) {
        this.nha_xuat_ban = nha_xuat_ban;
    }

    public int getLoai_sach() {
        return loai_sach;
    }

    public void setLoai_sach(int loai_sach) {
        this.loai_sach = loai_sach;
    }

    public String getTenLoaiSach() {
        return tenLoaiSach;
    }

    public void setTenLoaiSach(String tenLoaiSach) {
        this.tenLoaiSach = tenLoaiSach;
    }

    public int getNam_xuat_ban() {
        return nam_xuat_ban;
    }

    public void setNam_xuat_ban(int nam_xuat_ban) {
        this.nam_xuat_ban = nam_xuat_ban;
    }

    public String getTrang_thai() {
        return trang_thai;
    }

    public void setTrang_thai(String trang_thai) {
        this.trang_thai = trang_thai;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getGhi_chu() {
        return ghi_chu;
    }

    public void setGhi_chu(String ghi_chu) {
        this.ghi_chu = ghi_chu;
    }
}
