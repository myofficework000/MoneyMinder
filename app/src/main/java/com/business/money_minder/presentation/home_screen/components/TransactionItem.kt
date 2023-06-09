package com.business.money_minder.presentation.home_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.business.money_minder.common.Constants
import com.business.money_minder.domain.model.Transaction
import com.business.money_minder.presentation.home_screen.CategoryItem
import com.business.money_minder.presentation.home_screen.ExpenseCategory
import com.business.money_minder.presentation.home_screen.HomeViewModel
import com.business.money_minder.presentation.home_screen.IncomeCategory
import com.business.money_minder.presentation.home_screen.amountFormat
import com.business.money_minder.presentation.ui.theme.BG1ToBG2
import com.business.money_minder.presentation.ui.theme.GreenAlpha700
import com.business.money_minder.presentation.ui.theme.Red500
import com.business.money_minder.util.spacing

@ExperimentalMaterialApi
@ExperimentalUnitApi
@Composable
fun TransactionItem(
    transaction: Transaction,
    homeViewModel: HomeViewModel = hiltViewModel(),
    onItemClick: () -> Unit
) {
    val category = getCategory(transaction.category)
    val currencyCode by homeViewModel.selectedCurrencyCode.collectAsState()

    val small = spacing.small
    val medium = spacing.medium

    Card(
        onClick = {
            onItemClick()
        },
        elevation = 0.dp,
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = medium)
    ) {
        Box(modifier = Modifier.background(brush = BG1ToBG2)) {
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = spacing.small,
                        vertical = spacing.small
                    )
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {

                    Icon(
                        painter = painterResource(id = category.iconRes),
                        contentDescription = "transaction",
                        tint = Color.Black,
                        modifier = Modifier
                            .background(
                                Color.DarkGray.copy(alpha = 0.2f),
                                RoundedCornerShape(8.dp)
                            )
                            .padding(18.dp)
                    )

                    Column(verticalArrangement = Arrangement.SpaceBetween) {
                        if (transaction.title.isNotEmpty()) {
                            Text(
                                text = transaction.title.replaceFirstChar { it.uppercase() },
                                style = MaterialTheme.typography.body1,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )

                            Spacer(modifier = Modifier.height(spacing.extraSmall))
                        }

                        Text(
                            text = "${transaction.amount}".amountFormat() + " $currencyCode",
                            color = if (transaction.transactionType == Constants.INCOME)
                                GreenAlpha700
                            else Red500.copy(alpha = 0.75f),
                            style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.W600),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }

                Spacer(modifier = Modifier.height(spacing.medium))

                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {

                    Text(
                        text = category.title,
                        style = MaterialTheme.typography.caption,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .background(
                                category.bgRes,
                                shape = RoundedCornerShape(12.dp)
                            )
                            .padding(
                                vertical = small,
                                horizontal = medium
                            ),
                        color = category.colorRes,
                        letterSpacing = TextUnit(1.1f, TextUnitType.Sp)
                    )

                    Text(
                        text = transaction.account,
                        style = MaterialTheme.typography.caption,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .background(
                                Color.White,
                                shape = RoundedCornerShape(12.dp)
                            )
                            .padding(
                                vertical = small,
                                horizontal = medium
                            ),
                        color = Color.Black,
                        letterSpacing = TextUnit(1.1f, TextUnitType.Sp)
                    )
                }
            }
        }
    }
}

fun getCategory(title: String): CategoryItem {
    var result: CategoryItem = ExpenseCategory.FOOD_DRINK
    ExpenseCategory.values().forEach {
        if (it.title == title)
            result = it
    }
    IncomeCategory.values().forEach {
        if (it.title == title)
            result = it
    }
    return result
}