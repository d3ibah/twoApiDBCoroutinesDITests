package com.twcc.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.twcc.R
import com.twcc.domain.models.UserDomain

class UserAdapter(
    private val onItemClick: (UserDomain) -> Unit
) : RecyclerView.Adapter<UserViewHolder>() {
    private var users: List<UserDomain> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder =
        LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
            .let { UserViewHolder(it, onItemClick) }


    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bindData(users[position])
    }

    override fun getItemCount(): Int = users.size

    fun setData(newData: List<UserDomain>) {
        val diffCallback = DataCallback(users, newData)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        users = newData
        diffResult.dispatchUpdatesTo(this)
    }
}


class UserViewHolder(view: View, private val onItemClick: (UserDomain) -> Unit) :
    ViewHolder(view) {
    private var itemContainer = itemView.findViewById<LinearLayout>(R.id.itemContainer)
    private var userNameTV = itemView.findViewById<TextView>(R.id.user_name)
    private var userApiTV = itemView.findViewById<TextView>(R.id.user_api)
    private var avatarIV = itemView.findViewById<ImageView>(R.id.user_avatar)
    fun bindData(item: UserDomain) {
        userNameTV.text = item.name
        userApiTV.text = item.api
//        avatarIV
        itemContainer.setOnClickListener {
            onItemClick.invoke(item)
        }
    }
}

class DataCallback(
    private val oldUserList: List<UserDomain>,
    private val newUserList: List<UserDomain>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldUserList.size

    override fun getNewListSize(): Int = newUserList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldUserList[oldItemPosition]
        val newItem = newUserList[newItemPosition]
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldUserList[oldItemPosition]
        val newItem = newUserList[newItemPosition]
        return oldItem == newItem
    }

}