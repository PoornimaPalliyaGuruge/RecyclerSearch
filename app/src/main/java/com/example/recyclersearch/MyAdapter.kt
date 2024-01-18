package com.example.recyclersearch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase

class MyAdapter(private val accountList : ArrayList<Accounts>, private val firebaseAnalytics: FirebaseAnalytics, private val onItemClick: (String) -> Unit) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
       return accountList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = accountList[position]
        holder.imgTitle.setImageResource(currentItem.titleImage)
        holder.tvHeading.text = currentItem.heading

        holder.itemView.setOnClickListener {

            val itemClickBundle = Bundle()
            itemClickBundle.putString(FirebaseAnalytics.Param.ITEM_ID, currentItem.titleImage.toString())
            itemClickBundle.putString(FirebaseAnalytics.Param.ITEM_NAME, currentItem.heading)
            firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, itemClickBundle)


            onItemClick.invoke(currentItem.heading)
        }
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val imgTitle : ShapeableImageView =  itemView.findViewById(R.id.img_title)
        val tvHeading : TextView = itemView.findViewById(R.id.tvHeading)

    }
}