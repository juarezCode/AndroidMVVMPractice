package com.juarez.mvvmpractice.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.juarez.mvvmpractice.R
import com.juarez.mvvmpractice.data.UserRepository
import com.juarez.mvvmpractice.databinding.ActivityUserDetailBinding
import com.juarez.mvvmpractice.utils.UserViewModelFactory
import com.juarez.mvvmpractice.viewmodels.UserViewModel

class UserDetailActivity : AppCompatActivity() {
    private lateinit var viewModel: UserViewModel
    private lateinit var binding: ActivityUserDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val provider = UserViewModelFactory(UserRepository())
        viewModel = ViewModelProvider(this, provider).get(UserViewModel::class.java)

        val userId = intent.getIntExtra("userId", 1)
        viewModel.getUser(userId)

        loading()
        user()
        error()
    }

    private fun user() {
        viewModel.user.observe(this, Observer {
            Log.i("user", it.toString())
            binding.txtName.text = "name: ${it.name}"
        })
    }

    private fun loading() {
        viewModel.loading.observe(this, Observer {
            binding.progressDetail.isVisible = it
        })
    }

    private fun error() {
        viewModel.error.observe(this, Observer {
            binding.txtName.text = it
        })
    }
}