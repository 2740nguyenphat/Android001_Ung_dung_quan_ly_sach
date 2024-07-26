package com.example.trannguyenphat.fragment.admin;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.trannguyenphat.R;
import com.example.trannguyenphat.adapter.DocGiaAdapter;
import com.example.trannguyenphat.database.QuanLyThuVienDataBase;
import com.example.trannguyenphat.database.dao.DocGiaDAO;
import com.example.trannguyenphat.database.model.DocGia;

import java.util.ArrayList;

public class QuanLyDocGiaFragment extends Fragment {
    ListView listView;
    SearchView searchView;
    DocGiaAdapter adapter;
    QuanLyThuVienDataBase db;
    ArrayList<DocGia> docGias;
    DocGiaDAO docGiaDAO;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quan_ly_doc_gia, container, false);
        ControlMapping(view);
        SetEvent();
        return view;
    }

    private void SetEvent() {
        LoadDATA();
        Search();
    }

    private void Search() {
        db = new QuanLyThuVienDataBase(getActivity().getBaseContext());
        docGiaDAO = new DocGiaDAO(db);

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
        ArrayList<DocGia> docGiaArrayList = docGiaDAO.searchDocGia(query);

        DocGiaAdapter giaAdapter = new DocGiaAdapter(getActivity().getBaseContext(), R.layout.listview_docgias_item, docGiaArrayList);
        listView.setAdapter(giaAdapter);
    }

    private void LoadDATA() {
        docGias = new ArrayList<>();
        adapter = new DocGiaAdapter(getActivity().getBaseContext(), R.layout.listview_docgias_item, docGias);
        listView.setAdapter(adapter);
        db = new QuanLyThuVienDataBase(getActivity().getBaseContext());
        docGiaDAO = new DocGiaDAO(db);
        docGias.clear();
        docGiaDAO.getAllData(docGias);
        adapter.notifyDataSetChanged();
    }

    private void ControlMapping(View view) {
        listView = view.findViewById(R.id.lsvDanhSachDocGia);
        searchView = view.findViewById(R.id.search_docgias);
    }
}