package com.aqube.newsapp.ui.newsdetail

import android.os.Bundle
import android.view.View
import com.aqube.newsapp.base.BaseActivity
import com.aqube.newsapp.util.NEWS_URL
import kotlinx.android.synthetic.main.activity_new_detail.*
import com.aqube.newsapp.R
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar

/**
 * NewsDetailActivity is used to show news detail on web page (WebView)
 */
class NewsDetailActivity : BaseActivity() {

    private var URL: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_detail)
        setupToolBar()
        bindWebView()
    }

    /**
     * Setting up the toolbar to show back arrow and back functionality
     */
    private fun setupToolBar() {
        val toolBar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolBar)
        supportActionBar?.title = getString(R.string.app_name)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    /**
     * Checking the back click and returning to previous activity
     * @param item : MenuItem
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    /**
     * Setting up the WebView and getting the URL from intent and loading the url to WebView
     */
    fun bindWebView() {
        val receivedIntent = intent
        URL = receivedIntent.getStringExtra(NEWS_URL)
        // If URL is null return to previous screen
        if (URL == null) {
            finish()
        }
        // Setting WebClient to WebView
        webview.webViewClient = NewsWebClient()
        // Setting WebChromeClient to WebView
        webview.webChromeClient = NewsChromeClient(progress_bar)
        webview.settings.loadsImagesAutomatically = true
        webview.settings.javaScriptEnabled = true
        webview.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
        // Loading URL to WebView
        webview.loadUrl(URL)
    }
}