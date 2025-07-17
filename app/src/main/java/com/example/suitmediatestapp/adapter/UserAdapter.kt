package com.example.suitmediatestapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.suitmediatestapp.R
import com.example.suitmediatestapp.data.response.DataItem

class UserAdapter(
    private var userList: List<DataItem>, private val onItemClicked: (DataItem) -> Unit) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val itemData = userList[position]
        Glide.with(holder.itemView.context)
            .load(itemData.avatar)
            .into(holder.imgPhoto)
        holder.name.text = "${itemData.firstName} ${itemData.lastName}"
        holder.email.text = itemData.email

        holder.itemView.setOnClickListener {
            onItemClicked(itemData)

        }

    }

    override fun getItemCount(): Int = userList.size

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPhoto: ImageView = itemView.findViewById(R.id.iv_userPhoto)
        val name: TextView = itemView.findViewById(R.id.txt_fullname)
        val email: TextView = itemView.findViewById(R.id.txt_email)

    }
}

