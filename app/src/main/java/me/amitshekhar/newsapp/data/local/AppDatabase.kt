package me.amitshekhar.newsapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import me.amitshekhar.newsapp.data.local.dao.ArticleDao
import me.amitshekhar.newsapp.data.local.entity.Article

@Database(entities = [Article::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao
}