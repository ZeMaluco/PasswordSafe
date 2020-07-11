package com.example.safe.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PasswordDao {

    @Query("SELECT * FROM password")
    fun getAll(): List<Password>

    @Query("SELECT * FROM password WHERE id = :id")
    fun getPassword(id: Int):List<Password>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: Password)

    @Delete
    fun delete(item: Password)

   @Update
    fun update(item: Password)
}

