package com.samad_talukder.androidmvvmretrofitcoroutinekotlin.utils

import android.app.Activity
import android.app.Dialog
import android.view.View
import android.widget.ProgressBar
import com.samad_talukder.androidmvvmretrofitcoroutinekotlin.R
import kotlinx.android.synthetic.main.custom_prograss_bar_dialog.view.*

import timber.log.Timber
import java.lang.Exception

class CustomProgressBar(activity: Activity) {

    private var dialog: Dialog
    private var pbLoading: ProgressBar


    init {
        val view: View = activity.layoutInflater.inflate(R.layout.custom_prograss_bar_dialog, null)
        pbLoading = view.pb_loading
        dialog = Dialog(activity)

        dialog.apply {
            setContentView(view)
            window?.setBackgroundDrawableResource(R.color.colorTransparent)
            setCancelable(false)
        }
    }

    fun showLoading() {
        try {
            if (!dialog.isShowing) {
                dialog.show()
            }
        } catch (e: Exception) {
            Timber.e("Error:$e")
        }


    }

    fun hideLoading() {
        try {
            dialog.cancel()
        } catch (e: Exception) {
            Timber.e("Error:$e")
        }
    }

}