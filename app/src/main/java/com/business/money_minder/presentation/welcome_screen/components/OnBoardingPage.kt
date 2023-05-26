package com.business.money_minder.presentation.welcome_screen.components

import com.business.money_minder.common.Constants.Companion.ADD_EXPENSES
import com.business.money_minder.common.Constants.Companion.ADD_EXPENSES_DETAIL
import com.business.money_minder.common.Constants.Companion.ANALYSIS
import com.business.money_minder.common.Constants.Companion.ANALYSIS_DETAIL
import com.business.money_minder.common.Constants.Companion.ANALYSIS_ICON
import com.business.money_minder.common.Constants.Companion.EXPENSES_ICON
import com.business.money_minder.common.Constants.Companion.INSIGHTS
import com.business.money_minder.common.Constants.Companion.INSIGHTS_DETAIL
import com.business.money_minder.common.Constants.Companion.INSIGHTS_ICON

sealed class OnBoardingPage(
    val icon: String,
    val title: String,
    val description: String
) {
    object FirstPage : OnBoardingPage(EXPENSES_ICON, ADD_EXPENSES, ADD_EXPENSES_DETAIL)

    object SecondPage : OnBoardingPage(INSIGHTS_ICON, INSIGHTS, INSIGHTS_DETAIL)

    object ThirdPage : OnBoardingPage(ANALYSIS_ICON, ANALYSIS, ANALYSIS_DETAIL)

    /*
        object FirstPage : OnBoardingPage(R.drawable.entry, ADD_EXPENSES, ADD_EXPENSES_DETAIL)

        object SecondPage : OnBoardingPage(R.drawable.insight, INSIGHTS, INSIGHTS_DETAIL)

        object ThirdPage : OnBoardingPage(R.drawable.decision, ANALYSIS, ANALYSIS_DETAIL)
    */
}

