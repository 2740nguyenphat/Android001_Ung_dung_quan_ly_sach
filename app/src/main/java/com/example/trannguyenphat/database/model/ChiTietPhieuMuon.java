package com.example.trannguyenphat.database.model;

public class ChiTietPhieuMuon {
    public ChiTietPhieuMuon(int id_chi_tiet_phieu_muon, int id_phieu_muon, String id_sach, long ngay_muon, long han_tra, String trang_thai) {
        this.id_chi_tiet_phieu_muon = id_chi_tiet_phieu_muon;
        this.id_phieu_muon = id_phieu_muon;
        this.id_sach = id_sach;
        this.ngay_muon = ngay_muon;
        this.han_tra = han_tra;
        this.trang_thai = trang_thai;
    }
    private int id_chi_tiet_phieu_muon;

    public int getId_chi_tiet_phieu_muon() {
        return id_chi_tiet_phieu_muon;
    }

    public void setId_chi_tiet_phieu_muon(int id_chi_tiet_phieu_muon) {
        this.id_chi_tiet_phieu_muon = id_chi_tiet_phieu_muon;
    }
public ChiTietPhieuMuon(){

}
    public int getId_phieu_muon() {
        return id_phieu_muon;
    }

    public void setId_phieu_muon(int id_phieu_muon) {
        this.id_phieu_muon = id_phieu_muon;
    }

    public String getId_sach() {
        return id_sach;
    }

    public void setId_sach(String id_sach) {
        this.id_sach = id_sach;
    }

    public long getNgay_muon() {
        return ngay_muon;
    }

    public void setNgay_muon(long ngay_muon) {
        this.ngay_muon = ngay_muon;
    }

    public long getHan_tra() {
        return han_tra;
    }

    public void setHan_tra(long han_tra) {
        this.han_tra = han_tra;
    }

    public String getTrang_thai() {
        return trang_thai;
    }

    public void setTrang_thai(String trang_thai) {
        this.trang_thai = trang_thai;
    }

    private int id_phieu_muon;
    private String id_sach;
    private long ngay_muon;
    private long han_tra;
    private String trang_thai;
}
