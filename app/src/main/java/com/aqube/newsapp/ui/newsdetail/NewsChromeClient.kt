package com.aqube.newsapp.ui.newsdetail

import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.widget.ProgressBar

class NewsChromeClient(private val progressBar: ProgressBar): WebChromeClient() {
    override fun onProgressChanged(view: WebView, progress: Int) {
        progressBar.progress=progress
        if (progress == 100) {
            progressBar.visibility= View.GONE
        } else {
            progressBar.visibility= View.VISIBLE
        }
    }
}