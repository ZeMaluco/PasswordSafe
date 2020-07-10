package com.example.safe.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Password (

    @PrimaryKey(autoGenerate = true) var id: Int? = null,

    @ColumnInfo(name="website")
    var website: String = "",

    @ColumnInfo(name = "password")
    var password: String = "",

    @ColumnInfo(name="description")
    var description: String = ""
)
