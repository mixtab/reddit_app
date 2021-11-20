package ua.com.jetpack.reddit_app.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import dagger.hilt.android.AndroidEntryPoint
import ua.com.jetpack.reddit_app.common.Resource
import ua.com.jetpack.reddit_app.databinding.FragmentNewsBinding
import ua.com.jetpack.reddit_app.domain.model.NewsList
import ua.com.jetpack.reddit_app.presentation.news_list.NewsListViewModel
import ua.com.jetpack.reddit_app.ui.base.BaseFragment
import ua.com.jetpack.reddit_app.ui.news.adapter.NewsAdapter
import ua.com.jetpack.reddit_app.ui.news.adapter.NewsItemDecorator

@AndroidEntryPoint
class NewsFragment() : BaseFragment<FragmentNewsBinding>(),NewsAdapter.EventListener {

    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentNewsBinding::inflate

    val viewModel: NewsListViewModel by viewModels()
    val state = viewModel.state.value

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeObservers()
    }

    private fun subscribeObservers() {
        viewModel.state.observe(viewLifecycleOwner, { state ->
            when {
                state.news != null ->{
                    showLoading(false)
                    showNews(state.news)

                }
                state.isLoading -> {
                    showLoading(true)
                }
                state.error.isNotBlank() ->{
                    showError(state.error)

                }
            }


        })
    }

    fun showNews(news:NewsList){
        with(binding){
            binding.recyclerNews.let {
                // it.addOnScrollListener(scrollListener)
                it.addItemDecoration(NewsItemDecorator())
                it.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
                it.adapter = NewsAdapter(news.data.children,this@NewsFragment)
            }
        }

    }

    private fun showLoading(show: Boolean) {
        binding.prgoressNews.visibility = if (show) View.VISIBLE else View.GONE
    }

    private fun showError(error:String){

    }

}