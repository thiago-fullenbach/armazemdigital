package br.com.thiago.armazemdigital.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

@Dao
public interface BaseDao<T> {
    @Insert
    long insert(T t);

    @Update
    int update(T t);

    @Delete
    int delete(T t);
}
