package com.example.searchenginegithub

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.searchenginegithub.model.developer.ItemProgramist
import com.example.searchenginegithub.model.developer.ResultListProgramist
import com.example.searchenginegithub.network.API
import com.example.searchenginegithub.network.RetrofitClient
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), ItemCallback {

    var adapter: ProgramistAdapter? = null
    lateinit var list: ArrayList<ItemProgramist>
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
                        list =
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
       val intent = Intent(this,InfoAboutDeveloperActivity::class.java)
        val login:String = list[index].login
        val avatarUrl:String = list[index].avatar_url
        val id:String = list[index].id.toString()
        val noteId:String = list[index].node_id
        val followersUrl:String = list[index].followers_url
        intent.putExtra("index",index)
        intent.putExtra("login",login)
        intent.putExtra("avatarUrl",avatarUrl)
        intent.putExtra("id",id)
        intent.putExtra("noteId",noteId)
        intent.putExtra("followersUrl",followersUrl)
        startActivity(intent)
    }
}
