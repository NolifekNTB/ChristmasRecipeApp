package com.example.recipeapp.data

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

class MigrationFrom2To3 : Migration(2, 3) {
    override fun migrate(database: SupportSQLiteDatabase) {
        // Since you are adding a new table (Favorite), you need to create it
        database.execSQL("CREATE TABLE IF NOT EXISTS `favorite_table` (" +
                "`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "`title` TEXT NOT NULL," +
                "`description` TEXT NOT NULL," +
                "`image` INTEGER NOT NULL)")

        // You might also need to perform other modifications or data migrations here
    }
}
