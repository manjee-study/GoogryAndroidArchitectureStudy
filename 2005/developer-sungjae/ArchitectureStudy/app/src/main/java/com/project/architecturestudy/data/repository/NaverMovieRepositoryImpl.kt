package com.project.architecturestudy.data.repository

import android.content.Context
import android.util.Log
import com.project.architecturestudy.data.model.Movie
import com.project.architecturestudy.data.source.local.NaverMovieLocalDataSourceImpl
import com.project.architecturestudy.data.source.local.room.MovieLocal
import com.project.architecturestudy.data.source.remote.NaverMovieRemoteDataSourceImpl

object NaverMovieRepositoryImpl : NaverMovieRepository {

    override lateinit var context: Context
    override lateinit var naverMovieLocalDataSource: NaverMovieLocalDataSourceImpl
    override lateinit var naverMovieRemoteDataSource: NaverMovieRemoteDataSourceImpl
    override fun setRepository(
        context: Context,
        naverMovieLocalDataSource: NaverMovieLocalDataSourceImpl,
        naverMovieRemoteDataSource: NaverMovieRemoteDataSourceImpl
    ) {
        this.context = context
        this.naverMovieLocalDataSource = naverMovieLocalDataSource
        this.naverMovieRemoteDataSource = naverMovieRemoteDataSource

    }

    override fun getCashedMovieList(Success: (ArrayList<MovieLocal>) -> Unit, Failure: (Throwable) -> Unit) {
        naverMovieLocalDataSource.getMovieList(context, Success, Failure)
    }

    override fun saveMovieListToLocal(items: ArrayList<Movie.Items>) {
        naverMovieLocalDataSource.saveMovieList(context, items)
    }

    override fun getMovieList(
        keyWord: String,
        Success: (ArrayList<Movie.Items>) -> Unit,
        Failure: (Throwable) -> Unit
    ) {
        naverMovieRemoteDataSource.getMovieList(keyWord, Success, Failure)
    }
}