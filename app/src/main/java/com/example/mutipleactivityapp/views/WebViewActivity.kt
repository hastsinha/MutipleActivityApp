package com.example.mutipleactivityapp.views

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.mutipleactivityapp.R
import com.example.mutipleactivityapp.databinding.ActivityWebViewBinding

class WebViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWebViewBinding

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setStatusBar()
        val bundle = intent.extras
        val data = bundle?.getString("productUrl")
        with(binding.wv) {
            webViewClient = WebViewClient()
            data?.let { loadUrl("https://www.bluestone.com/earrings/the-indu-sui-dhaga-earrings~37454.html?impEvent=browseclick&posEvent=1&sortbyEvent=mostpopular&tagEvent=") }
            settings.setSupportZoom(true)
            settings.javaScriptEnabled = true
        }
    }

    private fun setStatusBar() {
        window?.decorView?.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        window?.statusBarColor = ContextCompat.getColor(this, R.color.white)
        window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    }
}