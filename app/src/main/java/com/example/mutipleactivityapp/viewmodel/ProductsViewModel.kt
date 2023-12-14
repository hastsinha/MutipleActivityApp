package com.example.mutipleactivityapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mutipleactivityapp.modal.DesignItemsItem
import com.example.mutipleactivityapp.repository.ProductRepository

class ProductsViewModel : ViewModel() {

    private val _selectedProducts = MutableLiveData<List<DesignItemsItem>>()
    val selectedProducts: LiveData<List<DesignItemsItem>> get() = _selectedProducts

    fun addSelectedProduct(designItemsItem: DesignItemsItem) {
        val currentList = _selectedProducts.value ?: emptyList()
        val updatedList = currentList.toMutableList().apply {
            add(designItemsItem)
        }
        _selectedProducts.value = updatedList
    }

    fun deleteProduct(designItemsItem: DesignItemsItem) {
        val currentList = _selectedProducts.value ?: emptyList()
        val updatedList = currentList.toMutableList().apply {
            remove(designItemsItem)
        }
        _selectedProducts.value = updatedList
    }

    fun getItems(page: Int) = ProductRepository().getApiProductList(page)

}