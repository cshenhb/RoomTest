package com.example.roomtest.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * 数据库操作
 */
@Dao
public interface RoomUserDao {
    @Query("SELECT * FROM roomuser")
    List<RoomUser> getAll();

    @Delete
    void deleteUser(RoomUser user);


    @Insert
    void addUser(RoomUser user);


    @Query("select * from roomuser where userId = :userId ")
    RoomUser findUserByUserId(String userId);
}
