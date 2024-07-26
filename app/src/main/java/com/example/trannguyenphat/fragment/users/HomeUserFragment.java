package com.example.trannguyenphat.fragment.users;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.trannguyenphat.R;
import com.example.trannguyenphat.adapter.SachAdapter;
import com.example.trannguyenphat.adapter.SachUserAdapter;
import com.example.trannguyenphat.database.QuanLyThuVienDataBase;
import com.example.trannguyenphat.database.dao.SachsDAO;
import com.example.trannguyenphat.database.model.Sach;
import com.example.trannguyenphat.fragment.admin.EditBookFragment;

import java.util.ArrayList;

public class HomeUserFragment extends Fragment {
    ListView listView;
    SearchView searchView;
    SachUserAdapter adapter;
    QuanLyThuVienDataBase db;
    ArrayList<Sach> saches;
    SachsDAO sachsDAO;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_user, container, false);
        ControlMapping(view);
        SetEvent();
        return view;
    }

    private void SetEvent() {
        LoadALLDATA();
        Search();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Sach sach = saches.get(position);

                ViewBookFragment fragment = new ViewBookFragment();
                Bundle args = new Bundle();
                args.putSerializable("sach", sach);
                fragment.setArguments(args);

                getFragmentManager().beginTransaction()
                        .replace(R.id.content_frameU, fragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    private void Search() {
        db = new QuanLyThuVienDataBase(getActivity().getBaseContext());
        sachsDAO = new SachsDAO(db);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                performSearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                performSearch(newText);
                return false;
            }
        });
    }

    private void performSearch(String query) {
        ArrayList<Sach> sachArrayList = sachsDAO.searchBooks(query);

        saches.clear(); // Xóa dữ liệu cũ trong danh sách saches
        saches.addAll(sachArrayList); // Thêm kết quả tìm kiếm vào danh sách saches
        adapter.notifyDataSetChanged(); // Thông báo cho adapter rằng dữ liệu đã thay đổi
    }

    private void LoadALLDATA() {
        saches = new ArrayList<>();
        adapter = new SachUserAdapter(getActivity().getBaseContext(), R.layout.listview_sachs_item_user, saches);
        listView.setAdapter(adapter);
        db = new QuanLyThuVienDataBase(getActivity().getBaseContext());
        sachsDAO = new SachsDAO(db);
        saches.clear();
        sachsDAO.getAllData(saches);
        adapter.notifyDataSetChanged();
    }

    private void ControlMapping(View view) {
        listView = view.findViewById(R.id.lsvDanhSachU);
        searchView = view.findViewById(R.id.search_sachsU);
    }
}