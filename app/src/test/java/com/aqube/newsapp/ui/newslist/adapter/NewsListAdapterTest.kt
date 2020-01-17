package com.aqube.newsapp.ui.newslist.adapter

import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Looper.getMainLooper
import android.view.View
import androidx.paging.PagedList
import com.aqube.newsapp.model.News
import com.aqube.newsapp.ui.newslist.NewsListActivity
import com.bumptech.glide.RequestManager
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.InjectMocks
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows.shadowOf
import org.robolectric.annotation.Config
import javax.inject.Inject
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestBuilder
import kotlinx.android.synthetic.main.item_news.view.*
import org.mockito.Mockito.*
import java.io.File


@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
class NewsListAdapterTest {
    var activity: NewsListActivity? = null
    var requestManager: RequestManager = mock(RequestManager::class.java)
    lateinit var adapter: NewsListAdapter

    @Before
    @Throws(Exception::class)
    fun setUp() {

        activity = Robolectric.buildActivity(NewsListActivity::class.java)
            .create()
            .resume()
            .get()

        adapter = NewsListAdapter(requestManager, { news: News -> activity?.itemClicked(news) })
    }

    @Test
    fun shouldNotBeNull() {
        assertNotNull(activity)
        assertNotNull(adapter)
    }

    @Test
    fun shouldSetDataToAdapter() {
        adapter.submitList(mockPagedList(getDummyNewsList()))
        assertEquals(adapter.itemCount, 3)
    }

    @Test
    fun abc() {
        // Given
        adapter.submitList(mockPagedList(getDummyNewsList()))
        val rvParent = RecyclerView(activity!!.baseContext)
        rvParent.layoutManager = LinearLayoutManager(activity!!.baseContext)
        val viewHolder = adapter.onCreateViewHolder(rvParent, 0)
        // When
        adapter.onBindViewHolder(viewHolder, 0)
        // Then
        assertEquals(View.VISIBLE, viewHolder.itemView.text_view_source.visibility);
    }

    private fun <T> mockPagedList(list: List<T>): PagedList<T> {
        val pagedList = mock(PagedList::class.java) as PagedList<T>
        `when`(pagedList.get(ArgumentMatchers.anyInt())).then { invocation ->
            val index = invocation.arguments.first() as Int
            list[index]
        }
        `when`(pagedList.size).thenReturn(list.size)
        return pagedList
    }

    private fun getDummyNewsList(): List<News> {
        shadowOf(getMainLooper()).idle()
        val news = News(urlToImage = "url")
        val news1 = News(urlToImage = "url")
        val news2 = News(urlToImage = "url")
        return listOf<News>(news, news1, news2)
    }
}