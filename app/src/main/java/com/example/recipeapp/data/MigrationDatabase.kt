package com.example.recipeapp.data

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

class MigrationFrom1To2 : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        // Add your migration logic here
        // For example, if you added a new column 'newColumn', you might do something like:
        database.execSQL("ALTER TABLE recipe_table ADD COLUMN image INTEGER NOT NULL DEFAULT 0")
    }
}