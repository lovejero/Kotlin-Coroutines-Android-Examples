package com.mindorks.example.coroutines.learn.retrofit.parallel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mindorks.example.coroutines.data.api.ApiHelper
import com.mindorks.example.coroutines.data.local.DatabaseHelper
import com.mindorks.example.coroutines.data.model.ApiUser
import com.mindorks.example.coroutines.utils.Resource
import kotlinx.coroutines.*

class ParallelNetworkCallsViewModel(
    private val apiHelper: ApiHelper,
    private val dbHelper: DatabaseHelper,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
) : ViewModel() {

    private val users = MutableLiveData<Resource<List<ApiUser>>>()

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        val job = viewModelScope.launch(dispatcher) {
            users.postValue(Resource.loading(null))
            try {
                // coroutineScope is needed, else in case of any network error, it will crash
                coroutineScope {
                    val usersFromApiDeferred = async { apiHelper.getUsers() }
                    val moreUsersFromApiDeferred = async { apiHelper.getMoreUsers() }

                    val usersFromApi = usersFromApiDeferred.await()
                    val moreUsersFromApi = moreUsersFromApiDeferred.await()

                    val allUsersFromApi = mutableListOf<ApiUser>()
                    allUsersFromApi.addAll(usersFromApi)
                    allUsersFromApi.addAll(moreUsersFromApi)

                    users.postValue(Resource.success(allUsersFromApi))
                }
            } catch (e: Exception) {
                val newMessage = e.message ?: ""
                users.postValue(Resource.error(newMessage, null))
            }
        }

        viewModelScope.launch(Dispatchers.IO) {
            // Do something
        }
        //job.cancel("Cancel single job")
        //viewModelScope.cancel("Cancel all scope jobs")

        viewModelScope.launch {
            //job.join()
            delay(1001)
            users.postValue(Resource.error("Nueva Coroutine iniciada", null))
        }
    }

    fun getUsers(): LiveData<Resource<List<ApiUser>>> {
        return users
    }

}