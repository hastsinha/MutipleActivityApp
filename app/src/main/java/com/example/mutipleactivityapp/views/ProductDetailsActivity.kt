package com.example.mutipleactivityapp.views

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.mutipleactivityapp.R
import com.example.mutipleactivityapp.databinding.ActivityProductDetailsBinding
import com.example.mutipleactivityapp.modal.DesignItemsItem
import com.example.mutipleactivityapp.repository.ProductRepository
import com.example.mutipleactivityapp.viewmodel.ProductsViewModel

class ProductDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductDetailsBinding
    private lateinit var viewModel: ProductsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setStatusBar()
        viewModel = ViewModelProvider(this)[ProductsViewModel::class.java]
        val bundle = intent.extras
        val data = bundle?.getSerializable("productData") as? DesignItemsItem
        with(binding) {
            Glide.with(ivProduct).load(data?.imageUrl).into(ivProduct)
            tvName.text = data?.designName
            tvDesc.text = data?.shortDesc
            tvPrice.text = data?.price.toString()
            tvCategory.text = data?.categoryType
            tvCustomizationId.text = data?.customizationId.toString()
            setButtonClicks(data)
            setWebView(data)
        }
    }

    private fun setWebView(data: DesignItemsItem?) {
        binding.btnWebview.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("productUrl", data?.productPageUrl)
            val intent = Intent(this@ProductDetailsActivity, WebViewActivity::class.java)
            intent.putExtras(bundle)
            startActivity(intent)
        }
    }

    private fun setButtonClicks(data: DesignItemsItem?) {
        with(binding) {
            btnAddToCart.setOnClickListener {
                setUpdatedResult(data!!)
                Toast.makeText(btnAddToCart.context, "Product Added to Cart", Toast.LENGTH_SHORT)
                    .show()
                btnAddToCart.isEnabled = false
            }
            fabBack.setOnClickListener {
                onBackPressed()
            }
            ivInfo.setOnClickListener {
                Toast.makeText(
                    ivInfo.context,
                    getString(R.string.text_verified_product),
                    Toast.LENGTH_SHORT
                ).show()
            }

        }
    }

    private fun setStatusBar() {
        window?.decorView?.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        window?.statusBarColor = ContextCompat.getColor(this, R.color.home_page_background_color)
        window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    }

    private fun setUpdatedResult(productItem: DesignItemsItem) {
        val intent = Intent().apply {
            putExtra("productItem", productItem)
        }
        setResult(Activity.RESULT_OK, intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        ProductRepository().clearDisposables()
    }
}