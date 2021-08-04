package com.example.searchenginegithub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    var adapter: ProgramistAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val loc:String = edLocation.text.toString()
        val lan:String = edLanguage.text.toString()

        var retrofit = RetrofitClient.getClient("https://api.github.com/").create(API::class.java)

        retrofit.search("location:ukraine language:kotlin")
            .enqueue(object : Callback<ResultListProgramist> {
            override fun onResponse(call: Call<ResultListProgramist>, response: Response<ResultListProgramist>) {
                if (response.body() != null){
                    val list:ArrayList<ItemProgramist> = response.body()!!.items as ArrayList<ItemProgramist>
                    adapter = ProgramistAdapter(baseContext, list)
                    recycler.setLayoutManager(LinearLayoutManager(baseContext));
                    recycler.setAdapter(adapter);
                }
                Log.d("MyLOG", "onRespose "+ response.body()!!.total_count.toString())
            }
            override fun onFailure(call: Call<ResultListProgramist>, t: Throwable) {
                Log.d("MyLOG", "onFailture")
            }
        })
    }
}