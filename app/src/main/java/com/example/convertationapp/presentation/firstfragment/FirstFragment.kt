package com.example.convertationapp.presentation.firstfragment

import android.app.Activity
import androidx.fragment.app.viewModels
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.convertationapp.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {

    private lateinit var listAdapter: ArrayAdapter<String>

    private var _binding: FragmentFirstBinding? = null
    private val binding: FragmentFirstBinding
        get() = _binding ?: throw RuntimeException("FragmentFirstBinding == null")

    private val viewModel: FirstViewModel by viewModels()

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)

        setupListSpinner()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getListCountriesForSpinner()

        viewModel.selectedItem.observe(viewLifecycleOwner) { selectedItem ->
            selectedItem?.let {
                val position = listAdapter.getPosition(it)
                binding.spinner.setSelection(position)
            }
        }

        binding.actionButtonFirstScreen.setOnClickListener {
            val amountOfCurrency = binding.amountOfCurrency.text.toString()
            Log.d("FirstFragment", "Выбранный элемент: ${viewModel.selectedItem.value} и сумма: $amountOfCurrency")

            viewModel.convertationCurrency(viewModel.selectedItem.value!!, amountOfCurrency)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupListSpinner() {
        viewModel.listForSpinner.observe(viewLifecycleOwner) { list ->
            Log.d("FirstFragment", "Загруженные страны: $list")
            listAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, list)

            listAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

            binding.spinner.adapter = listAdapter

            setSpinnerListener()
        }
    }

    private fun setSpinnerListener() {
        val buttonResult = binding.actionButtonFirstScreen

        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (view != null) {
                    var selectedItem = parent.getItemAtPosition(position).toString()

                    viewModel.setSelectedItem(selectedItem)
                } else {
                    Log.d("FirstFragment", "View is null in onItemSelected")
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                Log.d("FirstFragment", "Ничего не выбрано")
            }
        }
        viewModel.convertationResult.observe(viewLifecycleOwner) { result ->
            binding.amountOfRubles.text = result.toString()
        }
    }

    companion object {
        fun newInstance() = FirstFragment()
    }
}