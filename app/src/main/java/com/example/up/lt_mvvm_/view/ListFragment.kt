package com.example.up.lt_mvvm_.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.up.lt_mvvm_.R
import com.example.up.lt_mvvm_.databinding.FragmentListBinding


class ListFragment : Fragment() {

    private var binding: FragmentListBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
    }

    private fun setAdapter() {
        context?.let {
            binding?.apply {
                container.layoutManager = LinearLayoutManager(it)
                val data = mutableListOf<String>().also {
                    it.add("One")
                    it.add("Two")
                    it.add("Three")
                    it.add("Four")
                    it.add("Five")
                    it.add("Six")
                }
                container.adapter = ListFragmentAdapter(data)

                val itemDecorator = DividerItemDecoration(it, RecyclerView.VERTICAL)
                ContextCompat.getDrawable(it, R.drawable.divider)?.apply {
                    itemDecorator.setDrawable(this)
                }
                container.addItemDecoration(itemDecorator)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding?.unbind()
        binding = null
    }
}