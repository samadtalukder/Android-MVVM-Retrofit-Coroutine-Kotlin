package com.samad_talukder.androidmvvmretrofitcoroutinekotlin.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.samad_talukder.androidmvvmretrofitcoroutinekotlin.utils.CustomProgressBar

abstract class BaseActivity<viewBinding : ViewBinding> : AppCompatActivity() {

    private lateinit var customProgressBar: CustomProgressBar
    lateinit var binding: viewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = getViewBinding()

        customProgressBar = CustomProgressBar(this)

        setContentView(binding.root)

    }

    abstract fun getViewBinding(): viewBinding

    fun showLoading() {
        customProgressBar.showLoading()
    }

    fun hideLoading() {
        customProgressBar.hideLoading()
    }

    override fun onDestroy() {

        hideLoading()

        super.onDestroy()

    }
}