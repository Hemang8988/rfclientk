package com.example.crudappkotlin.adapter

import com.example.crudappkotlin.model.UserResponse
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.crudappkotlin.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.user_list_row.view.*

class UserAdapter(context: Context, private val userList:List<UserResponse.User>, private val onClickListener:View.OnClickListener ):
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private var layoutInflater = LayoutInflater.from(context)

    inner class UserViewHolder(itemView:View): RecyclerView.ViewHolder(itemView), View.OnClickListener {
        override fun onClick(view: View?) {
            view!!.tag = itemPosition
            onClickListener.onClick(view)
        }

        var itemPosition:Int = 0
        fun setData(user:UserResponse.User, position: Int){
            itemView.txt_name.text = user.firstName+" "+user.lastName
            itemView.txt_email.text = user.email
            itemView.setOnClickListener(this)
            Picasso.with(itemView.context).load(user.avatar).into(itemView.img_user)
            this.itemPosition = position
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.UserViewHolder {
        val itemView = layoutInflater.inflate(R.layout.user_list_row,parent,false)
        return UserViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: UserAdapter.UserViewHolder, position: Int) {
        holder.setData(userList[position],position)
    }
}