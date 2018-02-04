package com.khoiron.crud_with_room.Utility;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.khoiron.crud_with_room.R;
import com.khoiron.crud_with_room.Room.Mahasiswa;

import java.util.List;


public class RecycleAdapter extends  RecyclerView.Adapter<RecycleAdapter.MyViewHolder> {

    private Context mContext;
    private List<Mahasiswa> albumList;
    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView nama,nim,kejuruan,alamat;
        public MyViewHolder(View v) {
            super(v);

            nama = (TextView)v.findViewById(R.id.tvNama);
            nim = (TextView)v.findViewById(R.id.tvNim);
            kejuruan = (TextView)v.findViewById(R.id.tvKejuruan);
            alamat = (TextView)v.findViewById(R.id.tvAlamat);
        }
    }
    public RecycleAdapter(Context mContext, List<Mahasiswa> albumList) {
        this.mContext = mContext;
        this.albumList = albumList;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_layout, parent, false);

        return new MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Mahasiswa album = albumList.get(position);
        holder.nama.setText(album.getNama());
        holder.nim.setText(album.getNim());
        holder.kejuruan.setText(album.getKejuruan());
        holder.alamat.setText(album.getAlamat());
    }
    @Override
    public int getItemCount() {
        return albumList.size();
    }
}
