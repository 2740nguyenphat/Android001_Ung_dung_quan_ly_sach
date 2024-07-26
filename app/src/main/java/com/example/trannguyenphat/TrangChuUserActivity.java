package com.example.trannguyenphat;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.trannguyenphat.classes.UserSession;
import com.example.trannguyenphat.database.QuanLyThuVienDataBase;
import com.example.trannguyenphat.database.dao.DocGiaDAO;
import com.example.trannguyenphat.database.model.DocGia;
import com.example.trannguyenphat.fragment.admin.HomeFragment;
import com.example.trannguyenphat.fragment.admin.QuanLyDocGiaFragment;
import com.example.trannguyenphat.fragment.admin.QuanLyPhieuMuonFragment;
import com.example.trannguyenphat.fragment.admin.QuanLySachFragment;
import com.example.trannguyenphat.fragment.admin.QuanLyTaiKhoanFragment;
import com.example.trannguyenphat.fragment.users.HomeUserFragment;
import com.example.trannguyenphat.fragment.users.PhieuMuonFragment;
import com.example.trannguyenphat.fragment.users.QuanLyThongTinCaNhanFragment;
import com.google.android.material.navigation.NavigationView;

public class TrangChuUserActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout mDrawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;
    private static final int FRAGMENT_HOME = 0;
    private static final int FRAGMENT_QLTTCN = 1;
    private static final int FRAGMENT_PM = 2;
    private int mCurrentFragment = FRAGMENT_HOME;

    TextView txtTenDocGia, txtEmail, txtSDT, txtNgaySinh;
    DocGiaDAO docGiaDAO;
    DocGia docGia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_trang_chu_user);
        ControlMapping();

        setSupportActionBar(toolbar);
        setTitle("Thư viện Hikari");
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        replaceFragment(new HomeUserFragment());

        fillDataToDocGia();
    }

    private void fillDataToDocGia() {
        docGiaDAO = new DocGiaDAO(new QuanLyThuVienDataBase(this));
        String username = UserSession.getInstance().getUsername();
        docGia = docGiaDAO.getDocGiaByUsername(username);

        if (docGia != null) {
            txtTenDocGia.setText(docGia.getTen_doc_gia());
            String email = docGia.getEmail();
            String sdt = String.valueOf(docGia.getSo_dien_thoai());
            String ns = docGia.getFormattedNgaySinh();
            if(email == null || email.isEmpty())
            {
                txtEmail.setText("Chưa cập nhật email");
            }else {
                txtEmail.setText(email);
            }

            if(sdt.equals("0") || email.isEmpty())
            {
                txtSDT.setText("Chưa cập nhật số điện thoại");
            }else {
                txtSDT.setText(sdt);
            }

            if(ns.equals("01-01-1970") || email.isEmpty())
            {
                txtNgaySinh.setText("Chưa cập nhật ngày sinh");
            }else {
                txtNgaySinh.setText(ns);
            }
        }
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frameU, fragment);
        transaction.commit();
    }

    private void ControlMapping() {
        mDrawerLayout = findViewById(R.id.drawer_layout_user);
        toolbar = findViewById(R.id.toolBarU);
        navigationView = findViewById(R.id.navigation_viewU);

        View viewHeader = navigationView.getHeaderView(0);
        txtTenDocGia = viewHeader.findViewById(R.id.txtTenDocGia);
        txtEmail = viewHeader.findViewById(R.id.txtEmail);
        txtSDT = viewHeader.findViewById(R.id.txtSDT);
        txtNgaySinh = viewHeader.findViewById(R.id.txtNgaySinh);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.iHomeU){
            if (mCurrentFragment != FRAGMENT_HOME){
                setTitle("Thư viện Hikari");
                replaceFragment(new HomeUserFragment());
                mCurrentFragment = FRAGMENT_HOME;
            }
        } else if(id == R.id.iQLTTCN){
            if (mCurrentFragment != FRAGMENT_QLTTCN){
                setTitle("Thông tin cá nhân");
                replaceFragment(new QuanLyThongTinCaNhanFragment());
                mCurrentFragment = FRAGMENT_QLTTCN;
            }
        } else if (id == R.id.iQLPM) {
            if (mCurrentFragment != FRAGMENT_PM){
                setTitle("Phiếu muợn");
                replaceFragment(new PhieuMuonFragment());
                mCurrentFragment = FRAGMENT_PM;
            }
        } else if (id == R.id.iLogOutU){
            finish();
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }
    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)){
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }
}