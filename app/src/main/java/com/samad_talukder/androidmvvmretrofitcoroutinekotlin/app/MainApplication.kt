package com.samad_talukder.androidmvvmretrofitcoroutinekotlin.app

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import android.os.Build
import timber.log.Timber
import java.util.*

class MainApplication : Application() {

    override fun attachBaseContext(base: Context?) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val config = Configuration()
            val localLanguageCode = "en"

            val local = Locale(localLanguageCode)
            Locale.setDefault(local)

            Timber.e("Local: $local")

            base?.createConfigurationContext(config)
        }

        super.attachBaseContext(base)

    }

}