package com.business.money_minder.data.local.prefs

import android.content.SharedPreferences
import com.business.money_minder.domain.repository.PrefsRepository
import javax.inject.Inject

class PrefsRepositoryImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
): PrefsRepository {
    override fun getPref(name: String) = sharedPreferences.getString(name, null)

    override fun setPref(name: String, value: String) {
        sharedPreferences.edit().putString(name, value).apply()
    }
}