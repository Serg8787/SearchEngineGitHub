package com.example.searchenginegithub

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.searchenginegithub.model.developer.ItemDeveloper
import kotlinx.android.synthetic.main.developer_item.view.*


class DeveloperAdapter(
    val context: Context,
    val listProgramist: ArrayList<ItemDeveloper>,
    val callback: ItemCallback
) : RecyclerView.Adapter<ViewHolderDeveloper>() {
    val lastPosition=-1
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderDeveloper {
        return ViewHolderDeveloper(
            LayoutInflater.from(context).inflate(R.layout.developer_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolderDeveloper, position: Int) {
        if(holder.adapterPosition>lastPosition){
            val animation = AnimationUtils.loadAnimation(context,R.anim.slide_in)
            holder.itemView.startAnimation(animation)
            holder.login.text = listProgramist[position].login
            holder.urlItem.text = listProgramist[position].url
            Glide.with(context).load(listProgramist[position].avatar_url)
                .placeholder(R.drawable.icons8_load).circleCrop().into(holder.avatar);
            holder.itemView.setOnClickListener { callback.infoProgramist(position) }

        }

    }

    override fun getItemCount(): Int = listProgramist.size


}

class ViewHolderDeveloper(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val avatar = itemView.ivAvatarItem
    val login = itemView.tvLoginItem
    val urlItem = itemView.tvURLItem
}

interface ItemCallback {
    fun infoProgramist(index: Int)


}