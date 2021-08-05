package com.samad_talukder.androidmvvmretrofitcoroutinekotlin

import android.os.Bundle
import com.samad_talukder.androidmvvmretrofitcoroutinekotlin.app.BaseActivity
import com.samad_talukder.androidmvvmretrofitcoroutinekotlin.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.textView.text = getString(R.string.normal_text)

    }

    override fun getViewBinding() = ActivityMainBinding.inflate(layoutInflater)
}