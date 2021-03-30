package com.mindorks.example.coroutines.workshop.myfirstcoroutine

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.mindorks.example.coroutines.R
import com.mindorks.example.coroutines.data.api.ApiHelperImpl
import com.mindorks.example.coroutines.data.api.RetrofitBuilder
import com.mindorks.example.coroutines.data.local.DatabaseBuilder
import com.mindorks.example.coroutines.data.local.DatabaseHelperImpl
import com.mindorks.example.coroutines.utils.ViewModelFactory

class MyFirstCoroutineActivity : AppCompatActivity() {

    private lateinit var viewModel: MyFirstCoroutineViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basic)
        setupUI()
        setupViewModel()
        setupObserver()
    }

    private fun setupUI() {
    }

    private fun setupObserver() {

    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(
                ApiHelperImpl(RetrofitBuilder.apiService),
                DatabaseHelperImpl(DatabaseBuilder.getInstance(applicationContext))
            )
        ).get(MyFirstCoroutineViewModel::class.java)
    }
}
