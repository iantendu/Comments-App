package com.example.commentsretrofit.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.commentsretrofit.models.CommentItem
import com.example.commentsretrofit.repository.CommentsRepository
import com.example.commentsretrofit.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class CommentsViewModel(
    val commentsRepository: CommentsRepository
):ViewModel() {
    val viewComments:MutableLiveData<Resource<CommentItem>> =MutableLiveData()

fun getComments()=viewModelScope.launch {
        safeGetCommentCall()
}

    private suspend fun safeGetCommentCall() {
     viewComments.postValue(Resource.Loading())
        try {
            val response=commentsRepository.getComments()
            viewComments.postValue(handleViewCommentsResponse(response))


        }catch (t:Throwable){

        }
    }

    private fun handleViewCommentsResponse(response: Response<List<CommentItem>>): Resource<CommentItem>? {
        if (response.isSuccessful){
            response.body()?.let { resultResponse->
                viewComments=resultResponse
            }
        }


        return Resource.Error(response.message())

    }


}