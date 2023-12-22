package com.example.recipeapp.data

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

/**
 * Database migration from version 1 to version 2.
 *
 * This migration adds the 'image' column to the 'recipe_table'.
 */
class MigrationFrom1To2 : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE recipe_table ADD COLUMN image INTEGER NOT NULL DEFAULT 0")
    }
}