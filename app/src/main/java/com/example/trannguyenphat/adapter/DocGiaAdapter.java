package com.example.trannguyenphat.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.trannguyenphat.R;
import com.example.trannguyenphat.database.QuanLyThuVienDataBase;
import com.example.trannguyenphat.database.dao.DocGiaDAO;
import com.example.trannguyenphat.database.model.DocGia;
import com.example.trannguyenphat.database.model.Sach;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DocGiaAdapter extends ArrayAdapter<DocGia> {
    Context context;
    int layoutResourceID;
    ArrayList<DocGia> data = null;
    QuanLyThuVienDataBase dbHelper;
    public DocGiaAdapter(@NonNull Context context, int layoutResourceID, ArrayList<DocGia> data) {
        super(context, layoutResourceID, data);
        this.context = context;
        this.layoutResourceID = layoutResourceID;
        this.data = data;
    }
    static class DocGiaHoler{
        ImageView img_delete;
        TextView txtName, txtDate, txtEmail, txtNumPhone, txtGioiTinh, txtDiaChi;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        DocGiaHoler holer = null;
        if(row != null){
            holer = (DocGiaHoler) row.getTag();
        }else {
            holer = new DocGiaHoler();
            LayoutInflater inflater = LayoutInflater.from(context);
            row = inflater.inflate(R.layout.listview_docgias_item, parent, false);

            holer.txtName = row.findViewById(R.id.txtReaderNameLSVItems);
            holer.txtDate = row.findViewById(R.id.txtNgaySinhLSVItems);
            holer.txtEmail = row.findViewById(R.id.txtEmailLSVItems);
            holer.txtNumPhone = row.findViewById(R.id.txtSDTLSVItems);
            holer.txtGioiTinh = row.findViewById(R.id.txtGioiTinhLSVItems);
            holer.txtDiaChi = row.findViewById(R.id.txtDiaChiLSVItems);
            holer.img_delete = row.findViewById(R.id.img_delete);
            row.setTag(holer);
        }
        final DocGia docGia = data.get(position);
            holer.txtName.setText(docGia.getTen_doc_gia());
            holer.txtDate.setText(docGia.getFormattedNgaySinh());
            holer.txtEmail.setText(docGia.getEmail());
            holer.txtNumPhone.setText(String.valueOf(docGia.getSo_dien_thoai()));
            holer.txtGioiTinh.setText(docGia.getGioi_tinh());
            holer.txtDiaChi.setText(docGia.getDia_chi());
            holer.img_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dbHelper = new QuanLyThuVienDataBase(context);
                    DocGiaDAO docGiaDAO = new DocGiaDAO(dbHelper);
                    long result = docGiaDAO.deleteDocGia(docGia.getId_doc_gia());
                    if(result>0){
                        Toast.makeText(context, "Xóa độc giả thành công!", Toast.LENGTH_SHORT).show();
                        data.remove(position);
                        notifyDataSetChanged();
                    }else {
                        Toast.makeText(context, "Xóa độc giả thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        return row;
    }
}
