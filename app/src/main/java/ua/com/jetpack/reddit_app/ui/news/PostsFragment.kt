package ua.com.jetpack.reddit_app.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.*
import androidx.viewbinding.ViewBinding
import dagger.hilt.android.AndroidEntryPoint
import ua.com.jetpack.reddit_app.databinding.FragmentPostsBinding
import ua.com.jetpack.reddit_app.domain.model.Posts
import ua.com.jetpack.reddit_app.presentation.news_list.PostsListViewModel
import ua.com.jetpack.reddit_app.ui.base.BaseFragment
import ua.com.jetpack.reddit_app.ui.news.adapter.PostsAdapter
import ua.com.jetpack.reddit_app.ui.news.adapter.PostsItemDecorator

@AndroidEntryPoint
class PostsFragment : BaseFragment<FragmentPostsBinding>(){

    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentPostsBinding::inflate

    val viewModel: PostsListViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       subscribeObservers()
        initRecycler()
    }

    private fun initRecycler() {
        binding.recyclerNews.let {
            it.addOnScrollListener(postsLoadListener)
            it.addItemDecoration(PostsItemDecorator())
            it.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            it.adapter = PostsAdapter(mutableListOf())
        }
    }

   private fun subscribeObservers() {
        viewModel.state.observe(viewLifecycleOwner, { state ->
            when {
                state.news != null -> {
                    showLoading(false)
                    showPostsLoading(false)
                    showNews(state.news)
                }
                state.isLoading -> {
                    showPostsLoading(true)
                }
                state.error.isNotBlank() -> {
                    showError(state.error)
                }
            }
        })
    }

    private fun showNews(posts: Posts) {
        val adapter = binding.recyclerNews.adapter as PostsAdapter
        adapter.insertPosts(posts.data.children)
    }


    var isLoadingPosts = false
    private val postsLoadListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            if (!isLoadingPosts) {
                val postsAdapter = (binding.recyclerNews.adapter as PostsAdapter)
                val offset = postsAdapter.itemCount
                val lastVisibleItemPosition: Int =
                        (recyclerView.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()
                if (lastVisibleItemPosition >= offset - 4) {
                    viewModel.getNews()
                }
            }
        }
    }

    private fun showLoading(show: Boolean) {
        isLoadingPosts = show
        binding.prgoressNews.visibility = if (show) View.VISIBLE else View.GONE
    }

    private fun showPostsLoading(show: Boolean) {
        isLoadingPosts = show
        binding.progressNextPosts.visibility = if (show) View.VISIBLE else View.GONE
    }

    private fun showError(error: String) {
        Toast.makeText(context, error, Toast.LENGTH_LONG).show()
    }

}