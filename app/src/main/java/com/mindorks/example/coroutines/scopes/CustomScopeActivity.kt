package com.mindorks.example.coroutines.scopes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class CustomScopeActivity : AppCompatActivity(), CoroutineScope {
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