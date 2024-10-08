package me.amitshekhar.newsapp.data.api

import me.amitshekhar.newsapp.data.model.ApiArticleResponse
import me.amitshekhar.newsapp.utils.AppConstant.API_KEY
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface NetworkService {

    @Headers("X-Api-Key: $API_KEY")
    @GET("top-headlines")
    suspend fun getArticles(@Query("country") country: String): ApiArticleResponse

}