package com.example.mutipleactivityapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mutipleactivityapp.modal.Responses
import com.example.mutipleactivityapp.network.ApiService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class ProductRepository {

    private val compositeDisposable = CompositeDisposable()

    fun getApiProductList(page: Int): LiveData<Responses> {
        val mutableLiveData = MutableLiveData<Responses>()
        val disposable = ApiService.instance.getProducts(page, "earrings", "mostpopular", "")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                mutableLiveData.value = it
            }, {
                android.util.Log.e("Network", "API Call Failed")
            })
        compositeDisposable.add(disposable)
        return mutableLiveData
    }

    fun clearDisposables() {
        compositeDisposable.clear()
    }
}
