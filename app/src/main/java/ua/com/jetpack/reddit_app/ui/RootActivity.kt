package ua.com.jetpack.reddit_app.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ua.com.jetpack.reddit_app.R
import ua.com.jetpack.reddit_app.databinding.ActivityRootBinding

class RootActivity : AppCompatActivity(){

    val binding = ActivityRootBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_root)
    }
}