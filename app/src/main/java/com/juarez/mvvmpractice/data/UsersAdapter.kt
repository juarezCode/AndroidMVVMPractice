package com.juarez.mvvmpractice.data

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.juarez.mvvmpractice.databinding.CardUserItemBinding
import com.juarez.mvvmpractice.models.User

class UsersAdapter(
    private val users: ArrayList<User>,
    private val onClick: (User) -> Unit
) : RecyclerView.Adapter<UsersAdapter.ViewHolder>() {

    fun updateData(data: List<User>) {
        users.clear()
        users.addAll(data)
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: CardUserItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            CardUserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(users[position]) {
                binding.txtUserName.text = name ?: "Unknown name"
            }
            itemView.setOnClickListener { onClick(users[position]) }
        }
    }

    override fun getItemCount() = users.size
}