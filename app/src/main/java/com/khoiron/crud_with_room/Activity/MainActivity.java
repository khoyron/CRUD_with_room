package com.khoiron.crud_with_room.Activity;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.khoiron.crud_with_room.R;
import com.khoiron.crud_with_room.Room.AppDatabase;
import com.khoiron.crud_with_room.Room.Mahasiswa;
import com.khoiron.crud_with_room.Room.MyApp;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.OkHttpClient;

import static com.khoiron.crud_with_room.Room.MyApp.db;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.etNama)EditText etNama;
    @BindView(R.id.etNim)EditText etNim;
    @BindView(R.id.etKejuruan)EditText etKejuruan;
    @BindView(R.id.etAlamat)EditText etAlamat;
    Mahasiswa mahasiswa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Stetho.initializeWithDefaults(this);
        new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .build();

    }

    @OnClick(R.id.btInsert)void buttonListener(){

        if (!etAlamat.getText().toString().isEmpty()&&!etKejuruan.getText().toString().isEmpty()&&
                !etNama.getText().toString().isEmpty()&&etNim.getText().toString().isEmpty()){

            mahasiswa = new Mahasiswa();
            mahasiswa.setAlamat(etAlamat.getText().toString());
            mahasiswa.setKejuruan(etKejuruan.getText().toString());
            mahasiswa.setNama(etNama.getText().toString());
            mahasiswa.setNim(etNim.getText().toString());
            //Insert data in database
            db.userDao().insertAll(mahasiswa);
            startActivity(new Intent(MainActivity.this,DetailActivity.class));
        }

    }


}
