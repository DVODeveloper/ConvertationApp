package com.example.convertationapp.presentation.rvadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.convertationapp.R
import com.example.convertationapp.databinding.FragmentDetailBinding
import com.example.convertationapp.domain.entities.Valute

class ItemListAdapterForFirstScreen: ListAdapter<Valute, ItemListAdapterForFirstScreen.Holder>(Comparator()) {

    class Holder(view: View): RecyclerView.ViewHolder(view){

        private val binding = FragmentDetailBinding.bind(view)


        fun bind(valute: Valute) = with(binding) {
            spacer.text = valute.CharCode
            nameOfCurrency.text = valute.Name
            nominalOfCurrency.text = valute.Nominal.toString()
            currencyShort.text = valute.CharCode
            currencyValue.text = valute.Value.toString()
        }

    }

    class Comparator : DiffUtil.ItemCallback<Valute>() {
        override fun areItemsTheSame(oldItem: Valute, newItem: Valute): Boolean {
            return oldItem.ID == newItem.ID
        }

        override fun areContentsTheSame(oldItem: Valute, newItem: Valute): Boolean {
            return oldItem == newItem
        }

    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.fragment_detail,
            parent,
            false
        )
        return Holder(view)
    }

}