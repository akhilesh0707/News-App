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
}