package com.example.up.lt_mvvm_.home

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.up.lt_mvvm_.R
import com.example.up.lt_mvvm_.data.Currencies
import com.example.up.lt_mvvm_.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var binding: FragmentHomeBinding? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
        setTextWatcher()
    }

    private fun setTextWatcher() {
        binding?.apply {
            input.addTextChangedListener(object : TextWatcher{
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

                override fun afterTextChanged(s: Editable?) {
                        binding?.apply {
                            val isEmpty = s != null && s.isNotEmpty()
                            dollar.isEnabled = isEmpty
                            euro.isEnabled = isEmpty
                            pound.isEnabled = isEmpty
                            yen.isEnabled = isEmpty
                        }
                }
            })
        }
    }

    private fun setListeners(){
        binding?.apply {
            dollar.setOnClickListener{
                val bundle = bundleOf(ARG_CURRENCY to Currencies.USD.name, ARG_AMOUNT to input.text.toString())
                Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_listFragment, bundle)
            }
            euro.setOnClickListener{
                val bundle = bundleOf(ARG_CURRENCY to Currencies.EUR.name, ARG_AMOUNT to input.text.toString())
                Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_listFragment, bundle)
            }
            pound.setOnClickListener{
                val bundle = bundleOf(ARG_CURRENCY to Currencies.GBP.name, ARG_AMOUNT to input.text.toString())
                Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_listFragment, bundle)
            }
            yen.setOnClickListener{
                val bundle = bundleOf(ARG_CURRENCY to Currencies.JPY.name, ARG_AMOUNT to input.text.toString())
                Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_listFragment, bundle)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    companion object{
        const val ARG_CURRENCY = "currency"
        const val ARG_AMOUNT = "amount"
    }
}