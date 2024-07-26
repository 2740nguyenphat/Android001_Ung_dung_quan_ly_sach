package com.example.trannguyenphat.fragment.users;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.trannguyenphat.R;
import com.example.trannguyenphat.database.QuanLyThuVienDataBase;
import com.example.trannguyenphat.database.dao.LoaiSachDAO;
import com.example.trannguyenphat.database.model.LoaiSach;
import com.example.trannguyenphat.database.model.Sach;

import java.io.InputStream;
import java.util.List;

public class ViewBookFragment extends Fragment {
    private static final int REQUEST_CODE_PICK_IMAGE = 1;
    ImageButton btnImage;
    EditText etTenSach, etTacGia, etNhaXB, etNamXB, etGhiChu;
    Spinner spnTheLoai, spnTrangThai;
    QuanLyThuVienDataBase dbHelper;
    Sach sach;
    Uri selectedImageUri;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_book, container, false);
        dbHelper = new QuanLyThuVienDataBase(getContext());
        ControlMapping(view);
        sach = (Sach) getArguments().getSerializable("sach");
        if(sach != null){
            fillData(sach);
        }
        btnImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, REQUEST_CODE_PICK_IMAGE);
            }
        });
        btnImage.setEnabled(false);
        spnTheLoai.setEnabled(false);
        spnTrangThai.setEnabled(false);
        SetEvent();
        return view;
    }

    private void SetEvent() {

    }

    private void fillData(Sach sach) {
        etTenSach.setText(sach.getTen_sach());
        etTacGia.setText(sach.getTac_gia());
        etNhaXB.setText(sach.getNha_xuat_ban());
        etNamXB.setText(String.valueOf(sach.getNam_xuat_ban()));
        etGhiChu.setText(sach.getGhi_chu());
        fillDatatospnLoaiSach();
        fillDatatospnTrangThai();
        fillDatatoImageBook();
    }

    private void fillDatatoImageBook() {
        String imageName = sach.getImage();
        if (imageName != null && !imageName.isEmpty()) {

            if (imageName.startsWith("img_")) {
                try {
                    InputStream inputStream = getActivity().openFileInput(imageName);
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    btnImage.setImageBitmap(bitmap);
                } catch (Exception e) {
                    e.printStackTrace();

                }
            } else {

                int resId = getResources().getIdentifier(imageName, "drawable", getActivity().getPackageName());
                if (resId != 0) {
                    btnImage.setImageResource(resId);
                } else {

                }
            }
        }
    }

    private void fillDatatospnTrangThai() {
        String[] trangThaiArray = new String[]{"Sẵn có", "Đã mượn", "Mất"};
        ArrayAdapter<String> adaptertt = new ArrayAdapter<>(
                getContext(),
                android.R.layout.simple_spinner_item,
                trangThaiArray
        );
        spnTrangThai.setAdapter(adaptertt);

        String trangthai = sach.getTrang_thai();

        int position = -1;
        for (int i = 0; i < adaptertt.getCount(); i++) {

            if (trangthai.equals(adaptertt.getItem(i))) {
                position = i;
                break;
            }
        }

        if (position != -1) {
            spnTrangThai.setSelection(position);
        }
    }

    private void fillDatatospnLoaiSach() {
        LoaiSachDAO loaiSachDAO = new LoaiSachDAO(dbHelper);
        List<LoaiSach> loaiSachList = loaiSachDAO.getAllData();

        ArrayAdapter<LoaiSach> adapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, loaiSachList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnTheLoai.setAdapter(adapter);

        int loaiSachId = sach.getLoai_sach();
        int position = -1;
        for (int i = 0; i < adapter.getCount(); i++) {
            if (adapter.getItem(i).getId_loai_sach() == loaiSachId) {
                position = i;
                break;
            }
        }

        if (position != -1) {
            spnTheLoai.setSelection(position);
        }
    }

    private void ControlMapping(View view) {
        btnImage = view.findViewById(R.id.btnAddImg);

        etTenSach = view.findViewById(R.id.etNameBook);
        etTacGia = view.findViewById(R.id.etTacGia);
        etNhaXB = view.findViewById(R.id.etNhaXB);
        etNamXB = view.findViewById(R.id.etNamXB);
        etGhiChu = view.findViewById(R.id.etGhiChu);

        spnTheLoai = view.findViewById(R.id.spnTheLoai);
        spnTrangThai = view.findViewById(R.id.spnTrangThai);
    }
}