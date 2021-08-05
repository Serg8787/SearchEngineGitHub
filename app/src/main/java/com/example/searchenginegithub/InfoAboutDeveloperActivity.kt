package com.example.searchenginegithub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.searchenginegithub.model.developer.ItemProgramist
import com.example.searchenginegithub.model.developer.ResultListProgramist
import com.example.searchenginegithub.model.repo.Repo
import com.example.searchenginegithub.model.repo.RepoItem
import kotlinx.android.synthetic.main.activity_info_about_developer.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dropdown_item.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InfoAboutDeveloperActivity : AppCompatActivity() {

    lateinit var login:String


    var adapterRepo: RepoAdapter? = null
    lateinit var listRepo: ArrayList<RepoItem>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_about_developer)

        login = intent.getStringExtra("login").toString()
        tvLoginInfo.text = login
        Log.d("MyLOG",login)

        val avatar = intent.getStringExtra("avatarUrl")
        Glide
            .with(this).load(avatar).circleCrop().into(ivAvatarInfo);

        val retrofit = RetrofitClient.getClient("https://api.github.com/").create(API::class.java)
        retrofit.getListRepo("${login}/repos")
            .enqueue(object : Callback<Repo> {
                override fun onResponse(
                    call: Call<Repo>,
                    response: Response<Repo>
                ) {
                    if (response.body() != null) {
                        listRepo = response.body()!!
                        adapterRepo = RepoAdapter(baseContext, listRepo)
                        recyclerRepo.setLayoutManager(LinearLayoutManager(baseContext));
                        recyclerRepo.setAdapter(adapterRepo);
                        Log.d("MyLOG","good" + response.body().toString())
                    }
                }

                override fun onFailure(call: Call<Repo>, t: Throwable) {
                    Log.d("MyLOG", "onFailture")
                }
            })



    }
}