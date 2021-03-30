package com.mindorks.example.coroutines.workshop.myfirstcoroutine

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.mindorks.example.coroutines.R
import com.mindorks.example.coroutines.data.api.ApiHelperImpl
import com.mindorks.example.coroutines.data.api.RetrofitBuilder
import com.mindorks.example.coroutines.data.local.DatabaseBuilder
import com.mindorks.example.coroutines.data.local.DatabaseHelperImpl
import com.mindorks.example.coroutines.utils.Status
import com.mindorks.example.coroutines.utils.ViewModelFactory
import kotlinx.android.synthetic.main.activity_basic.*
import kotlinx.android.synthetic.main.activity_recycler_view.*

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
        rootLayout.setOnClickListener {
            viewModel.updateTaps()
        }
    }

    private fun setupObserver() {
        viewModel.taps.observe(this, Observer { value ->
            taps.text = value
        })
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
