package com.example.up.lt_mvvm_.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.up.lt_mvvm_.R
import com.example.up.lt_mvvm_.databinding.ListItemBinding

class ListFragmentAdapter(
    private val data: Map<String, Double>,
    private val amount: Int
): RecyclerView.Adapter<ListFragmentAdapter.ListVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListVH {
        val binding: ListItemBinding = DataBindingUtil
            .inflate(LayoutInflater.from(parent.context), R.layout.list_item, parent, false)
        return ListVH(binding, amount)
    }

    override fun onBindViewHolder(holder: ListVH, position: Int) {
        data.keys.toList()[position]
        holder.bind(data.keys.toList()[position], data[data.keys.toList()[position]])
    }

    override fun getItemCount() = data.size

    class ListVH(private val binding: ListItemBinding, private val amount: Int): RecyclerView.ViewHolder(binding.root) {

        fun bind(currency: String?, value: Double?) {
            binding.label1.text = currency
            value?.let {  binding.label2.text = (it * amount).toString() }
        }
    }
}