package com.example.trannguyenphat.database.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.trannguyenphat.database.QuanLyThuVienDataBase;
import com.example.trannguyenphat.database.model.DocGia;
import com.example.trannguyenphat.database.model.Sach;

import java.util.ArrayList;

public class DocGiaDAO {
    private QuanLyThuVienDataBase dbHelper;
    SQLiteDatabase db;
    String TABLE_DOCGIA = "docgia";
    public DocGiaDAO(QuanLyThuVienDataBase dbHelper) {
        this.dbHelper = dbHelper;
    }

    public void getAllData(ArrayList<DocGia> docGias){
        db = dbHelper.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_DOCGIA;
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.moveToFirst()){
            do {
                DocGia docGia = new DocGia();
                docGia.setId_doc_gia(cursor.getInt(cursor.getColumnIndex("id_doc_gia")));
                docGia.setTen_doc_gia(cursor.getString(cursor.getColumnIndex("ten_doc_gia")));
                docGia.setNgay_sinh(cursor.getInt(cursor.getColumnIndex("ngay_sinh")));
                docGia.setEmail(cursor.getString(cursor.getColumnIndex("email")));
                docGia.setSo_dien_thoai(cursor.getInt(cursor.getColumnIndex("so_dien_thoai")));
                docGia.setGioi_tinh(cursor.getString(cursor.getColumnIndex("gioi_tinh")));
                docGia.setDia_chi(cursor.getString(cursor.getColumnIndex("dia_chi")));
                docGia.setTen_nguoi_dung(cursor.getString(cursor.getColumnIndex("ten_nguoi_dung")));
                docGias.add(docGia);
            }while (cursor.moveToNext());
        }
    }
    public DocGia getDocGiaByUsername(String username) {
        DocGia docGia = null;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM docgia INNER JOIN users ON docgia.ten_nguoi_dung = users.ten_nguoi_dung WHERE users.ten_nguoi_dung = ?", new String[]{username});
        if (cursor.moveToFirst()) {
            docGia = new DocGia();
            docGia.setId_doc_gia(cursor.getInt(cursor.getColumnIndex("id_doc_gia")));
            docGia.setTen_doc_gia(cursor.getString(cursor.getColumnIndex("ten_doc_gia")));
            docGia.setEmail(cursor.getString(cursor.getColumnIndex("email")));
            docGia.setSo_dien_thoai(cursor.getInt(cursor.getColumnIndex("so_dien_thoai")));
            docGia.setGioi_tinh(cursor.getString(cursor.getColumnIndex("gioi_tinh")));
            docGia.setDia_chi(cursor.getString(cursor.getColumnIndex("dia_chi")));
            docGia.setTen_nguoi_dung(cursor.getString(cursor.getColumnIndex("ten_nguoi_dung")));
            docGia.setNgay_sinh(cursor.getLong(cursor.getColumnIndex("ngay_sinh")));
        }
        cursor.close();
        return docGia;
    }
    public long deleteDocGia(int idDocGia){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        return  db.delete("docgia", "id_doc_gia = ?", new String[]{String.valueOf(idDocGia)});
    }

    public ArrayList<DocGia> searchDocGia(String query){
        ArrayList<DocGia> docGias = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_DOCGIA +
                " WHERE ten_doc_gia LIKE ? OR ngay_sinh LIKE ? OR email LIKE ?" +
                " OR so_dien_thoai LIKE ? OR gioi_tinh LIKE ? OR dia_chi LIKE ?";

        String[] selectionArgs = new String[] {"%" + query + "%", "%" + query + "%", "%" + query + "%",
                "%" + query + "%", "%" + query + "%", "%" + query + "%"};

        Cursor cursor = db.rawQuery(selectQuery, selectionArgs);
        if (cursor.moveToFirst()){
            do{
                DocGia docGia = new DocGia();
                docGia.setId_doc_gia(cursor.getInt(cursor.getColumnIndex("id_doc_gia")));
                docGia.setTen_doc_gia(cursor.getString(cursor.getColumnIndex("ten_doc_gia")));
                docGia.setNgay_sinh(cursor.getInt(cursor.getColumnIndex("ngay_sinh")));
                docGia.setEmail(cursor.getString(cursor.getColumnIndex("email")));
                docGia.setSo_dien_thoai(cursor.getInt(cursor.getColumnIndex("so_dien_thoai")));
                docGia.setGioi_tinh(cursor.getString(cursor.getColumnIndex("gioi_tinh")));
                docGia.setDia_chi(cursor.getString(cursor.getColumnIndex("dia_chi")));
                docGia.setTen_nguoi_dung(cursor.getString(cursor.getColumnIndex("ten_nguoi_dung")));
                docGias.add(docGia);
            } while (cursor.moveToNext());
        }
        return docGias;
    }

    public long insertDocGia(DocGia docGia) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("ten_doc_gia", docGia.getTen_doc_gia());
        values.put("ngay_sinh", docGia.getNgay_sinh());
        values.put("email", docGia.getEmail());
        values.put("so_dien_thoai", docGia.getSo_dien_thoai());
        values.put("gioi_tinh", docGia.getGioi_tinh());
        values.put("dia_chi", docGia.getDia_chi());

        long id = db.insert("docgia", null, values);
        db.close();
        return id;
    }
}
