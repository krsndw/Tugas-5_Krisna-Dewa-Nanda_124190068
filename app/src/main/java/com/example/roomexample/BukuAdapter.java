package com.example.roomexample;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomexample.database.AppDatabase;
import com.example.roomexample.database.BukuModel;

import java.util.ArrayList;

public class BukuAdapter extends RecyclerView.Adapter<BukuAdapter.ViewHolder> {
    private Context context;
    private ArrayList<BukuModel> bukuItems = new ArrayList<>();
    private AppDatabase appDatabase;

    public BukuAdapter(Context context) {
        this.context = context;
        appDatabase = AppDatabase.iniDatabase(this.context);
    }

    public void setData(ArrayList<BukuModel> items) {
        bukuItems.clear();
        bukuItems.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BukuAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlist_buku,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BukuAdapter.ViewHolder holder, int position) {
        holder.tvJudul.setText(bukuItems.get(position).getJudul());
        holder.tvPenulis.setText(bukuItems.get(position).getPenulis());

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    BukuModel bukuModel = new BukuModel();

                    bukuModel.setJudul(bukuItems.get(position).getJudul());
                    bukuModel.setPenulis(bukuItems.get(position).getPenulis());
                    bukuModel.setId(bukuItems.get(position).getId());

                    appDatabase.bukuDAO().deleteBuku(bukuModel);

                    Log.d("BukuAdapter", "Berhasil Dihapus");
                    Toast.makeText(context, "Berhasil Dihapus", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Log.e("MainActivity", "Gagal Dihapus , msg : " +e.getMessage());
                    Toast.makeText(context, "Gagal Dihapus", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return bukuItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        Button btnDelete;
        TextView tvJudul, tvPenulis;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            btnDelete = itemView.findViewById(R.id.itemlist_buku_btn_delete);

            tvJudul = itemView.findViewById(R.id.itemlist_buku_tv_judul);
            tvPenulis = itemView.findViewById(R.id.itemlist_buku_tv_penulis);
        }
    }
}