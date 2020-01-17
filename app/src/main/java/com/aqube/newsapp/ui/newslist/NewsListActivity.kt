package com.aqube.newsapp.ui.newslist

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aqube.newsapp.R
import com.aqube.newsapp.base.BaseActivity
import com.aqube.newsapp.base.NetworkState
import com.aqube.newsapp.base.ViewModelProviderFactory
import com.aqube.newsapp.model.News
import com.aqube.newsapp.ui.newsdetail.NewsDetailActivity
import com.aqube.newsapp.ui.newslist.adapter.NewsListAdapter
import com.aqube.newsapp.util.NEWS_URL
import com.bumptech.glide.RequestManager
import kotlinx.android.synthetic.main.activity_news_list.progress_bar
import kotlinx.android.synthetic.main.activity_news_list.recycler_view
import javax.inject.Inject

/**
 * NewsListActivity is used to display the news list
 */
class NewsListActivity : BaseActivity() {

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory
    @Inject
    lateinit var requestManager: RequestManager
    private lateinit var viewModel: NewsListViewModel
    private lateinit var newsListAdapter: NewsListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_list)
        setupToolBar()
        viewModel = ViewModelProvider(this, providerFactory).get(NewsListViewModel::class.java)
        initAdapter()
        initState()
    }

    /**
     * Setting up the toolbar
     */
    private fun setupToolBar() {
        val toolBar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolBar)
        supportActionBar?.title = getString(R.string.app_name)
    }

    /**
     * Setting up the Adapter and RecyclerView
     */
    private fun initAdapter() {
        newsListAdapter = NewsListAdapter(requestManager) { news: News -> itemClicked(news) }
        recycler_view.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recycler_view.adapter = newsListAdapter
        viewModel.newsList.observe(this, Observer {
            newsListAdapter.submitList(it)
        })
    }

    /**
     * Checking the loading status and according the loading status showing ProgressBar
     */
    private fun initState() {
        viewModel.getState().observe(this, Observer { networkState ->
            if (networkState != null && networkState.status === NetworkState.Status.RUNNING) {
                progress_bar.visibility = View.VISIBLE
            } else {
                progress_bar.visibility = View.GONE
            }
        })
    }

    /**
     * RecyclerView item click listener and start a NewsDetailActivity
     * @param news : News selected
     */
    fun itemClicked(news: News) {
        val intent = Intent(this@NewsListActivity, NewsDetailActivity::class.java)
        intent.putExtra(NEWS_URL, news.url)
        startActivity(intent)
    }
}
