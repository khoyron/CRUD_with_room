package com.khoiron.crud_with_room.Utility;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.khoiron.crud_with_room.Fragment.UpdateDialog;
import com.khoiron.crud_with_room.R;
import com.khoiron.crud_with_room.Room.Mahasiswa;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.khoiron.crud_with_room.Room.MyApp.db;


public class RecycleAdapter extends  RecyclerView.Adapter<RecycleAdapter.MyViewHolder> implements OnclickRecycler {

    private Context mContext;
    private List<Mahasiswa> myList;
    OnclickRecycler onclickRecycler = this;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvNama)TextView nama;
        @BindView(R.id.tvNim)TextView nim;
        @BindView(R.id.tvKejuruan)TextView kejuruan;
        @BindView(R.id.tvAlamat)TextView alamat;

        public MyViewHolder(View v) {
            super(v);

            ButterKnife.bind(this,v);
        }
    }
    public RecycleAdapter(Context mContext, List<Mahasiswa> albumList) {
        this.mContext = mContext;
        this.myList = albumList;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_layout, parent, false);

        return new MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Mahasiswa album = myList.get(position);
        holder.nama.setText(album.getNama());
        holder.nim.setText(album.getNim());
        holder.kejuruan.setText(album.getKejuruan());
        holder.alamat.setText(album.getAlamat());


        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                FragmentManager fm = ((Activity)mContext).getFragmentManager();
                UpdateDialog dialogFragment = new UpdateDialog(onclickRecycler);
                Bundle bundle = new Bundle();
                bundle.putInt("id",myList.get(position).getId());
                bundle.putInt("id_list",position);
                dialogFragment.setArguments(bundle);
                dialogFragment.show(fm," ");
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return myList.size();
    }


    @Override
    public void onItemDismiss(int position) {
        Mahasiswa mahasiswa = new Mahasiswa();
        mahasiswa.setId(myList.get(position).getId());
        db.userDao().deleteUsers(mahasiswa);

        myList.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public void updateListener(int id, Mahasiswa mahasiswa) {
        myList.get(id).setAlamat(mahasiswa.getAlamat());
        myList.get(id).setKejuruan(mahasiswa.getKejuruan());
        myList.get(id).setNama(mahasiswa.getNama());
        myList.get(id).setNim(mahasiswa.getNim());
        notifyDataSetChanged();
    }


}
