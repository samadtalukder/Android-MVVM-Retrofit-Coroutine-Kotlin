package com.samad_talukder.androidmvvmretrofitroomdbinkotlin.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.samad_talukder.androidmvvmretrofitroomdbinkotlin.R
import com.samad_talukder.androidmvvmretrofitroomdbinkotlin.adapter.RandomUserAdapter
import com.samad_talukder.androidmvvmretrofitroomdbinkotlin.db.UserDB
import com.samad_talukder.androidmvvmretrofitroomdbinkotlin.repository.RandomUserRepository
import com.samad_talukder.androidmvvmretrofitroomdbinkotlin.ui.viewmodel.RandomUserViewModel
import com.samad_talukder.androidmvvmretrofitroomdbinkotlin.ui.viewmodel.ViewModelProviderFactory
import com.samad_talukder.androidmvvmretrofitroomdbinkotlin.utils.HandleResource
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var randomUserViewModel: RandomUserViewModel
    private lateinit var randomUserAdapter: RandomUserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val randomUserRepository = RandomUserRepository(UserDB(this))
        val viewModelProviderFactory = ViewModelProviderFactory(randomUserRepository)

        randomUserViewModel =
            ViewModelProvider(this, viewModelProviderFactory)
                .get(RandomUserViewModel::class.java)

        setUpRecyclerView()

        randomUserAdapter.setOnClickListener {
            intent = Intent(this, DetailsActivity::class.java)
            startActivity(intent)
        }

        /*MainScope().launch {
            delay(500L)
            randomUserViewModel.getRandomUser()
        }*/

        MainScope().launch {
            delay(500L)
            randomUserViewModel.getRandomUserWithParameter(5)
        }



        randomUserViewModel.randomUser.observe(this, { response ->
            when (response) {
                is HandleResource.Loading -> {
                    showProgressBar()
                }
                is HandleResource.Success -> {
                    hideProgressBar()
                    response.data?.let { randomUserResponse ->
                        randomUserAdapter.differ.submitList(randomUserResponse.results)
                    }
                }
                is HandleResource.Error -> {
                    hideProgressBar()
                    response.message?.let { response -> Log.e("Error", response) }
                }
            }
        })


    }

    private fun setUpRecyclerView() {
        randomUserAdapter = RandomUserAdapter()
        rvBreakingNews.apply {
            rvBreakingNews.adapter = randomUserAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    private fun showProgressBar() {
        paginationProgressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        paginationProgressBar.visibility = View.INVISIBLE
    }
}