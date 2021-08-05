package com.example.searchenginegithub

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), ItemCallback {

    var adapter: ProgramistAdapter? = null
    lateinit var location: String
    lateinit var language: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val language = resources.getStringArray(R.array.language)
        val adapterLan = ArrayAdapter(this, R.layout.dropdown_item, language)
        autoCompleteTVLanguage.setAdapter(adapterLan)

        val location = resources.getStringArray(R.array.location)
        val adapterLoc = ArrayAdapter(this, R.layout.dropdown_item, location)
        autoCompleteTVLocation.setAdapter(adapterLoc)

        btSearch.setOnClickListener {
            getListProgramist()
        }
    }

    fun getListProgramist() {
        language = autoCompleteTVLocation.text.toString()
        location  = autoCompleteTVLocation.text.toString()

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
                        adapter = ProgramistAdapter(baseContext, list,this@MainActivity)
                        recycler.setLayoutManager(LinearLayoutManager(this@MainActivity));
                        recycler.setAdapter(adapter);
                    }
                }

                override fun onFailure(call: Call<ResultListProgramist>, t: Throwable) {
                    Log.d("MyLOG", "onFailture")
                }
            })
    }

    override fun infoProgramist(index: Int) {
        TODO("Not yet implemented")
    }
}
