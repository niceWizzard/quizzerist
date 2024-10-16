package com.coderizzard.network.domain

import com.coderizzard.network.data.model.ExtractedQuiz
import com.coderizzard.network.data.repository.ApiResponse
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface QuizExtractorApi {

    @GET("quiz/{id}")
    suspend fun getQuizByIdRaw(@Path("id") quizId : String) : Response<ResponseBody>

    @GET("quiz/{id}")
    suspend fun extractQuizById(@Path("id") quizId: String) : ExtractedQuiz

}