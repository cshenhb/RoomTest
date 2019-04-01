package com.example.roomtest.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * 数据库User
 */
@Entity
public class RoomUser {


    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "userId")
    public String userId;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoomUser roomUser = (RoomUser) o;

        return userId != null ? userId.equals(roomUser.userId) : roomUser.userId == null;
    }

    @Override
    public int hashCode() {
        return userId != null ? userId.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "RoomUser{" +
                "userId='" + userId + '\'' +
                ", loginName='" + loginName + '\'' +
                '}';
    }

    @Ignore
    public String loginName;


}
