package com.example.searchenginegithub

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    var adapter: ProgramistAdapter? = null
    lateinit var location: String
    lateinit var language: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        language = ""
        location = ""

        btSearch.setOnClickListener {
            getListProgramist()
        }
    }

    fun getListProgramist() {
        location = edLocation.text.toString()
        language = edLanguage.text.toString()

        val retrofit = RetrofitClient.getClient("https://api.github.com/").create(API::class.java)
        // наш запрос https://api.github.com/search/users?q=location:ukraine+language:java
        // q=означает что у нас в классе API один Query, хотя параметра для вставки два location и language
        // методом тыка я нашел что вместо + надо просто ставить пробел.
        // Тут при поиске в Гугле ничего не находил, что поставить вместо +

        retrofit.search("location:${location} language:${language}")
            .enqueue(object : Callback<ResultListProgramist> {
                override fun onResponse(
                    call: Call<ResultListProgramist>,
                    response: Response<ResultListProgramist>
                ) {
                    if (response.body() != null) {
                        val list: ArrayList<ItemProgramist> =
                            response.body()!!.items as ArrayList<ItemProgramist>
                        adapter = ProgramistAdapter(baseContext, list)
                        recycler.setLayoutManager(LinearLayoutManager(this@MainActivity));
                        recycler.setAdapter(adapter);
                    }
                    Log.d("MyLOG", "onRespose " + response.body()!!.total_count.toString())
                }

                override fun onFailure(call: Call<ResultListProgramist>, t: Throwable) {
                    Log.d("MyLOG", "onFailture")
                }
            })
    }
}
