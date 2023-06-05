package com.business.money_minder.domain.repository

interface PrefsRepository {
    fun getPref(name: String): String?
    fun setPref(name: String, value: String)
}