package com.mindorks.example.coroutines

import android.app.Activity
import android.os.Bundle
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MyScopeActivity : Activity(), CoroutineScope{
    // get() = <Dispatcher> + <Job>
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + job


    private lateinit var job: Job


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        job = Job()

        CoroutineScope(coroutineContext).launch {

        }
    }

    override fun onDestroy() {
        coroutineContext.cancel()
        super.onDestroy()
    }
}