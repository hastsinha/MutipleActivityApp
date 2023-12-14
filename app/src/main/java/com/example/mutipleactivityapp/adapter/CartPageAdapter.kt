package com.example.mutipleactivityapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mutipleactivityapp.databinding.ItemCartProductListBinding
import com.example.mutipleactivityapp.modal.DesignItemsItem

class CartPageAdapter(
    val products: List<DesignItemsItem>,
    val deleteProduct: (designItemsItem: DesignItemsItem, position: Int) -> Unit
) : RecyclerView.Adapter<CartPageAdapter.CartViewHolder>() {

    inner class CartViewHolder(private val binding: ItemCartProductListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            with(binding) {
                tvProductName.text = products[position].designName
                tvProductPrice.text = products[position].price
                Glide.with(ivProduct).load(products[position].imageUrl).into(ivProduct)
                ivCross.setOnClickListener {
                    deleteProduct(products[position], position)
                }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CartViewHolder(
        ItemCartProductListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(position)
    }
}
