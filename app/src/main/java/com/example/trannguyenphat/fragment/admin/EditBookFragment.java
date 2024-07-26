package com.example.trannguyenphat.fragment.admin;

import static android.app.Activity.RESULT_OK;

import android.content.Context;
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
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.widget.Toolbar;

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

public class EditBookFragment extends Fragment {
    private static final int REQUEST_CODE_PICK_IMAGE = 1;
    Button btnSave;
    TextView txtTitle;
    ImageButton btnImage;
    EditText etTenSach, etTacGia, etNhaXB, etNamXB, etGhiChu;
    Spinner spnTheLoai, spnTrangThai;
    ToggleButton tgbEdit;
    QuanLyThuVienDataBase dbHelper;
    Sach sach;
    Uri selectedImageUri;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_book, container, false);
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
        tgbEdit.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    btnSave.setVisibility(View.VISIBLE);
                    etTacGia.setEnabled(true);
                    etNhaXB.setEnabled(true);
                    etGhiChu.setEnabled(true);
                    etNamXB.setEnabled(true);
                    etTenSach.setEnabled(true);



                    txtTitle.setText("CHỈNH SỬA THÔNG TIN SÁCH");


                    btnImage.setEnabled(true);
                    spnTheLoai.setEnabled(true);
                    spnTrangThai.setEnabled(true);
                } else {
                    btnSave.setVisibility(View.INVISIBLE);
                    etTacGia.setEnabled(false);
                    etNhaXB.setEnabled(false);
                    etGhiChu.setEnabled(false);
                    etNamXB.setEnabled(false);
                    etTenSach.setEnabled(false);


                    txtTitle.setText("XEM THÔNG TIN SÁCH");

                    btnImage.setEnabled(false);
                    spnTheLoai.setEnabled(false);
                    spnTrangThai.setEnabled(false);
                }
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy dữ liệu từ các trường nhập liệu
                String tensach = etTenSach.getText().toString();
                String tacgia = etTacGia.getText().toString();
                String nhaxb = etNhaXB.getText().toString();
                int namxb = Integer.parseInt(etNamXB.getText().toString());
                LoaiSach loaiSach = (LoaiSach) spnTheLoai.getSelectedItem();
                String ghichu = etGhiChu.getText().toString();
                String trangthai = spnTrangThai.getSelectedItem().toString();

                // Tạo một đối tượng Sach mới với dữ liệu này
                Sach sachMoi = new Sach();
                sachMoi.setId_sach(sach.getId_sach()); // Sử dụng ID của sách hiện tại
                sachMoi.setTen_sach(tensach);
                sachMoi.setTac_gia(tacgia);
                sachMoi.setNha_xuat_ban(nhaxb);
                sachMoi.setNam_xuat_ban(namxb);
                sachMoi.setLoai_sach(loaiSach.getId_loai_sach());
                sachMoi.setGhi_chu(ghichu);
                sachMoi.setTrang_thai(trangthai);

                // Nếu người dùng đã chọn một hình ảnh mới, lưu hình ảnh vào bộ nhớ nội bộ và lấy tên tệp
                if (selectedImageUri != null) {
                    String imageName = saveImageToInternalStorage(selectedImageUri);
                    sachMoi.setImage(imageName);
                } else {
                    // Nếu không, sử dụng hình ảnh hiện tại
                    sachMoi.setImage(sach.getImage());
                }

                // Cập nhật sách trong cơ sở dữ liệu
                SachsDAO sachsDAO = new SachsDAO(dbHelper);
                int result = sachsDAO.updateSach(sachMoi);

                if (result > 0) {
                    Toast.makeText(getActivity().getBaseContext(), "Cập nhật sách thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity().getBaseContext(), "Cập nhật sách thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });

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
            // Kiểm tra xem ảnh có nằm trong bộ nhớ trong không
            if (imageName.startsWith("img_")) {
                try {
                    InputStream inputStream = getActivity().openFileInput(imageName);
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    btnImage.setImageBitmap(bitmap);
                } catch (Exception e) {
                    e.printStackTrace();
                    // Xử lý ngoại lệ - có thể hiển thị ảnh mặc định
                }
            } else {
                // Kiểm tra xem ảnh có nằm trong drawable không
                int resId = getResources().getIdentifier(imageName, "drawable", getActivity().getPackageName());
                if (resId != 0) {
                    btnImage.setImageResource(resId);
                } else {
                    // Xử lý trường hợp ảnh không tìm thấy
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
            // So sánh ID trạng thái thay vì chuỗi
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
        txtTitle = view.findViewById(R.id.txtTitle);

        btnImage = view.findViewById(R.id.btnAddImg);
        btnSave = view.findViewById(R.id.btnSave);

        tgbEdit = view.findViewById(R.id.tgbEdit);

        etTenSach = view.findViewById(R.id.etNameBook);
        etTacGia = view.findViewById(R.id.etTacGia);
        etNhaXB = view.findViewById(R.id.etNhaXB);
        etNamXB = view.findViewById(R.id.etNamXB);
        etGhiChu = view.findViewById(R.id.etGhiChu);

        spnTheLoai = view.findViewById(R.id.spnTheLoai);
        spnTrangThai = view.findViewById(R.id.spnTrangThai);
    }
}