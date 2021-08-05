package com.example.searchenginegithub

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.searchenginegithub.model.developer.ItemProgramist
import kotlinx.android.synthetic.main.activity_info_about_developer.*
import kotlinx.android.synthetic.main.programist_item.view.*

class ProgramistAdapter(val context: Context, val listProgramist: ArrayList<ItemProgramist>, val callback: ItemCallback) : RecyclerView.Adapter<ViewHolderDeveloper>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderDeveloper {
        return ViewHolderDeveloper(LayoutInflater.from(context).inflate(R.layout.developer_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolderDeveloper, position: Int) {
        holder.login.text = listProgramist[position].login
        Glide.with(context).load(listProgramist[position].avatar_url) .placeholder(R.drawable.icons8_load).circleCrop().into(holder.avatar);
        holder.itemView.setOnClickListener { callback.infoProgramist(position) }
    }
    override fun getItemCount(): Int =  listProgramist.size



}
class ViewHolderDeveloper(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val avatar = itemView.ivAvatarItem
    val login = itemView.tvLogin
}
interface ItemCallback {
    fun infoProgramist(index: Int)


}