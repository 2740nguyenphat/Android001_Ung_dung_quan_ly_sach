package com.example.trannguyenphat;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.trannguyenphat.fragment.admin.HomeFragment;
import com.example.trannguyenphat.fragment.admin.QuanLyDocGiaFragment;
import com.example.trannguyenphat.fragment.admin.QuanLyPhieuMuonFragment;
import com.example.trannguyenphat.fragment.admin.QuanLySachFragment;
import com.example.trannguyenphat.fragment.admin.QuanLyTaiKhoanFragment;
import com.google.android.material.navigation.NavigationView;

public class TrangChuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout mDrawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;
    private static final int FRAGMENT_HOME = 0;
    private static final int FRAGMENT_QLSACH = 1;
    private static final int FRAGMENT_QLTK = 2;
    private static final int FRAGMENT_QLDG = 3;
    private static final int FRAGMENT_QLPM = 4;
    private int mCurrentFragment = FRAGMENT_HOME;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_trang_chu);
        ControlMapping();

        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        replaceFragment(new HomeFragment());
//        navigationView.getMenu().findItem(R.id.iHome).setChecked(true);
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();
        if (id == R.id.iHome){
            if (mCurrentFragment != FRAGMENT_HOME){
                setTitle("Trang chủ Quản lý thư viện");
                replaceFragment(new HomeFragment());
                mCurrentFragment = FRAGMENT_HOME;
            }
        } else if(id == R.id.iQLS){
            if (mCurrentFragment != FRAGMENT_QLSACH){
                setTitle("Quản lý sách");
                replaceFragment(new QuanLySachFragment());
                mCurrentFragment = FRAGMENT_QLSACH;
            }
        } else if (id == R.id.iQLTK) {
            if (mCurrentFragment != FRAGMENT_QLTK){
                setTitle("Quản lý tài khoản");
                replaceFragment(new QuanLyTaiKhoanFragment());
                mCurrentFragment = FRAGMENT_QLTK;
            }
        } else if (id == R.id.iQLDG) {
            if (mCurrentFragment != FRAGMENT_QLDG){
                setTitle("Quản lý độc giả");
                replaceFragment(new QuanLyDocGiaFragment());
                mCurrentFragment = FRAGMENT_QLDG;
            }
        } else if (id == R.id.iQLPM) {
            if(mCurrentFragment != FRAGMENT_QLPM){
                setTitle("Quản lý phiếu mượn");
                replaceFragment(new QuanLyPhieuMuonFragment());
                mCurrentFragment = FRAGMENT_QLPM;
            }
        } else if (id == R.id.iLogOut){
            finish();
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)){
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, fragment);
        transaction.commit();
    }

    private void ControlMapping() {
        mDrawerLayout = findViewById(R.id.drawer_layout_admin);
        toolbar = findViewById(R.id.toolBar);
        navigationView = findViewById(R.id.navigation_view);
    }
}