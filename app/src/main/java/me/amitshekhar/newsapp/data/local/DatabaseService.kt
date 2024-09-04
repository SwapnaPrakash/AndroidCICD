package me.amitshekhar.newsapp.data.local

import kotlinx.coroutines.flow.Flow
import me.amitshekhar.newsapp.data.local.entity.Article

interface DatabaseService {

    fun getArticles(): Flow<List<Article>>

    fun deleteAllAndInsertAll(articles: List<Article>)

}