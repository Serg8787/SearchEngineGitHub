package com.example.searchenginegithub

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.searchenginegithub.model.repo.RepoItem
import kotlinx.android.synthetic.main.repo_item.view.*

class RepoAdapter(val context:Context,val listRepoItem:ArrayList<RepoItem>):RecyclerView.Adapter<ViewHolderRepo>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderRepo {
        return ViewHolderRepo(LayoutInflater.from(context).inflate(R.layout.repo_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolderRepo, position: Int) {
        holder.name.text = listRepoItem[position].events_url
    }

    override fun getItemCount(): Int = listRepoItem.size
}
class ViewHolderRepo(itemView: View) : RecyclerView.ViewHolder(itemView){
    val name = itemView.tvNameRepo

}