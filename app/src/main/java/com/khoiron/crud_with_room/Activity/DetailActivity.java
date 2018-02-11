package com.khoiron.crud_with_room.Activity;

import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;

import com.khoiron.crud_with_room.R;
import com.khoiron.crud_with_room.Room.AppDatabase;
import com.khoiron.crud_with_room.Room.Mahasiswa;
import com.khoiron.crud_with_room.Utility.RecycleAdapter;
import com.khoiron.crud_with_room.Utility.HandleTouchHelperCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.khoiron.crud_with_room.Room.MyApp.db;

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

        ItemTouchHelper.Callback callback =  new HandleTouchHelperCallback(recycleAdapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(myRecyclerview);

    }

    private void fetchDataFromRoom() {
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class,"mahasiswa").allowMainThreadQueries().build();
        listMahasiswas = db.userDao().getAll();

        //just checking data from db
        for (int i = 0 ;i <listMahasiswas.size();i++){
            Log.e("Aplikasi",listMahasiswas.get(i).getAlamat()+i);
            Log.e("Aplikasi",listMahasiswas.get(i).getKejuruan()+i);
            Log.e("Aplikasi",listMahasiswas.get(i).getNama()+i);
            Log.e("Aplikasi",listMahasiswas.get(i).getNim()+i);
        }
        Log.e("cek list",""+listMahasiswas.size());
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
