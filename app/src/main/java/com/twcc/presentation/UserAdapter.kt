package com.twcc.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.twcc.R
import com.twcc.databinding.UserItemBinding
import com.twcc.domain.models.UserDomain

class UserAdapter(
    private val onItemClick: (UserDomain) -> Unit
) : RecyclerView.Adapter<UserViewHolder>() {
    private var users: List<UserDomain> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding, onItemClick)
    }


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


class UserViewHolder(
    private val binding: UserItemBinding,
    private val onItemClick: (UserDomain) -> Unit
) :
    ViewHolder(binding.root) {
    fun bindData(item: UserDomain) {
        with(binding) {
            userName.text = item.name
            userApi.text = item.api

            Glide.with(itemView)
                .load(item.image)
                .fitCenter()
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(userAvatar)

            itemContainer.setOnClickListener {
                onItemClick.invoke(item)
            }
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