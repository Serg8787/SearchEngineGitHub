package com.example.searchenginegithub

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.example.searchenginegithub.model.repo.RepoItem
import kotlinx.android.synthetic.main.repo_item.view.*

class RepoAdapter(val context: Context, val listRepoItem: ArrayList<RepoItem>) :
    RecyclerView.Adapter<ViewHolderRepo>() {
    val lastPosition=-1
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderRepo {
        return ViewHolderRepo(
            LayoutInflater.from(context).inflate(R.layout.repo_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolderRepo, position: Int) {
        if(holder.adapterPosition>lastPosition){
            val animation = AnimationUtils.loadAnimation(context,R.anim.slide_in)
            holder.itemView.startAnimation(animation)
            holder.name.text = listRepoItem[position].name
            holder.desctption.text = listRepoItem[position].description
        }
    }
    override fun getItemCount(): Int = listRepoItem.size
}

class ViewHolderRepo(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val name = itemView.tvNameRepo
    val desctption = itemView.tvDescriptionRepo

}