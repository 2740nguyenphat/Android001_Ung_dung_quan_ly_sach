package com.example.trannguyenphat.database.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.trannguyenphat.database.QuanLyThuVienDataBase;
import com.example.trannguyenphat.database.model.LoaiSach;

import java.util.ArrayList;
import java.util.List;

public class LoaiSachDAO {
    private QuanLyThuVienDataBase dbHelper;
    public LoaiSachDAO(QuanLyThuVienDataBase dbHelper) {
        this.dbHelper = dbHelper;
    }

    public List<LoaiSach> getAllData(){
        List<LoaiSach> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM loaisach", null);
        if(cursor.moveToFirst()){
            do {
                int id = cursor.getInt(0);
                String tenloaisach = cursor.getString(1);
                list.add(new LoaiSach(id, tenloaisach));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }
}
