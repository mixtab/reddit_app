package ua.com.jetpack.reddit_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ua.com.jetpack.reddit_app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val binding = ActivityMainBinding.inflate(layoutInflater)
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}