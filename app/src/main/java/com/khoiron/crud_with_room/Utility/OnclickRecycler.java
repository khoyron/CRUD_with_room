package com.khoiron.crud_with_room.Utility;

import com.khoiron.crud_with_room.Room.Mahasiswa;

/**
 * Created by khoiron on 11/02/18.
 */

public interface OnclickRecycler {
    void onItemDismiss(int position);
    void updateListener(int id, Mahasiswa mahasiswa);
}
