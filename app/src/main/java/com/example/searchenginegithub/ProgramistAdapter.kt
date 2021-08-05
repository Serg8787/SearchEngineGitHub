package com.example.searchenginegithub

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.programist_item.view.*

class ProgramistAdapter(val context: Context, val listProgramist: ArrayList<ItemProgramist>,val callback: ItemCallback) : RecyclerView.Adapter<ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.programist_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.login.text = listProgramist[position].login
        holder.itemView.setOnClickListener { callback.infoProgramist(position) }
    }

    override fun getItemCount(): Int =  listProgramist.size



}
class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val avatar = itemView.ivAvatarItem
    val login = itemView.tvLogin
}
interface ItemCallback {
    fun infoProgramist(index: Int)


}