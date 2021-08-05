package com.example.searchenginegithub

import com.example.searchenginegithub.model.developer.ItemProgramist
import com.example.searchenginegithub.model.developer.ResultListProgramist
import com.example.searchenginegithub.model.repo.Repo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface API {
// знак вопроса в конце оставляем
    @GET("search/users?")
    fun search(
        @Query("q") query: String
    ): Call<ResultListProgramist>

    @GET("search/users?")
    fun infoDeveloper(
        @Query("q") query: String
    ): Call<ItemProgramist>



// для теста убрал знак вопроса
    @GET("users")
    fun getListRepo(
        @Query("q") query: String
    ): Call<Repo>


}