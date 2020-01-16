package com.aqube.newsapp.ui.newsdetail

import android.webkit.WebView
import android.webkit.WebViewClient

class NewsWebClient() : WebViewClient() {
    override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
        view.loadUrl(url)
        return true
    }
}