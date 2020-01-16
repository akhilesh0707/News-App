package com.aqube.newsapp.ui.newsdetail

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import com.aqube.newsapp.base.BaseActivity
import com.aqube.newsapp.util.NEWS_URL
import kotlinx.android.synthetic.main.activity_new_detail.*
import com.aqube.newsapp.R
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar


class NewsDetailActivity : BaseActivity() {

    private var URL: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_detail)
        val toolBar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolBar)
        supportActionBar?.title = getString(R.string.app_name)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        bindWebView()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    fun bindWebView() {
        val receivedIntent = intent
        URL = receivedIntent.getStringExtra(NEWS_URL)
        if (URL == null) {
            finish()
        }

        webview.webViewClient = NewsWebClient()
        webview.webChromeClient = NewsChromeClient(progress_bar)
        webview.settings.loadsImagesAutomatically = true
        webview.settings.javaScriptEnabled = true
        webview.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
        webview.loadUrl(URL)
    }

    override fun onBackPressed() {
        if (webview.canGoBack()) {
            webview.goBack()
        } else {
            super.onBackPressed()
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (event.action == KeyEvent.ACTION_DOWN) {
            when (keyCode) {
                KeyEvent.KEYCODE_BACK -> {
                    if (webview.canGoBack()) {
                        webview.goBack()
                    } else {
                        onBackPressed()
                    }
                    return true
                }
            }
        }
        return super.onKeyDown(keyCode, event)
    }
}