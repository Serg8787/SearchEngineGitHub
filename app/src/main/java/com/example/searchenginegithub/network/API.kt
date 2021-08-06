package com.example.searchenginegithub.network

import com.example.searchenginegithub.model.developer.ResultListDeveloper
import com.example.searchenginegithub.model.repo.Repo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface API {
// знак вопроса в конце оставляем
    @GET("search/users?")
    fun search(
        @Query("q") query: String,
    ): Call<ResultListDeveloper>



// для теста убрал знак вопроса
    @GET("users/{login}/repos")
    fun getListRepo(
    @Path("login") login:String
    ): Call<Repo>


}