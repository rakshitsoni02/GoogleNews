package com.rax.googlenews.core.utils

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.SCROLL_STATE_IDLE


abstract class RecyclerViewPager(recyclerView: RecyclerView) : RecyclerView.OnScrollListener() {

    /*
     * This is the Page Limit for each request
     * */
    private val batchSize = 21L

    /*
     * Variable to keep track of the current page
     * */
    private var currentPage: Long = 1L

    /*
     * This variable is used to set
     * the threshold.
     * */
    private val threshold = 2

    /*
     * This is a hack to ensure that the app is notified
     * only once to fetch more data.
     * */
    private var endWithAuto = false

    /*
     * We pass the RecyclerView to the constructor
     * of this class to get the LayoutManager
     * */
    private val layoutManager: RecyclerView.LayoutManager?

    val startSize: Long
        get() = ++currentPage

    val maxSize: Long
        get() = currentPage + batchSize

    abstract val isLastPage: Boolean

    init {
        recyclerView.addOnScrollListener(this)
        this.layoutManager = recyclerView.layoutManager
    }

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)
        if (newState == SCROLL_STATE_IDLE && null != layoutManager) {
            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount

            var firstVisibleItemPosition = 0
            if (layoutManager is LinearLayoutManager) {
                firstVisibleItemPosition = layoutManager.findLastVisibleItemPosition()

            } else if (layoutManager is GridLayoutManager) {
                firstVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
            }
            if (isLastPage) return
            if (visibleItemCount + firstVisibleItemPosition + threshold >= totalItemCount) {
                if (!endWithAuto) {
                    endWithAuto = true
                    loadMore(startSize, maxSize)
                }
            } else {
                endWithAuto = false
            }
        }
    }

    fun reset() {
        currentPage = 0L
    }

    abstract fun loadMore(start: Long, count: Long)
}