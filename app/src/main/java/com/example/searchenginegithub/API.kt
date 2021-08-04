package com.example.searchenginegithub

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface API {
// знак вопроса в конце оставляем
    @GET("search/users?")
    fun search(
        @Query("q") query: String
    ): Call<ResultListProgramist>


}