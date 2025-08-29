package com.business.money_minder

import android.app.Application
import androidx.compose.foundation.ExperimentalFoundationApi

class App: Application() {
    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate() {
        super.onCreate()
    }
}