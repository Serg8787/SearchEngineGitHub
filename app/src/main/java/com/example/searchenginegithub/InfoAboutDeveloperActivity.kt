package com.example.searchenginegithub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_info_about_developer.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dropdown_item.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InfoAboutDeveloperActivity : AppCompatActivity() {
    lateinit var login:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_about_developer)



        login = intent.getStringExtra("login").toString()
        tvLoginInfo.text = login

        val avatar = intent.getStringExtra("avatarUrl")
        Glide
            .with(this)
            .load(avatar)
            .circleCrop()
            .into(ivAvatarInfo);
    }





}