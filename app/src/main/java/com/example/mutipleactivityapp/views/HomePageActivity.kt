package com.example.mutipleactivityapp.views

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.mutipleactivityapp.R
import com.example.mutipleactivityapp.databinding.ActivityHomePageBinding
import com.example.mutipleactivityapp.viewmodel.ProductsViewModel

class HomePageActivity : AppCompatActivity() {

    private lateinit var viewModel: ProductsViewModel
    private lateinit var binding: ActivityHomePageBinding
    private lateinit var cartPageFragment : CartPageFragment
    private lateinit var homePageFragment: HomePageFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomePageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setStatusBar()
        cartPageFragment = CartPageFragment()
        homePageFragment = HomePageFragment()
        viewModel = ViewModelProvider(this)[ProductsViewModel::class.java]
        initHomePageFragment()
        handleBottomNavigation()
    }

    private fun setStatusBar() {
        window?.decorView?.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        window?.statusBarColor = ContextCompat.getColor(this, R.color.home_page_background_color)
        window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    }

    private fun handleBottomNavigation() {
        binding.bnvHome.setOnItemSelectedListener {
            if (it.itemId == R.id.menu_item_cart) {
                if (!cartPageFragment.isAdded) {
                    supportFragmentManager.beginTransaction()
                        .replace(binding.flHome.id, cartPageFragment, "cart_page_fragment").commit()
                }
                true
            } else {
                if (!homePageFragment.isAdded) {
                    supportFragmentManager.beginTransaction()
                        .replace(binding.flHome.id, homePageFragment, "cart_page_fragment").commit()
                }
                true
            }
        }
    }

    private fun initHomePageFragment() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(binding.flHome.id, homePageFragment, "home_page_fragment")
        fragmentTransaction.commit()
    }

}