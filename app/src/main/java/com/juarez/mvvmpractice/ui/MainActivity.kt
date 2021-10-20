package com.juarez.mvvmpractice.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.juarez.mvvmpractice.data.UserRepository
import com.juarez.mvvmpractice.data.UsersAdapter
import com.juarez.mvvmpractice.viewmodels.UserViewModel
import com.juarez.mvvmpractice.utils.UserViewModelFactory
import com.juarez.mvvmpractice.databinding.ActivityMainBinding
import com.juarez.mvvmpractice.models.User

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: UserViewModel
    private lateinit var binding: ActivityMainBinding
    private var usersAdapter = UsersAdapter(arrayListOf()) { onItemClick(it) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val provider = UserViewModelFactory(UserRepository())
        viewModel = ViewModelProvider(this, provider).get(UserViewModel::class.java)

        initViews()

        loading()
        error()
        users()
    }

    private fun initViews() {
        binding.recyclerUsers.apply {
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            adapter = usersAdapter
        }

        binding.btnClick.setOnClickListener {
            Log.i("activity", "click")
            binding.txtError.text = ""
            viewModel.getUsers("Jose")
        }
    }

    private fun users() {
        viewModel.users.observe(this, Observer { users ->
            usersAdapter.updateData(users)
        })
    }

    private fun error() {
        viewModel.error.observe(this, Observer {
            binding.txtError.text = it
            Log.i("error", it)
        })
    }

    private fun loading() {
        viewModel.loading.observe(this, Observer {
            binding.progressBar.isVisible = it
        })
    }

    private fun onItemClick(user: User) {
        val intent = Intent(this, UserDetailActivity::class.java)
        intent.putExtra("userId", user.id)
        startActivity(intent)
    }
}

