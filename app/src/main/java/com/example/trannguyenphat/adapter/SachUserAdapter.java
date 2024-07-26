package com.example.trannguyenphat.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.trannguyenphat.R;
import com.example.trannguyenphat.database.QuanLyThuVienDataBase;
import com.example.trannguyenphat.database.dao.SachsDAO;
import com.example.trannguyenphat.database.model.Sach;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Objects;

public class SachUserAdapter extends ArrayAdapter<Sach> {
    Context context;
    int layoutResourceID;
    ArrayList<Sach> data = null;
    QuanLyThuVienDataBase dbHelper;
    public SachUserAdapter(@NonNull Context context, int layoutResourceID, ArrayList<Sach> data) {
        super(context, layoutResourceID, data);
        this.context = context;
        this.layoutResourceID = layoutResourceID;
        this.data = data;
    }
    static class SachHolder{
        ImageView image_book,img_delete, img_trangthai;
        TextView txtBookName, txtTacGia, txtNamXB, txtTheLoai, txtNhaXB;
    }

    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        SachHolder holder = null;
        if(row != null){
            holder = (SachHolder) row.getTag();
        } else {
            holder = new SachHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            row = inflater.inflate(R.layout.listview_sachs_item_user, parent, false);

            holder.image_book = row.findViewById(R.id.image_book);
            holder.img_trangthai = row.findViewById(R.id.img_trangthai);
            holder.txtBookName = row.findViewById(R.id.txtBookName);
            holder.txtTacGia = row.findViewById(R.id.txtTacGia);
            holder.txtNamXB = row.findViewById(R.id.txtNamXB);
            holder.txtTheLoai = row.findViewById(R.id.txtTheLoai);
            holder.txtNhaXB = row.findViewById(R.id.txtNhaXB);
            holder.img_delete = row.findViewById(R.id.img_delete);
            row.setTag(holder);
        }
        final Sach sach = data.get(position);
            int imageId = context.getResources().getIdentifier(sach.getImage(), "drawable", context.getPackageName());
            if (imageId != 0) {
                Glide.with(context).load(imageId).into(holder.image_book); //Thêm ảnh sách vào bằng thư viện Glide
            } else {
                try {
                    FileInputStream fis = context.openFileInput(sach.getImage());
                    Bitmap bitmap = BitmapFactory.decodeStream(fis);
                    holder.image_book.setImageBitmap(bitmap);
                    fis.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


            String state = sach.getTrang_thai();
            if(Objects.equals(state, "Sẵn có")){
                holder.img_trangthai.setImageResource(R.drawable.ic_availability);
            } else if (Objects.equals(state, "Đã mượn")) {
                holder.img_trangthai.setImageResource(R.drawable.ic_borrowed);
            } else {
                holder.img_trangthai.setImageResource(R.drawable.ic_lost);
            }

            holder.txtBookName.setText(sach.getTen_sach());
            holder.txtTacGia.setText(sach.getTac_gia());
            holder.txtNamXB.setText(String.valueOf(sach.getNam_xuat_ban()));
            holder.txtTheLoai.setText(sach.getTenLoaiSach());
            holder.txtNhaXB.setText(sach.getNha_xuat_ban());
            holder.img_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dbHelper = new QuanLyThuVienDataBase(context);
                    SachsDAO sachsDAO = new SachsDAO(dbHelper);
                    long result = sachsDAO.deleteSach(sach.getId_sach());
                    if(result>0){
                        Toast.makeText(context, "Xóa sách thành công", Toast.LENGTH_SHORT).show();
                        data.remove(position);
                        notifyDataSetChanged();
                    }else {
                        Toast.makeText(context, "Xóa sách thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            return row;
    }
}
