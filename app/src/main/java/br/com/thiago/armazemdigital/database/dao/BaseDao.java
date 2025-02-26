package br.com.thiago.armazemdigital.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

@Dao
public interface BaseDao<T> {
    @Insert
    void insert(T t);

    @Update
    void update(T t);

    @Delete
    void delete(T t);
}
