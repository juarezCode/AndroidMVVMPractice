package com.juarez.mvvmpractice.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.juarez.mvvmpractice.data.UserRepository
import com.juarez.mvvmpractice.models.User
import com.juarez.mvvmpractice.utils.isNull
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository) : ViewModel() {
    val users by lazy { MutableLiveData<List<User>>() }
    val user by lazy { MutableLiveData<User>() }
    val error = MutableLiveData<String>()
    val loading = MutableLiveData<Boolean>()

    fun getUsers(name: String) = viewModelScope.launch {
        Log.i("viewModel", "getUsers")
        loading.value = true
        val res = repository.getUsers(name)

        if (!res.result.isNullOrEmpty()) users.value = res.result
        else error.value = res.error ?: "error 500"

        loading.value = false
    }

    fun getUser(userId: Int) = viewModelScope.launch {
        Log.i("viewModel", "getUser")
        loading.value = true

        val res = repository.getUser(userId)

        if (!res.result.isNull()) user.value = res.result
        else error.value = res.error ?: "error 500"

        loading.value = false
    }
}