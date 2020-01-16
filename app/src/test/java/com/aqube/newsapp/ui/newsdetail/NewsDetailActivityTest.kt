package com.aqube.newsapp.ui.newsdetail

import android.os.Build
import com.aqube.newsapp.ui.newslist.NewsListActivity
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.Shadows
import org.robolectric.shadows.ShadowActivity
import android.R
import org.robolectric.Shadows.shadowOf
import org.robolectric.fakes.RoboMenuItem




@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
class NewsDetailActivityTest{

    var activity: NewsDetailActivity? = null

    @Before
    @Throws(Exception::class)
    fun setUp() {
        activity = Robolectric.buildActivity(NewsDetailActivity::class.java)
            .create()
            .resume()
            .get()
    }

    @Test
    fun shouldNotBeNull() {
        assertNotNull(activity)
    }

    @Test
    fun shouldCloseActivity() {
        shadowOf(activity).clickMenuItem(R.id.home)
        assertTrue(shadowOf(activity).isFinishing())
    }
}