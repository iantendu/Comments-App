package com.example.commentsretrofit.repository

import com.example.commentsretrofit.api.RetrofitInstance

class CommentsRepository {
    suspend fun getComments()=RetrofitInstance.api.getComments()
}