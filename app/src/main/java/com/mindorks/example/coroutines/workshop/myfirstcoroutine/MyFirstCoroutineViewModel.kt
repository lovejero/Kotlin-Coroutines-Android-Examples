package com.mindorks.example.coroutines.workshop.myfirstcoroutine

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mindorks.example.coroutines.utils.BACKGROUND

class MyFirstCoroutineViewModel() : ViewModel() {

    private var tapCount = 0
    private val _taps = MutableLiveData<String>()
    val taps: LiveData<String>
        get() = _taps

    fun updateTaps() {
        // TODO: Convert updateTaps to use coroutines
        tapCount++
        BACKGROUND.submit {
            Thread.sleep(1_000)
            _taps.postValue("$tapCount taps")
        }
    }
}