package com.rax.googlenews.news.view

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.rax.googlenews.R
import com.rax.googlenews.core.utils.formatPublishedDate
import com.rax.googlenews.core.utils.inflate
import com.rax.googlenews.news.model.vo.NewsArticle
import kotlinx.android.synthetic.main.layout_news_headline.view.*

/**
 * The News adapter to show the news in a list.
 */
class NewsArticlesAdapter(
    private val listener: (NewsArticle) -> Unit
) : ListAdapter<NewsArticle, NewsArticlesAdapter.NewsHolder>(DIFF_CALLBACK) {

//    private val newsHeadLineList: MutableList<NewsArticle> = mutableListOf()


    /**
     * Inflate the view
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        NewsHolder(parent.inflate(R.layout.layout_news_headline))

    /**
     * Bind the view with the data
     */
    override fun onBindViewHolder(newsHolder: NewsHolder, position: Int) =
        newsHolder.bind(getItem(position), listener)

    /**
     * View Holder Pattern
     */
    class NewsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        /**
         * Binds the UI with the data and handles clicks
         */
        fun bind(newsArticle: NewsArticle, listener: (NewsArticle) -> Unit) = with(itemView) {
            newsTitle.text = newsArticle.title
//            2020-04-02T08:27:39Z
            newsAuthor.text = newsArticle.author
//            //TODO: need to format date
            //tvListItemDateTime.text = getFormattedDate(newsArticle.publishedAt)
            newsPublishedAt.text = newsArticle.publishedAt?.formatPublishedDate()
            Glide.with(context)
                .load(newsArticle.urlToImage)
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.tools_placeholder)
                        .error(R.drawable.tools_placeholder)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                )
                .into(newsImage)
            setOnClickListener { listener(newsArticle) }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<NewsArticle>() {
            override fun areItemsTheSame(oldItem: NewsArticle, newItem: NewsArticle): Boolean =
                oldItem.title == newItem.title

            override fun areContentsTheSame(oldItem: NewsArticle, newItem: NewsArticle): Boolean =
                oldItem == newItem
        }
    }
}