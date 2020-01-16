package com.aqube.newsapp.ui.newsdetail

import android.webkit.WebView
import android.webkit.WebViewClient

/**
 * NewsWebClient is used to load url on WebView
 */
class NewsWebClient() : WebViewClient() {
    /**
     * Overriding shouldOverrideUrlLoading to Load Url
     * @param view : WebView
     * @param url : String url
     * @return Boolean : loading status true or false
     */
    override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
        view.loadUrl(url)
        return true
    }
}