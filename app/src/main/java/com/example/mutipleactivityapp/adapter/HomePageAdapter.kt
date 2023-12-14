package com.example.mutipleactivityapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mutipleactivityapp.databinding.ItemProductListHomeBinding
import com.example.mutipleactivityapp.modal.DesignItemsItem

class HomePageAdapter(
    list: List<DesignItemsItem>,
    val onClick: (productItem: DesignItemsItem, isForCart: Boolean) -> Unit,
) : RecyclerView.Adapter<HomePageAdapter.HomePageViewHolder>() {

    private val data = mutableListOf<DesignItemsItem>()

    init {
        data.addAll(list)
    }

    fun updateData(newData: List<DesignItemsItem>) {
        val initialSize = data.size
        data.addAll(newData)
        notifyItemRangeInserted(initialSize, newData.size)
    }


    inner class HomePageViewHolder(private val binding: ItemProductListHomeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {

            with(binding) {
                Glide.with(ivProduct).load(data[position].imageUrl).into(ivProduct)
                tvProductName.text = data[position].designName
                tvProductPrice.text = data[position].price.toString()
                ivAddToCart.setOnClickListener {
                    if (bindingAdapterPosition > -1) {
                        onClick(data[bindingAdapterPosition], true)
                    }
                    ivAddToCart.isVisible = false
                }
                root.setOnClickListener {
                    if (bindingAdapterPosition > -1) {
                        onClick(data[bindingAdapterPosition], false)
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = HomePageViewHolder(
        ItemProductListHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: HomePageViewHolder, position: Int) {
        holder.bind(position)
    }
}