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

import com.example.trannguyenphat.classes.UserSession;
import com.example.trannguyenphat.database.QuanLyThuVienDataBase;
import com.example.trannguyenphat.database.dao.UserDAO;
import com.example.trannguyenphat.database.model.NguoiDung;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    Button btnDangNhap;
    EditText etUserName, etPassword;
    TextView txtWrapDangKy;
    QuanLyThuVienDataBase dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        dbHelper = new QuanLyThuVienDataBase(this);
        ControlMapping();
        SetEvent();
    }

    private void SetEvent() {
        txtWrapDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DangKyActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uname = etUserName.getText().toString();
                String upass = etPassword.getText().toString();

                UserDAO userDAO = new UserDAO(dbHelper);
                NguoiDung nguoiDung = userDAO.getUserByUNameAndPass(uname, upass);

                if (nguoiDung != null){
                    if(Objects.equals(nguoiDung.getVai_tro(), "admin"))
                    {
                        Toast.makeText(getBaseContext(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        UserSession.getInstance().setUsername(uname);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(MainActivity.this, TrangChuActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }, 1000);
                    } else if(Objects.equals(nguoiDung.getVai_tro(), "user"))
                    {
                        Toast.makeText(getBaseContext(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        UserSession.getInstance().setUsername(uname);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(MainActivity.this, TrangChuUserActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }, 1000);
                    }
                }else {
                    Toast.makeText(MainActivity.this, "Tên đăng nhập hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void ControlMapping() {
        btnDangNhap = findViewById(R.id.btnDangNhap);
        etUserName = findViewById(R.id.etUserName);
        etPassword = findViewById(R.id.etPassword);
        txtWrapDangKy = findViewById(R.id.txtWrapDangKy);
    }
}