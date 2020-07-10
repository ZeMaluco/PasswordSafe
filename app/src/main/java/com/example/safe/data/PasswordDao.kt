package com.example.safe.data

import androidx.room.*

@Dao
interface PasswordDao {

    @Query("SELECT * FROM password")
    fun getAll(): List<Password>

    @Insert
    fun insert(item: Password)

    @Delete
    fun delete(item: Password)

    @Update
    fun update(item: Password)
}

