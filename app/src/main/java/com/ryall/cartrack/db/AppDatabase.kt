package com.ryall.cartrack.db

import android.content.Context
import androidx.room.*
import com.ryall.cartrack.models.Search

@Database(entities = [Search::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getAppDao() : AppDao

    companion object {
        private var DB_INSTANCE : AppDatabase? = null

        fun getAppDbInstance(context: Context) : AppDatabase{
            if (DB_INSTANCE == null){
                DB_INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java, "APP_DB")
                    .allowMainThreadQueries()
                    .build()
            }
            return DB_INSTANCE!!
        }
    }
}