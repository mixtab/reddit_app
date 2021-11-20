package ua.com.jetpack.reddit_app.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import ua.com.jetpack.reddit_app.databinding.ActivityRootBinding

@AndroidEntryPoint
class RootActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRootBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setTheme(R.style.Theme_PublicApiApp)
        binding = ActivityRootBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}