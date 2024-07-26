package com.example.trannguyenphat.fragment.dialog;

import static android.app.Activity.RESULT_OK;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.service.controls.Control;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.trannguyenphat.R;
import com.example.trannguyenphat.database.QuanLyThuVienDataBase;
import com.example.trannguyenphat.database.dao.LoaiSachDAO;
import com.example.trannguyenphat.database.dao.SachsDAO;
import com.example.trannguyenphat.database.model.LoaiSach;
import com.example.trannguyenphat.database.model.Sach;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Random;

public class ThemSach extends DialogFragment {
    private static final int REQUEST_CODE_PICK_IMAGE = 1;
    Button btnSave;
    ImageButton btnImage;
    EditText etTenSach;
    EditText etTacGia;
    EditText etNhaXB;
    EditText etNamXB;
    EditText etGhiChu;
    Spinner spnTheLoai;
    QuanLyThuVienDataBase dbHelper;
    Uri selectedImageUri;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_themsach, null);
        dbHelper = new QuanLyThuVienDataBase(getContext());
        builder.setView(view);
        ControlMapping(view);
        SetEvent(view);
        getDataToSpn(view);
        return builder.create();
    }

    private void SetEvent(View view) {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tensach = etTenSach.getText().toString();
                String tacgia = etTacGia.getText().toString();
                String nhaxb = etNhaXB.getText().toString();
                int namxb = Integer.parseInt(etNamXB.getText().toString());
                LoaiSach loaiSach = (LoaiSach) spnTheLoai.getSelectedItem();
                String ghichu = etGhiChu.getText().toString();
                String trangthai = "Sẵn có";

                // Lưu hình ảnh vào bộ nhớ nội bộ và lấy tên tệp
                String imageName = saveImageToInternalStorage(selectedImageUri);

                Sach sach = new Sach();
                sach.setTen_sach(tensach);
                sach.setTac_gia(tacgia);
                sach.setNha_xuat_ban(nhaxb);
                sach.setNam_xuat_ban(namxb);
                sach.setLoai_sach(loaiSach.getId_loai_sach());
                sach.setGhi_chu(ghichu);
                sach.setImage(imageName);
                sach.setTrang_thai(trangthai);

                SachsDAO sachsDAO = new SachsDAO(dbHelper);
                long result = sachsDAO.insertSach(sach);

                if (result > 0) {
                    Toast.makeText(getActivity().getBaseContext(), "Thêm sách thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity().getBaseContext(), "Thêm sách thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, REQUEST_CODE_PICK_IMAGE);
            }
        });
    }

    private void ControlMapping(View view) {
        btnImage = view.findViewById(R.id.btnAddImg);
        btnSave = view.findViewById(R.id.btnSave);

        etTenSach = view.findViewById(R.id.etNameBook);
        etTacGia = view.findViewById(R.id.etTacGia);
        etNhaXB = view.findViewById(R.id.etNhaXB);
        etNamXB = view.findViewById(R.id.etNamXB);
        etGhiChu = view.findViewById(R.id.etGhiChu);

        spnTheLoai = view.findViewById(R.id.spnTheLoai);
    }

    private void getDataToSpn(View view) {
        LoaiSachDAO loaiSachDAO = new LoaiSachDAO(dbHelper);
        List<LoaiSach> loaiSachList = loaiSachDAO.getAllData();

        ArrayAdapter<LoaiSach> adapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, loaiSachList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spnTheLoai.setAdapter(adapter);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            selectedImageUri = data.getData();
            // Hiển thị hình ảnh đã chọn trên ImageButton
            btnImage.setImageURI(selectedImageUri);
        }
    }

    private String saveImageToInternalStorage(Uri imageUri) {
        try {
            // Mở luồng dữ liệu từ URI hình ảnh
            InputStream is = getActivity().getContentResolver().openInputStream(imageUri);

            // Tạo tên tệp ngẫu nhiên
            String fileName = "img_" + new Random().nextInt(1000000000) + ".jpg";

            // Mở luồng đầu ra tới tệp mới trong bộ nhớ nội bộ
            FileOutputStream fos = getActivity().openFileOutput(fileName, Context.MODE_PRIVATE);

            // Sao chép dữ liệu từ luồng đầu vào sang luồng đầu ra
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                fos.write(buffer, 0, length);
            }

            // Đóng các luồng
            fos.flush();
            fos.close();
            is.close();

            // Trả về tên tệp
            return fileName;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

