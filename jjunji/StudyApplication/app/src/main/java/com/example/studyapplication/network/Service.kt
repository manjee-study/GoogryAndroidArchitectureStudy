package com.example.studyapplication.network

import com.example.studyapplication.data.model.BlogList
import com.example.studyapplication.data.model.ImageList
import com.example.studyapplication.data.model.KinList
import com.example.studyapplication.data.model.MovieList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Service {
    @GET("v1/search/movie.json")
    fun getMovieList(@Query("query") query : String) : Call<MovieList>

    @GET("v1/search/image.json")
    fun getImageList(@Query("query") query : String) : Call<ImageList>

    @GET("v1/search/blog.json")
    fun getBlogList(@Query("query") query : String) : Call<BlogList>

    @GET("v1/search/kin.json")
    fun getKinList(@Query("query") query : String) : Call<KinList>
}