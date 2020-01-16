package com.aqube.newsapp.ui.newslist

import android.os.Build
import android.os.Looper.getMainLooper
import androidx.paging.PagedList
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import androidx.recyclerview.widget.RecyclerView
import com.aqube.newsapp.R
import com.aqube.newsapp.model.News
import com.aqube.newsapp.ui.newslist.adapter.NewsListAdapter
import org.mockito.Mockito.*
import org.robolectric.Shadows.shadowOf


@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])

class NewsListActivityTest {

    var activity: NewsListActivity? = null

    @Before
    @Throws(Exception::class)
    fun setUp() {
        activity = Robolectric.buildActivity(NewsListActivity::class.java)
            .create()
            .resume()
            .get()
    }

    @Test
    fun shouldNotBeNull() {
        assertNotNull(activity)
    }

    @Test
    fun shouldClickRecyclerViewItem() {
        val currentRecyclerView = activity?.findViewById(R.id.recycler_view) as RecyclerView
        currentRecyclerView.measure(0,0)
        currentRecyclerView.layout(0,0,100,1000)
        shadowOf(getMainLooper()).idle();
        var newsListAdapter = mock(NewsListAdapter::class.java)
        val pagedList = mock(PagedList::class.java) as PagedList<News>
        newsListAdapter.submitList(pagedList)
        currentRecyclerView.adapter =newsListAdapter
        currentRecyclerView.findViewHolderForItemId(0)
            .itemView
            .performClick()

    }
}