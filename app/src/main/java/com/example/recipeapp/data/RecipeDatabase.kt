package com.example.recipeapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Recipe::class], version = 2)
abstract class ContactDatabase: RoomDatabase() {
    abstract fun contactDao(): ContactDao
}

object contactDb{
    private var db: ContactDatabase? = null

    fun getInstance(context: Context): ContactDatabase{
        if (db == null){
            db = Room.databaseBuilder(
                context,
                ContactDatabase::class.java,
                "contact_database"
            ).addMigrations(MigrationFrom1To2()).build()
        }
        return db!!
    }
}