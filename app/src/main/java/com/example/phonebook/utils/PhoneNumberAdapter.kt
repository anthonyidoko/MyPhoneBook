package com.example.phonebook.utils

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.phonebook.R
import com.example.phonebook.data.NameAndNumber
import com.example.phonebook.databinding.PhoneNumberItemBinding
import com.example.phonebook.ui.NumberListFragment

class PhoneNumberAdapter : RecyclerView.Adapter<PhoneNumberAdapter.PhoneNumberViewHolder>() {

    private lateinit var clickLister: SetPhoneClickListener

    private val differCallBack = object : DiffUtil.ItemCallback<NameAndNumber>() {
        override fun areItemsTheSame(oldItem: NameAndNumber, newItem: NameAndNumber): Boolean {
            return oldItem.number == newItem.number
        }

        override fun areContentsTheSame(oldItem: NameAndNumber, newItem: NameAndNumber): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallBack)


    inner class PhoneNumberViewHolder(
        binding: PhoneNumberItemBinding, listener: SetPhoneClickListener
    ) : RecyclerView.ViewHolder(binding.root) {
        val name = binding.tvContactName
        val phoneNumber = binding.tvContactNumber

        init {
            itemView.setOnClickListener {
                listener.onNumberClicked(differ.currentList[adapterPosition].number)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhoneNumberViewHolder {
        val itemBinding = PhoneNumberItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return PhoneNumberViewHolder(itemBinding, clickLister)

    }

    override fun onBindViewHolder(holder: PhoneNumberViewHolder, position: Int) {
        val currentItem = differ.currentList[position]
        holder.apply {
            name.text = currentItem.name
            phoneNumber.text = currentItem.number
        }

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    interface SetPhoneClickListener {
        fun onNumberClicked(contact: String)
    }

    fun setOnClickListener(listener: SetPhoneClickListener) {
        clickLister = listener
    }

}