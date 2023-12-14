package com.example.mutipleactivityapp.views

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mutipleactivityapp.R
import com.example.mutipleactivityapp.adapter.HomePageAdapter
import com.example.mutipleactivityapp.adapter.LoaderAdapter
import com.example.mutipleactivityapp.databinding.FragmentHomePageBinding
import com.example.mutipleactivityapp.modal.DesignItemsItem
import com.example.mutipleactivityapp.viewmodel.ProductsViewModel

class HomePageFragment : Fragment() {
    private var _binding: FragmentHomePageBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ProductsViewModel
    private lateinit var productDetailLauncher: ActivityResultLauncher<Intent>
    private lateinit var homePageAdapter: HomePageAdapter
    private val loadMoreAdapter: LoaderAdapter by lazy { LoaderAdapter() }
    private lateinit var adapter: ConcatAdapter
    private var page: Int = 0
    private var isApiCallInProgress: Boolean = false

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity?.window?.decorView?.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        activity?.window?.statusBarColor =
            ContextCompat.getColor(context, R.color.home_page_background_color)
        activity?.window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomePageBinding.inflate(inflater, container, false)
        productDetailLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode == Activity.RESULT_OK) {
                    val productItem =
                        it.data?.getSerializableExtra("productItem") as DesignItemsItem
                    handleOnCLick(productItem, true)
                }
            }
        viewModel = ViewModelProvider(requireActivity())[ProductsViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initSearch()
    }

    private fun initSearch() {
        with(binding.svSearch) {
            clearFocus()
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    //No Implementation Required
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    newText?.let {
                        filterProducts(newText)
                    }
                    return true
                }
            })
        }
    }

    private fun initRecyclerView() {
        with(binding) {
            val listData = mutableListOf<DesignItemsItem>()
            viewModel.getItems(++page).observe(viewLifecycleOwner) {
                it?.designItems?.let { designItems ->
                    designItems.forEach { item ->
                        item?.let { listData.add(item) }
                    }
                    homePageAdapter = HomePageAdapter(
                        listData,
                        ::handleOnCLick
                    )
                    adapter = ConcatAdapter(homePageAdapter, loadMoreAdapter)
                    rvList.adapter = adapter
                    val layoutManager = GridLayoutManager(rvList.context, 2)
                    rvList.layoutManager = layoutManager
                    ivNoProducts.isVisible = designItems.isEmpty()
                }
            }
            setScrollListener()
        }
    }

    private fun setScrollListener() {
        binding.rvList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                isApiCallInProgress = false
                val layoutManager = recyclerView.layoutManager as GridLayoutManager
                val mCurrentVisibleItemPosition =
                    layoutManager.findLastVisibleItemPosition()
                if (mCurrentVisibleItemPosition > layoutManager.itemCount - 3 && !isApiCallInProgress) {
                    isApiCallInProgress = true
                    adapter.addAdapter(loadMoreAdapter)
                    val listData = mutableListOf<DesignItemsItem>()
                    viewModel.getItems(++page).observe(viewLifecycleOwner) {
                        it?.designItems?.let { list ->
                            list.forEach { item ->
                                item?.let {
                                    listData.add(item)
                                }
                            }
                        }
                        adapter.removeAdapter(loadMoreAdapter)
                        homePageAdapter.updateData(listData)
                        isApiCallInProgress = false
                    }
                }
            }
        })
    }

    private fun filterProducts(text: String) {
        val list = mutableListOf<DesignItemsItem>()
        viewModel.getItems(1).observe(viewLifecycleOwner) {
            it.designItems?.let { designList ->
                for (products in designList) {
                    if (products?.designName?.lowercase()?.contains(text.lowercase()).orFalse()) {
                        products?.let { list.add(products) }
                    }
                }
                if (list.isEmpty()) {
                    Toast.makeText(binding.svSearch.context, "No Product Found", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    setFilteredProducts(list)
                }
            }
        }
    }

    private fun Boolean?.orFalse(): Boolean = this ?: false

    private fun setFilteredProducts(list: MutableList<DesignItemsItem>) {
        val adapter = HomePageAdapter(list, ::handleOnCLick)
        binding.rvList.adapter = adapter
        (binding.rvList.adapter as HomePageAdapter).notifyDataSetChanged()
    }

    private fun handleOnCLick(productItem: DesignItemsItem, isForCart: Boolean) {
        if (isForCart) {
            viewModel.addSelectedProduct(productItem)
            Toast.makeText(
                activity,
                getString(R.string.text_product_added_to_cart),
                Toast.LENGTH_SHORT
            ).show()
        } else {
            val bundle = Bundle()
            bundle.putSerializable("productData", productItem)
            val intent = Intent(activity, ProductDetailsActivity::class.java)
            intent.putExtras(bundle)
            productDetailLauncher.launch(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}