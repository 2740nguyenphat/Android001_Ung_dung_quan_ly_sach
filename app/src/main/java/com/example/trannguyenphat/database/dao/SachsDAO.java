package com.example.trannguyenphat.database.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.trannguyenphat.database.QuanLyThuVienDataBase;
import com.example.trannguyenphat.database.model.Sach;

import java.util.ArrayList;

public class SachsDAO {
    QuanLyThuVienDataBase dbHelper;
    SQLiteDatabase db;

    String TABLE_SACHS = "sachs";
    String TABLE_LOAISACH = "loaisach";

    public SachsDAO(QuanLyThuVienDataBase dbHelper){
        this.dbHelper = dbHelper;
    }

    public void getAllData(ArrayList<Sach> saches){
        db = dbHelper.getReadableDatabase();
        String sql = "select id_sach, ten_sach, tac_gia, nha_xuat_ban,s.loai_sach, ls.ten_loai_sach, nam_xuat_ban, trang_thai, image, ghi_chu " +
                "from " + TABLE_SACHS + " s join " + TABLE_LOAISACH + " ls on s.loai_sach=ls.id_loai_sach";
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.moveToFirst()){
            do {
                Sach sach = new Sach();
                sach.setId_sach(cursor.getInt(cursor.getColumnIndex("id_sach")));
                sach.setTen_sach(cursor.getString(cursor.getColumnIndex("ten_sach")));
                sach.setTac_gia(cursor.getString(cursor.getColumnIndex("tac_gia")));
                sach.setNha_xuat_ban(cursor.getString(cursor.getColumnIndex("nha_xuat_ban")));
                sach.setLoai_sach(cursor.getInt(cursor.getColumnIndex("loai_sach")));
                sach.setTenLoaiSach(cursor.getString(cursor.getColumnIndex("ten_loai_sach")));
                sach.setNam_xuat_ban(cursor.getInt(cursor.getColumnIndex("nam_xuat_ban")));
                sach.setTrang_thai(cursor.getString(cursor.getColumnIndex("trang_thai")));
                sach.setImage(cursor.getString(cursor.getColumnIndex("image")));
                sach.setGhi_chu(cursor.getString(cursor.getColumnIndex("ghi_chu")));
                saches.add(sach);
            }while (cursor.moveToNext());
        }
    }

    public ArrayList<Sach> searchBooks(String query) {
        ArrayList<Sach> saches = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();


        String selectQuery = "SELECT id_sach, ten_sach, tac_gia, nha_xuat_ban, ls.ten_loai_sach, nam_xuat_ban, trang_thai, image, ghi_chu " +
                "FROM " + TABLE_SACHS + " s JOIN " + TABLE_LOAISACH + " ls ON s.loai_sach=ls.id_loai_sach " +
                "WHERE ten_sach LIKE ? OR tac_gia LIKE ? OR nha_xuat_ban LIKE ? OR ls.ten_loai_sach LIKE ? OR ls.ten_loai_sach LIKE ?";

        String[] selectionArgs = new String[] {"%" + query + "%", "%" + query + "%", "%" + query + "%", "%" + query + "%", "%" + query + "%"};

        Cursor cursor = db.rawQuery(selectQuery, selectionArgs);
        if(cursor.moveToFirst()){
            do {
                Sach sach = new Sach();
                sach.setId_sach(cursor.getInt(cursor.getColumnIndex("id_sach")));
                sach.setTen_sach(cursor.getString(cursor.getColumnIndex("ten_sach")));
                sach.setTac_gia(cursor.getString(cursor.getColumnIndex("tac_gia")));
                sach.setNha_xuat_ban(cursor.getString(cursor.getColumnIndex("nha_xuat_ban")));
                sach.setTenLoaiSach(cursor.getString(cursor.getColumnIndex("ten_loai_sach")));
                sach.setNam_xuat_ban(cursor.getInt(cursor.getColumnIndex("nam_xuat_ban")));
                sach.setTrang_thai(cursor.getString(cursor.getColumnIndex("trang_thai")));
                sach.setImage(cursor.getString(cursor.getColumnIndex("image")));
                sach.setGhi_chu(cursor.getString(cursor.getColumnIndex("ghi_chu")));
                saches.add(sach);
            } while (cursor.moveToNext());
        }
        return saches;
    }
    public long insertSach(Sach sach) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("ten_sach", sach.getTen_sach());
        values.put("tac_gia", sach.getTac_gia());
        values.put("nha_xuat_ban", sach.getNha_xuat_ban());
        values.put("nam_xuat_ban", sach.getNam_xuat_ban());
        values.put("loai_sach", sach.getLoai_sach());
        values.put("trang_thai", sach.getTrang_thai());
        values.put("image", sach.getImage());
        values.put("ghi_chu", sach.getGhi_chu());

        long id = db.insert("sachs", null, values);
        db.close();
        return id;
    }

    public int updateSach(Sach sach) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("ten_sach", sach.getTen_sach());
        values.put("tac_gia", sach.getTac_gia());
        values.put("nha_xuat_ban", sach.getNha_xuat_ban());
        values.put("nam_xuat_ban", sach.getNam_xuat_ban());
        values.put("loai_sach", sach.getLoai_sach());
        values.put("trang_thai", sach.getTrang_thai());
        values.put("image", sach.getImage());
        values.put("ghi_chu", sach.getGhi_chu());

        // Cập nhật dữ liệu dựa trên id của sách
        String selection = "id_sach = ?";
        String[] selectionArgs = {String.valueOf(sach.getId_sach())};

        int rowsUpdated = db.update(TABLE_SACHS, values, selection, selectionArgs);
        db.close();
        return rowsUpdated;
    }

    public long deleteSach(int idSach) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        return db.delete("sachs", "id_sach = ?", new String[]{String.valueOf(idSach)});
    }
}
