package edu.neu.madcourse.numad21fa_yanwang

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class LinkAdapter(val linkItemList : List<LinkItem>) : RecyclerView.Adapter<LinkAdapter.ViewHolder>(){
    private var listener : OnRecycleViewClickListener? =  null
    inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val linkItemName : TextView  = view.findViewById(R.id.linkItem_name)
        val linkItemUrl : TextView = view.findViewById(R.id.linkItem_url)
    }
    public  fun setListener(listener : OnRecycleViewClickListener){
        this.listener = listener

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.linkitem_layout, parent, false)
        val viewHolder = ViewHolder(view)
        viewHolder.linkItemUrl.setOnClickListener {
            val pos = viewHolder.absoluteAdapterPosition
            val link : LinkItem = linkItemList[pos]
            val intent = Intent(Intent.ACTION_VIEW)
//            intent.data = Uri.parse(link.Url)
            this.listener?.onItemClickListener(pos)
        }
        viewHolder.linkItemName.setOnClickListener {
            val pos = viewHolder.absoluteAdapterPosition
            val link : LinkItem = linkItemList[pos]
            this.listener?.onItemClickListener(pos)
        }
        return viewHolder
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