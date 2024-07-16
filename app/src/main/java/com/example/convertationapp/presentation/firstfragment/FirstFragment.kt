package com.example.convertationapp.presentation.firstfragment

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.convertationapp.R
import com.example.convertationapp.databinding.FragmentFirstBinding
import com.example.convertationapp.presentation.rvadapter.ItemListAdapterForFirstScreen

class FirstFragment : Fragment() {

    private lateinit var listAdapter: ItemListAdapterForFirstScreen

    var _binding: FragmentFirstBinding? = null
    private val binding: FragmentFirstBinding
        get() = _binding?: throw RuntimeException("FragmentFirstBinding == null")



    private val viewModel: FirstViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)



        return binding.root
    }



    companion object {
        fun newInstance() = FirstFragment()
    }
}