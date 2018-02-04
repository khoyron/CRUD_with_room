package com.khoiron.crud_with_room.Activity;

import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.khoiron.crud_with_room.R;
import com.khoiron.crud_with_room.Room.AppDatabase;
import com.khoiron.crud_with_room.Room.Mahasiswa;
import com.khoiron.crud_with_room.Room.MyApp;
import com.khoiron.crud_with_room.Utility.RecycleAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.myRecyclerview)RecyclerView myRecyclerview;
    RecycleAdapter recycleAdapter;
    List<Mahasiswa> listMahasiswas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        fetchDataFromRoom();
        initRecyclerView();
        setAdapter();

    }

    private void fetchDataFromRoom() {
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class,"mahasiswa").allowMainThreadQueries().build();
        listMahasiswas = db.userDao().getAll();

        //just checking data from db
        for (int i = 0 ;i <listMahasiswas.size();i++){
            Log.e("Aplikasi",listMahasiswas.get(i).getAlamat()+i);
            Log.e("Aplikasi",listMahasiswas.get(i).getKejuruan()+i);
            Log.e("Aplikasi",listMahasiswas.get(i).getNama()+i);
            Log.e("Aplikasi",listMahasiswas.get(i).getNim()+i);
        }
    }

    private void initRecyclerView() {
        myRecyclerview.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        myRecyclerview.setLayoutManager(llm);
        recycleAdapter =new RecycleAdapter(this,listMahasiswas);
    }

    private void setAdapter() {
        myRecyclerview.setAdapter(recycleAdapter);
    }
}
