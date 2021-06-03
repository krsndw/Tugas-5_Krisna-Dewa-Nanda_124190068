package com.example.roomexample.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.roomexample.BukuAdapter;
import com.example.roomexample.R;
import com.example.roomexample.database.AppDatabase;
import com.example.roomexample.database.BukuModel;

import java.util.ArrayList;

public class ReadActivity extends AppCompatActivity {
    private BukuAdapter bukuAdapter;
    private RecyclerView rvBuku;
    private AppDatabase appDatabase;
    private ArrayList<BukuModel> listBuku = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);

        rvBuku = findViewById(R.id.readactivity_rv_buku);
        bukuAdapter = new BukuAdapter(getApplicationContext());
        bukuAdapter.notifyDataSetChanged();

        if (appDatabase == null) {
            appDatabase = AppDatabase.iniDatabase(getApplicationContext());
        }

        listBuku.addAll(appDatabase.bukuDAO().getBuku());
        bukuAdapter.setData(listBuku);

        rvBuku.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rvBuku.setAdapter(bukuAdapter);
    }
}