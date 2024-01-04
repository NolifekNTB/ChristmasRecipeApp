package com.example.recipeapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters

/**
 * Room Database class for managing recipe data.
 *
 * This class is annotated with the [Database] annotation to specify the entities it contains and the database version.
 */
@TypeConverters(HashMapConverter::class)
@Database(entities = [Recipe::class, Favorite::class], version = 3)
abstract class ContactDatabase: RoomDatabase() {
    /**
     * Abstract method to obtain the Data Access Object (DAO) for recipe-related database operations.
     *
     * @return Instance of [ContactDao] for interacting with recipe data.
     */
    abstract fun contactDao(): ContactDao
    abstract fun favoriteDao(): FavoriteDao
}


/**
 * Singleton object for accessing the Room database.
 *
 * This object ensures that only one instance of the database is created.
 */
object contactDb{
    private var db: ContactDatabase? = null

    /**
     * Gets the instance of the Room database.
     *
     * If the database instance doesn't exist, it is created using Room's databaseBuilder.
     *
     * @param context Application context for initializing the database.
     * @return Instance of [ContactDatabase].
     */
    fun getInstance(context: Context): ContactDatabase{
        if (db == null){
            db = Room.databaseBuilder(
                context,
                ContactDatabase::class.java,
                "contact_database"
            ).addMigrations(MigrationFrom1To2(), MigrationFrom2To3())
                .fallbackToDestructiveMigration()
                .build()
        }
        return db!!
    }
}
