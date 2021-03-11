package com.example.up.lt_mvvm_.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.up.lt_mvvm_.R
import com.example.up.lt_mvvm_.databinding.ListItemBinding

class ListFragmentAdapter(private val data: List<String>): RecyclerView.Adapter<ListFragmentAdapter.ListVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListVH {
        val binding: ListItemBinding = DataBindingUtil
            .inflate(LayoutInflater.from(parent.context), R.layout.list_item, parent, false)
        return ListVH(binding)
    }

    override fun onBindViewHolder(holder: ListVH, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount() = data.size

    class ListVH(private val binding: ListItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(s: String) {
            binding.label1.text = s
        }
    }
}