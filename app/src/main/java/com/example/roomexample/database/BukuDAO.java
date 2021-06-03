package com.example.roomexample.database;

import androidx.annotation.RequiresPermission;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface BukuDAO {

    @Insert
    long insertBuku(BukuModel bukuModel);

    @Delete
    int deleteBuku(BukuModel bukuModel);

    @Query("SELECT * from data_buku")
    List<BukuModel> getBuku();

}
