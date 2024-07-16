package com.example.convertationapp.presentation.secondfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.convertationapp.R
import com.example.convertationapp.data.api.RetrofitInstance
import com.example.convertationapp.databinding.FragmentSecondBinding
import com.example.convertationapp.presentation.rvadapter.ItemListAdapterForFirstScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SecondFragment : Fragment() {

    private lateinit var listAdapter: ItemListAdapterForFirstScreen

    var _binding: FragmentSecondBinding? = null
    private val binding: FragmentSecondBinding
        get() = _binding ?: throw RuntimeException("FragmentSecondBinding == null")



    private val viewModel: SecondViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)

        setupAdapter()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupAdapter(){
        listAdapter = ItemListAdapterForFirstScreen()
        binding.rvCurrenciesList.adapter = listAdapter

        CoroutineScope(Dispatchers.IO).launch {
            val responseList = RetrofitInstance.apiService.getCurrenciesList()

            withContext(Dispatchers.Main) {
                binding.apply { listAdapter.submitList(responseList.Valute.values.toList()) }
            }
        }
    }

    companion object {
        fun newInstance() = SecondFragment()
    }
}