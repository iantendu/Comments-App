package com.example.commentsretrofit.models

import retrofit2.Response
import retrofit2.http.GET

interface MyAPI {
    @GET("/comments")
    suspend fun getComments(): Response<List<CommentItem>>
}