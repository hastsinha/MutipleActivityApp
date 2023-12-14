package com.example.mutipleactivityapp.views

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mutipleactivityapp.R
import com.example.mutipleactivityapp.adapter.CartPageAdapter
import com.example.mutipleactivityapp.databinding.FragmentCartPageBinding
import com.example.mutipleactivityapp.modal.DesignItemsItem
import com.example.mutipleactivityapp.viewmodel.ProductsViewModel

class CartPageFragment : Fragment() {

    private var _binding: FragmentCartPageBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ProductsViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity?.window?.decorView?.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        activity?.window?.statusBarColor = ContextCompat.getColor(context, R.color.white)
        activity?.window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCartPageBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity())[ProductsViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            ivEmptyCart.isVisible = true
            initCartRecyclerView()
            setBackPressEvents()
        }
    }

    private fun setBackPressEvents() {
        with(binding) {
            toolbar.setNavigationOnClickListener {
                handleBackPress()
            }
            requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,
                object : OnBackPressedCallback(enabled = true) {
                    override fun handleOnBackPressed() {
                        handleBackPress()
                    }
                })
        }
    }

    private fun initCartRecyclerView() {
        with(binding) {
            viewModel.selectedProducts.observe(viewLifecycleOwner) {
                ivEmptyCart.isVisible = it.isEmpty()
                rvCart.adapter = CartPageAdapter(it, ::deleteProduct)
                val layoutManager =
                    LinearLayoutManager(binding.rvCart.context, LinearLayoutManager.VERTICAL, false)
                rvCart.layoutManager = layoutManager
            }
        }
    }

    private fun handleBackPress() {
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.fl_home, HomePageFragment(), "home_page_fragment")?.commit()
    }

    private fun deleteProduct(designItemsItem: DesignItemsItem, position: Int) {
        viewModel.deleteProduct(designItemsItem)
        binding.rvCart.adapter?.notifyItemRemoved(position)
        Toast.makeText(binding.rvCart.context, "Product Deleted From Cart", Toast.LENGTH_SHORT)
            .show()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}