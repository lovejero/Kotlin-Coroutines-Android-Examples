package com.mindorks.example.coroutines.workshop.myfirstcoroutine

import androidx.lifecycle.ViewModel
import com.mindorks.example.coroutines.data.api.ApiHelper
import com.mindorks.example.coroutines.data.local.DatabaseHelper

class MyFirstCoroutineViewModel(
    private val apiHelper: ApiHelper,
    private val dbHelper: DatabaseHelper
) : ViewModel() {
    init {
        doSomethingInBackground()
    }

    private fun doSomethingInBackground() {

    }

    fun doSomething() {
        return  doSomethingInBackground()
    }
}