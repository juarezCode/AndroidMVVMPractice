package com.juarez.mvvmpractice.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.juarez.mvvmpractice.data.UserRepository
import com.juarez.mvvmpractice.viewmodels.UserViewModel

class UserViewModelFactory(private val repository: UserRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return UserViewModel(repository) as T
    }
}