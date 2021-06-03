package com.example.roomexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.roomexample.database.AppDatabase;
import com.example.roomexample.database.BukuModel;
import com.example.roomexample.view.ReadActivity;

public class MainActivity extends AppCompatActivity {
    private AppDatabase appDatabase;
    private Button btnSimpan, btnLihatData;
    private EditText etJudul, etPenulis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSimpan = findViewById(R.id.activitymain_btn_simpan);
        btnLihatData = findViewById(R.id.activitymain_btn_lihatdata);

        etJudul = findViewById(R.id.activitymain_et_judul);
        etPenulis = findViewById(R.id.activitymain_et_penulis);

        appDatabase = AppDatabase.iniDatabase(getApplicationContext());

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    BukuModel bukuModel = new BukuModel();

                    bukuModel.setJudul(etJudul.getText().toString());
                    bukuModel.setPenulis(etPenulis.getText().toString());

                    appDatabase.bukuDAO().insertBuku(bukuModel);

                    Log.d("MainActivity", "Berhasil Menyimpan");
                    Toast.makeText(getApplicationContext(), "Berhasil Menyimpan", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Log.e("MainActivity", "Gagal Menyimpan , msg : " +e.getMessage());
                    Toast.makeText(getApplicationContext(), "Gagal Menyimpan", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnLihatData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ReadActivity.class);

                startActivity(intent);

            }
        });
    }
}