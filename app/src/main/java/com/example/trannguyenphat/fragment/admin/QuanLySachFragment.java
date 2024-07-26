package com.example.trannguyenphat.fragment.admin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.trannguyenphat.R;
import com.example.trannguyenphat.database.QuanLyThuVienDataBase;
import com.example.trannguyenphat.adapter.SachAdapter;
import com.example.trannguyenphat.database.dao.SachsDAO;
import com.example.trannguyenphat.fragment.dialog.ThemSach;
import com.example.trannguyenphat.database.model.Sach;

import java.util.ArrayList;

public class QuanLySachFragment extends Fragment {
    ListView listView;
    SearchView searchView;
    SachAdapter adapter;
    QuanLyThuVienDataBase db;
    ArrayList<Sach> saches;
    SachsDAO sachsDAO;
Button btnAddnew;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quan_ly_sach, container, false);
        ControlMapping(view);
        SetEvent();
        return view;
    }

    private void SetEvent() {
        LoadALLDATA();
        Search();

        btnAddnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThemSach themSach = new ThemSach();
                themSach.show(getFragmentManager(), "ThemSach");
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Sach sach = saches.get(position);

                EditBookFragment fragment = new EditBookFragment();
                Bundle args = new Bundle();
                args.putSerializable("sach", sach);
                fragment.setArguments(args);

                getFragmentManager().beginTransaction()
                        .replace(R.id.content_frame, fragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    private void Search() {
        //Chỗ này chỉ là tạo database với logic tầng DAO
        db = new QuanLyThuVienDataBase(getActivity().getBaseContext());
        sachsDAO = new SachsDAO(db);

        //Chỗ này lắng nghe sự kiện người dùng nhập text vào ô tìm kiếm
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                performSearch(query); //Chỗ này là xử lý cho lần nhận text đầu tiên
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                performSearch(newText); //Chỗ này là xử lý khi người dùng thay đổi text
                return false;
            }
        });
    }
    private void performSearch(String query) {
        ArrayList<Sach> sachArrayList = sachsDAO.searchBooks(query); // Chỗ này là truyền dữ liệu xuống tầng DAO để cung cấp truy vấn

        saches.clear(); // Xóa dữ liệu cũ trong danh sách saches
        saches.addAll(sachArrayList); // Thêm kết quả tìm kiếm vào danh sách saches
        adapter.notifyDataSetChanged(); // Thông báo cho adapter rằng dữ liệu đã thay đổi
    }

    private void LoadALLDATA() {
        saches = new ArrayList<>();
        adapter = new SachAdapter(getActivity().getBaseContext(), R.layout.listview_sachs_item, saches);
        listView.setAdapter(adapter);
        db = new QuanLyThuVienDataBase(getActivity().getBaseContext());
        sachsDAO = new SachsDAO(db);
        saches.clear();
        sachsDAO.getAllData(saches);
        adapter.notifyDataSetChanged();
    }

    private void ControlMapping(View view) {
        listView = view.findViewById(R.id.lsvDanhSach);
        searchView = view.findViewById(R.id.search_sachs);
        btnAddnew = view.findViewById(R.id.btnAddNew);
    }
}
