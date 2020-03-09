package com.rax.googlenews.core.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rax.googlenews.R
import com.rax.googlenews.core.utils.RecyclerViewPager
import com.rax.googlenews.core.utils.gone
import com.rax.googlenews.core.utils.visible
import com.rax.googlenews.news.model.vo.NewsArticle
import com.rax.googlenews.news.view.NewsHeadlinesFragment
import kotlin.math.max
import kotlin.reflect.KFunction

/**
 * A custom implementation of [RecyclerView] to support
 * Empty View & Loading animation.
 */
class MagicRecyclerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : RecyclerView(context, attrs, defStyle) {

    /**
     * Empty layout
     */
    private var emptyView: View? = null

    /**
     * Progress view
     */
    private var progressView: View? = null

    /**
     * Column width for grid layout
     */
    private var columnWidth: Int = 0

    init {
        //Gone at starting to show loading state
        gone()
        if (attrs != null) {
            val attrsArray = intArrayOf(android.R.attr.columnWidth)
            val array = context.obtainStyledAttributes(
                attrs, attrsArray
            )
            columnWidth = array.getDimensionPixelSize(0, -1)
            array.recycle()
        }
    }

    override fun setAdapter(adapter: Adapter<*>?) {
        visible()
        val oldAdapter = getAdapter()
        oldAdapter?.unregisterAdapterDataObserver(mAdapterObserver)
        super.setAdapter(adapter)
        adapter?.registerAdapterDataObserver(mAdapterObserver)
        refreshState()
    }

    private fun refreshState() {
        adapter?.let {
            val noItems = 0 == it.itemCount
            if (noItems) {
                progressView?.gone()
                emptyView?.visible()
                gone()
            } else {
                progressView?.gone()
                emptyView?.gone()
                visible()
            }
        }
    }

    fun setEmptyView(emptyView: View) {
        this.emptyView = emptyView
        this.emptyView?.gone()
    }

    fun setProgressView(progressView: View) {
        this.progressView = progressView
        this.progressView?.visible()
    }

    fun setEmptyMessage(@StringRes mEmptyMessageResId: Int) {
        val emptyText = emptyView?.findViewById<TextView>(R.id.empty_title)
        emptyText?.setText(mEmptyMessageResId)
    }

    fun setEmptyIcon(@DrawableRes mEmptyIconResId: Int) {
        val emptyImage = emptyView?.findViewById<ImageView>(R.id.empty_image)
        emptyImage?.setImageResource(mEmptyIconResId)
    }

    fun showLoading() {
        emptyView?.gone()
        progressView?.visible()
    }

    fun setSpanConfiguration() {
        if (layoutManager is GridLayoutManager) {
            (layoutManager as GridLayoutManager).spanCount = NewsHeadlinesFragment.SPAN_COUNT
            (layoutManager as GridLayoutManager).spanSizeLookup = object :
                GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return if (((position + 1) % 7) == 1) NewsHeadlinesFragment.SPAN_COUNT else 1
                }
            }.apply {
                this.isSpanIndexCacheEnabled = true
            }
        }
    }

    fun setPagingListener(listener: (Long) -> Unit, isLast: () -> Boolean) {
        addOnScrollListener(object : RecyclerViewPager(this) {
            override val isLastPage: Boolean
                get() = isLast.invoke()

            override fun loadMore(start: Long, count: Long) {
                listener(start)
            }
        })
    }

    /**
     * Observes for changes in the adapter and is triggered on change
     */
    private val mAdapterObserver = object : RecyclerView.AdapterDataObserver() {
        override fun onChanged() = refreshState()
        override fun onItemRangeInserted(positionStart: Int, itemCount: Int) = refreshState()
        override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) = refreshState()
    }

}
