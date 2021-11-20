package ua.com.jetpack.reddit_app.ui.news.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ua.com.jetpack.reddit_app.data.remote.dto.NewsDto
import ua.com.jetpack.reddit_app.databinding.ItemNewsBinding
import ua.com.jetpack.reddit_app.extentions.loadPostImage

open class NewsAdapter(
    private val newsList: List<NewsDto>,
    private var listener: EventListener
) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    init {
        setHasStableIds(true)
    }

    interface EventListener {
        fun onNewsSelected(string: String) {}
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            ItemNewsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(vh: NewsViewHolder, position: Int) {
        with(vh.binding) {
            val post = newsList[position]
            textAuthor.text = post.data.author
            textCreated.text = post.data.created_utc.toString()
            image.loadPostImage(post.data.preview.images[0].resolutions[0])
            textAmountComments.text = post.data.num_comments.toString()

        }
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    fun updateNews(notifications: List<NewsDto>) {
    }

    override fun getItemId(position: Int): Long {
        return newsList.size.toLong()
    }

    class NewsViewHolder(var binding: ItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root)
}