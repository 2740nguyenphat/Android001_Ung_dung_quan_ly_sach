package com.example.trannguyenphat;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.trannguyenphat.database.QuanLyThuVienDataBase;
import com.example.trannguyenphat.database.dao.UserDAO;
import com.example.trannguyenphat.database.model.NguoiDung;

public class DangKyActivity extends AppCompatActivity {
    Button btnDangKy;
    EditText etName, etUserName, etPassword, etRePassword;
    TextView txtWrapDangNhap;
    QuanLyThuVienDataBase dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dang_ky);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
        dbHelper = new QuanLyThuVienDataBase(this);
        ControlMapping();
        SetEvent();
    }

    private void SetEvent() {
        txtWrapDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DangKyActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                String uname = etUserName.getText().toString();
                String upass = etPassword.getText().toString();
                String repass = etRePassword.getText().toString();



                if(!upass.equals(repass)){
                    Toast.makeText(DangKyActivity.this, "Mật khẩu không khớp", Toast.LENGTH_SHORT).show();
                    return;
                }else if(uname.isEmpty() || upass.isEmpty() || repass.isEmpty() || name.isEmpty()){
                    Toast.makeText(DangKyActivity.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    return;
                } else{
                    UserDAO userDAO = new UserDAO(dbHelper);
                    if(userDAO.checkUserExists(uname))
                    {
                        Toast.makeText(DangKyActivity.this, "Tên đăng nhập đã tồn tại", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    userDAO.registerUser(name, uname,upass,"user");
                    Toast.makeText(DangKyActivity.this, "Đăng ký tài khoản thành công", Toast.LENGTH_SHORT).show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(DangKyActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    },1000);
                }
            }
        });
    }
    private void ControlMapping() {
        btnDangKy = findViewById(R.id.btnDangKy);
        etName = findViewById(R.id.etName);
        etUserName = findViewById(R.id.etUserName);
        etPassword = findViewById(R.id.etPassword);
        etRePassword = findViewById(R.id.etRePassword);
        txtWrapDangNhap = findViewById(R.id.txtWrapDangNhap);
    }
}