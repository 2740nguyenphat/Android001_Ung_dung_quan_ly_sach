package com.example.trannguyenphat.database.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.trannguyenphat.database.QuanLyThuVienDataBase;
import com.example.trannguyenphat.database.model.DocGia;
import com.example.trannguyenphat.database.model.NguoiDung;

public class UserDAO {
    private QuanLyThuVienDataBase dbHelper;

    public UserDAO(QuanLyThuVienDataBase dbHelper) {
        this.dbHelper = dbHelper;
    }

    public NguoiDung getUserByUNameAndPass(String username, String pass){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE ten_nguoi_dung = ? AND mat_khau = ?", new String[]{username, pass});
        if(cursor.moveToFirst()) {
            NguoiDung nguoiDung = new NguoiDung();
            nguoiDung.setTen_nguoi_dung(cursor.getString(cursor.getColumnIndex("ten_nguoi_dung")));
            nguoiDung.setMat_khau(cursor.getString(cursor.getColumnIndex("mat_khau")));
            nguoiDung.setVai_tro(cursor.getString(cursor.getColumnIndex("vai_tro")));
            cursor.close();
            return nguoiDung;
        }
        return null;
    }

//    public DocGia getDocGiaByUserName(String username) {
//        DocGia docGia = null;
//        SQLiteDatabase db = dbHelper.getReadableDatabase();
//        Cursor cursor = db.rawQuery("SELECT * FROM docgia INNER JOIN users ON docgia.ten_nguoi_dung = users.ten_nguoi_dung WHERE users.ten_nguoi_dung = ?", new String[]{username});
//        if (cursor.moveToFirst()) {
//            docGia = new DocGia();
//            this.id_doc_gia = id_doc_gia;
//            this.ten_doc_gia = ten_doc_gia;
//            this.ngay_sinh = ngay_sinh;
//            this.email = email;
//            this.so_dien_thoai = so_dien_thoai;
//            this.gioi_tinh = gioi_tinh;
//            this.dia_chi = dia_chi;
//            this.ten_nguoi_dung = ten_nguoi_dung;
//            docGia.setId_doc_gia(cursor.getInt(cursor.getColumnIndex("id_doc_gia")));
//            docGia.setTen_doc_gia(cursor.getString(cursor.getColumnIndex("ten_doc_gia")));
//            docGia.setNgay_sinh(cursor.getString());
//
//        }
//        cursor.close();
//        return docGia;
//    }

    public void registerUser(String name, String username, String userpass, String role){
        NguoiDung nguoiDung = new NguoiDung();
        DocGia docGia = new DocGia();
        docGia.setTen_doc_gia(name);
        docGia.setTen_nguoi_dung(username);
        nguoiDung.setTen_nguoi_dung(username);
        nguoiDung.setMat_khau(userpass);
        nguoiDung.setVai_tro(role);
        addUser(nguoiDung);
        addDocGia(docGia);
    }

    private void addDocGia(DocGia docGia) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ten_doc_gia", docGia.getTen_doc_gia());
        values.put("ten_nguoi_dung", docGia.getTen_nguoi_dung());
        db.insert("docgia", null, values);
        db.close();
    }

    private void addUser(NguoiDung nguoiDung) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ten_nguoi_dung", nguoiDung.getTen_nguoi_dung());
        values.put("mat_khau", nguoiDung.getMat_khau());
        values.put("vai_tro", nguoiDung.getVai_tro());
        db.insert("users", null, values);
        db.close();
    }
    public boolean checkUserExists(String username) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE ten_nguoi_dung = ?", new String[]{username});
        boolean exists = (cursor.getCount() > 0);
        cursor.close();
        return exists;
    }
}
