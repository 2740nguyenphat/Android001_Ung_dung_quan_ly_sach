package com.example.trannguyenphat.database.model;

public class PhieuMuon {
    public PhieuMuon(int id_phieu_muon, String id_doc_gia, long ngay_lap) {
        this.id_phieu_muon = id_phieu_muon;
        this.id_doc_gia = id_doc_gia;
        this.ngay_lap = ngay_lap;
    }
    public PhieuMuon(){

    }

    public int getId_phieu_muon() {
        return id_phieu_muon;
    }

    public void setId_phieu_muon(int id_phieu_muon) {
        this.id_phieu_muon = id_phieu_muon;
    }

    public String getId_doc_gia() {
        return id_doc_gia;
    }

    public void setId_doc_gia(String id_doc_gia) {
        this.id_doc_gia = id_doc_gia;
    }

    public long getNgay_lap() {
        return ngay_lap;
    }

    public void setNgay_lap(long ngay_lap) {
        this.ngay_lap = ngay_lap;
    }

    private int id_phieu_muon;
    private String id_doc_gia;
    private long ngay_lap;
}
