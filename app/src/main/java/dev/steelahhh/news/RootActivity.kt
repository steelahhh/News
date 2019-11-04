package dev.steelahhh.news

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class RootActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_root)
    }
}
