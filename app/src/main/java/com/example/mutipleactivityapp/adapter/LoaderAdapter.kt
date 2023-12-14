package com.example.mutipleactivityapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.mutipleactivityapp.databinding.ItemProgressBarBinding

class LoaderAdapter : RecyclerView.Adapter<LoaderAdapter.ItemLoadMoreViewHolder>() {

    inner class ItemLoadMoreViewHolder(private val itemBinding: ItemProgressBarBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind() {
            itemBinding.root.isVisible = true
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemLoadMoreViewHolder =
        ItemLoadMoreViewHolder(
            ItemProgressBarBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: ItemLoadMoreViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int = 1
}