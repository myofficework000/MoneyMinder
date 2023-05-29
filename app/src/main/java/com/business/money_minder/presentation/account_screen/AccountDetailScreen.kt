package com.business.money_minder.presentation.account_screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.business.money_minder.presentation.home_screen.components.ListPlaceholder
import com.business.money_minder.presentation.home_screen.components.TransactionItem
import com.business.money_minder.util.spacing

@ExperimentalUnitApi
@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun AccountDetailScreen(accountName: String?, accountViewModel: AccountViewModel = hiltViewModel()) {

    val transactions by accountViewModel.transactions.collectAsState()
    if (accountName != null) {
        accountViewModel.getTransaction(accountName)
    }

    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier.padding(
            top = spacing.small
        )
    ) {
        transactions.ifEmpty {
            ListPlaceholder()
        }

        LazyColumn(
            contentPadding = PaddingValues(
                start = spacing.medium,
                top = spacing.small,
                end = spacing.medium
            )
        ) {
            item {
                Text(
                    text = "Transactions",
                    color = MaterialTheme.colors.onBackground,
                    style = MaterialTheme.typography.h5.copy(fontWeight = FontWeight.Normal),
                )

                Text(
                    text = accountName!!,
                    style = MaterialTheme.typography.h3.copy(fontSize = 20.sp, fontWeight = FontWeight.W700),
                    color = MaterialTheme.colors.onBackground
                )
            }

            transactions.forEach { (date, allTrx) ->
                stickyHeader {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(
                                vertical = spacing.small
                            ),
                    ) {
                        Text(
                            text = date,
                            style = MaterialTheme.typography.body2,
                            color = MaterialTheme.colors.onBackground,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Start
                        )
                    }
                }

                itemsIndexed(allTrx) { _, transaction ->
                    TransactionItem(
                        transaction = transaction,
                        onItemClick = {}
                    )
                }
            }
        }
    }
}