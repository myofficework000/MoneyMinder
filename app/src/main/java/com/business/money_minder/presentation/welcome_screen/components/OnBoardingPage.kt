package com.business.money_minder.presentation.welcome_screen.components

import com.business.money_minder.R
import com.business.money_minder.common.Constants.Companion.ADD_EXPENSES
import com.business.money_minder.common.Constants.Companion.ADD_EXPENSES_DETAIL
import com.business.money_minder.common.Constants.Companion.ANALYSIS
import com.business.money_minder.common.Constants.Companion.ANALYSIS_DETAIL
import com.business.money_minder.common.Constants.Companion.INSIGHTS
import com.business.money_minder.common.Constants.Companion.INSIGHTS_DETAIL

sealed class OnBoardingPage(
    val icon: String,
    val title: String,
    val description: String
) {
    object FirstPage : OnBoardingPage("expenses.json", ADD_EXPENSES, ADD_EXPENSES_DETAIL)

    object SecondPage : OnBoardingPage("insights.json", INSIGHTS, INSIGHTS_DETAIL)

    object ThirdPage : OnBoardingPage("chart.json", ANALYSIS, ANALYSIS_DETAIL)

/*
    object FirstPage : OnBoardingPage(R.drawable.entry, ADD_EXPENSES, ADD_EXPENSES_DETAIL)

    object SecondPage : OnBoardingPage(R.drawable.insight, INSIGHTS, INSIGHTS_DETAIL)

    object ThirdPage : OnBoardingPage(R.drawable.decision, ANALYSIS, ANALYSIS_DETAIL)
*/
}

