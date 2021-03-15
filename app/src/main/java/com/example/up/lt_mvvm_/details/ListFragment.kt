package com.example.up.lt_mvvm_.details

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.up.lt_mvvm_.R
import com.example.up.lt_mvvm_.Utils
import com.example.up.lt_mvvm_.data.Currencies
import com.example.up.lt_mvvm_.databinding.FragmentListBinding
import com.example.up.lt_mvvm_.home.HomeFragment.Companion.ARG_CURRENCY

class ListFragment : Fragment() {

    private var binding: FragmentListBinding? = null
    private lateinit var currency: String
    private var isConnected: Boolean = false
    private val viewModel: ListFragmentViewModel by viewModels { ListFragmentViewModelFactory(currency, isConnected, activity?.application!!) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        currency = arguments?.getString(ARG_CURRENCY) ?: Currencies.USD.name
        context?.apply { isConnected = Utils.isNetworkConnected(this)}
        Log.d(TAG, currency)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()

        if (!isConnected)
            binding?.apply {
                status.visibility = View.VISIBLE
                status.setCompoundDrawablesWithIntrinsicBounds(R.drawable.offline_indicator, 0, R.drawable.offline_indicator, 0)
                status.text = resources.getText(R.string.offline)
            }
    }

    private fun setAdapter(data: Map<String, Double>) {
        context?.let {
            binding?.apply {
                container.layoutManager = LinearLayoutManager(it)
                container.adapter = ListFragmentAdapter(data)

                val itemDecorator = DividerItemDecoration(it, RecyclerView.VERTICAL)
                ContextCompat.getDrawable(it, R.drawable.divider)?.apply {
                    itemDecorator.setDrawable(this)
                }
                container.addItemDecoration(itemDecorator)
            }
        }
    }

    private fun initListeners() {

        viewModel.currencyLiveData.observe(viewLifecycleOwner, { rates ->
            binding?.apply {
                time.text = rates.date
                base.text = rates.base
                rates.rates?.let { setAdapter(it) }
                progressBarLayout.progressBar.visibility = View.GONE
                timeLabel.visibility = View.VISIBLE
                baseLabel.visibility = View.VISIBLE
                status.visibility = View.VISIBLE
            }
        })

        viewModel.errorLiveData.observe(viewLifecycleOwner, {
            binding?.noDataMsg?.visibility = View.VISIBLE
            binding?.progressBarLayout?.progressBar?.visibility = View.GONE
        })

    }

    override fun onDestroy() {
        super.onDestroy()
        binding?.unbind()
        binding = null
    }

    companion object {
        private val TAG = ListFragment::class.java.simpleName
    }
}