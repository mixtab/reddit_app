package ua.com.jetpack.reddit_app.ui.news.adapter

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import ua.com.jetpack.reddit_app.R
import ua.com.jetpack.reddit_app.databinding.ItemPostBinding
import ua.com.jetpack.reddit_app.domain.model.Children
import ua.com.jetpack.reddit_app.extentions.loadPostImage
import ua.com.jetpack.reddit_app.util.DateFormatUtil

open class PostsAdapter(
        private val postsList: MutableList<Children>
) : RecyclerView.Adapter<PostsAdapter.NewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
                ItemPostBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                )
        )
    }

    override fun onBindViewHolder(vh: NewsViewHolder, position: Int) {
        with(vh.binding) {
            val post = postsList[vh.bindingAdapterPosition]
            textAuthor.text = post.data.author
            textCreated.text = DateFormatUtil.convertUtcToTimeAgo(post.data.created_utc)
            textTitle.text = post.data.title

            if (post.data.url != "default") {
                image.apply {
                    visibility = View.VISIBLE
                    loadPostImage(post.data.url)
                    setOnClickListener {
                        try {
                            startActivity(
                                context,
                                Intent(
                                    Intent.ACTION_VIEW,
                                    Uri.parse(post.data.url)
                                ), null
                            )
                        } catch (e: ActivityNotFoundException) {
                            Toast.makeText(context, R.string.error_message, Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }else image.visibility = View.GONE


            val commentsAmount = post.data.num_comments.toString()
            textAmountComments.text = ("$commentsAmount Comments")

        }
    }

    override fun getItemCount(): Int {
        return postsList.size
    }

    fun insertPosts(posts: List<Children>) {
        val lastPosition: Int = this.postsList.size
        this.postsList.addAll(posts)
        notifyItemRangeInserted(lastPosition, postsList.size)
    }

    class NewsViewHolder(var binding: ItemPostBinding) :
            RecyclerView.ViewHolder(binding.root)
}