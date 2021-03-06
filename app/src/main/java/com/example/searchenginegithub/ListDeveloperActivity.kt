package com.example.searchenginegithub

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.searchenginegithub.model.developer.ItemDeveloper
import com.example.searchenginegithub.model.developer.ResultListDeveloper
import com.example.searchenginegithub.network.API
import com.example.searchenginegithub.network.RetrofitClient
import kotlinx.android.synthetic.main.activity_list_developer.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListDeveloperActivity : AppCompatActivity(), ItemCallback {
    var adapter: DeveloperAdapter? = null
    lateinit var list: ArrayList<ItemDeveloper>
    lateinit var location: String
    lateinit var language: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_developer)
        supportActionBar?.hide()

        val language = resources.getStringArray(R.array.language)
        val adapterLan = ArrayAdapter(this, R.layout.dropdown_item, language)
        autoCompleteTVLanguage.setAdapter(adapterLan)
        Log.d("MyLOG", language.toString())

        val location = resources.getStringArray(R.array.location)
        val adapterLoc = ArrayAdapter(this, R.layout.dropdown_item, location)
        autoCompleteTVLocation.setAdapter(adapterLoc)

        btSearch.setOnClickListener {
            getListProgramist()

        }
    }

    fun getListProgramist() {
        language = autoCompleteTVLanguage.text.toString()
        location  = autoCompleteTVLocation.text.toString()


        val retrofit = RetrofitClient.getClient(getString(R.string.base_url)).create(API::class.java)
        // наш запрос https://api.github.com/search/users?q=location:ukraine+language:java
        // q=означает что у нас в классе API один Query, хотя параметра для вставки два location и language
        // методом тыка я нашел что вместо + надо просто ставить пробел.
        // Тут при поиске в Гугле ничего не находил, что поставить вместо +

        retrofit.search("location:${location} language:${language}")
//        retrofit.search("","${location}","${language}")
            .enqueue(object : Callback<ResultListDeveloper> {
                override fun onResponse(
                    call: Call<ResultListDeveloper>,
                    response: Response<ResultListDeveloper>
                ) {
                    if (response.body() != null) {
                        list =
                            response.body()!!.items as ArrayList<ItemDeveloper>
                        adapter = DeveloperAdapter(baseContext, list,this@ListDeveloperActivity)
                        recycler.setLayoutManager(LinearLayoutManager(this@ListDeveloperActivity));
                        recycler.setAdapter(adapter);
                    }
                }

                override fun onFailure(call: Call<ResultListDeveloper>, t: Throwable) {
                   Toast.makeText(baseContext,getString(R.string.mistake_load),Toast.LENGTH_LONG).show()
                }
            })
    }

    override fun infoProgramist(index: Int) {
       val intent = Intent(this,InfoAboutDeveloperActivity::class.java)
        val login:String = list[index].login
        val avatarUrl:String = list[index].avatar_url
        val id:String = list[index].id.toString()
        intent.putExtra("index",index)
        intent.putExtra("login",login)
        intent.putExtra("avatarUrl",avatarUrl)
        intent.putExtra("id",id)
        startActivity(intent)
    }
}
