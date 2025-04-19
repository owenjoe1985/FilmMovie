package com.example.filmmovie.api

import com.example.filmmovie.models.detail.ResponseDetail
import com.example.filmmovie.models.home.ResponseGenresList
import com.example.filmmovie.models.home.ResponseMoviesList
import com.example.filmmovie.models.register.BodyRegister
import com.example.filmmovie.models.register.ResponseRegister
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServices {

    @POST("register")
    suspend fun registerUser(@Body body:BodyRegister) : Response<ResponseRegister>

    @GET("genres/{genre_id}/movies")
    suspend fun moviesTopList(@Path("genre_id")id: Int): Response<ResponseMoviesList>

    @GET("genres/{genre_id}/movies")
    suspend fun moviesGenresList(@Path("genre_id")id: Int): Response<ResponseMoviesList>

    @GET("genres")
    suspend fun genresList(): Response<ResponseGenresList>

    @GET("movies")
    suspend fun moviesLastList(): Response<ResponseMoviesList>

    @GET("movies")
    suspend fun searchMovies(@Query("q") name: String): Response<ResponseMoviesList>

    @GET("movies/{movie_id}")
    suspend fun detailMovie(@Path("movie_id") id: Int): Response<ResponseDetail>
}