package edu.neu.madcourse.numad21fa_yanwang

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class LinkAdapter(val linkItemList : List<LinkItem>) : RecyclerView.Adapter<LinkAdapter.ViewHolder>(){
    inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val linkItemName : TextView  = view.findViewById(R.id.linkItem_name)
        val linkItemUrl : TextView = view.findViewById(R.id.linkItem_url)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.linkitem_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val linkItem = linkItemList[position]
        holder.linkItemName.text = linkItem.name
        holder.linkItemUrl.text = linkItem.Url
    }

    override fun getItemCount(): Int {
        return linkItemList.size
    }
}