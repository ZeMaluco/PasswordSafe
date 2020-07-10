package com.example.safe.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Password::class), version = 1)
abstract class PasswordDatabase : RoomDatabase() {
    abstract fun passwordDao(): PasswordDao

    companion object {
        private var INSTANCE: PasswordDatabase? = null

        fun getInstance(context: Context): PasswordDatabase? {
            if (INSTANCE == null) {
                synchronized(PasswordDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        PasswordDatabase::class.java, "password-database"
                    )
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}
