package com.mindorks.example.coroutines.democoroutine

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*

class MySuperClass(
    private val coroutineScope: CoroutineScope,
    private val dispatcher: CoroutineDispatcher
) {
    private val _result = MutableLiveData<Int>()
    val result: LiveData<Int> get() = _result

    suspend fun getANumber(): Int {
        delay(2000)
        return 2
    }

    suspend fun getAnotherNumber(): Int {
        delay(1000)
        return 4
    }

    fun addTwoNumbers() {
        coroutineScope.launch(dispatcher) {
            val deferredFirstValue = async { getANumber() }
            val deferredSecondValue = async { getAnotherNumber() }

            val res = deferredFirstValue.await() + deferredSecondValue.await()

            _result.postValue(res)
        }
    }
}