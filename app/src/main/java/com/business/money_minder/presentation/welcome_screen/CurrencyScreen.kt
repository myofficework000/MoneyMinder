package com.business.money_minder.presentation.welcome_screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.contentColorFor
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.business.money_minder.R
import com.business.money_minder.domain.model.CurrencyModel
import com.business.money_minder.presentation.navigation.Screen
import com.business.money_minder.presentation.ui.theme.Manrope
import com.business.money_minder.presentation.ui.theme.RoyalBlue
import com.business.money_minder.util.OutlinedSearchBar
import com.business.money_minder.util.spacing
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@ExperimentalUnitApi
@ExperimentalFoundationApi
@Composable
fun CurrencyScreen(
    navController: NavController,
    setting: Boolean?,
    welcomeViewModel: WelcomeViewModel = hiltViewModel()
) {
    val currencies by welcomeViewModel.countryCurrenciesFiltered
    var selectedCountry by remember { mutableStateOf(CurrencyModel()) }

    val coroutineScope = rememberCoroutineScope()
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
    )

    BottomSheetScaffold(
        sheetContent = {
            ContinueButton(
                selectedCountry,
                setting,
                navController = navController,
                welcomeViewModel = welcomeViewModel
            )
        },
        scaffoldState = bottomSheetScaffoldState,
        sheetPeekHeight = 0.dp
    ) {
        Surface(color = MaterialTheme.colors.background) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = it.calculateBottomPadding()),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                /*Card(elevation = 1.dp) {
                    Text(
                        text = stringResource(id = R.string.select_your_currency),
                        style = MaterialTheme.typography.h6.copy(fontWeight = FontWeight.W700),
                        color = MaterialTheme.colors.onBackground,

                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                start = spacing.medium,
                                end = spacing.medium,
                                top = spacing.small
                            ),
                        textAlign = TextAlign.Start
                    )
                }*/

                OutlinedSearchBar(
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    label = { Text(stringResource(id = R.string.select_your_currency)) }
                ){
                    welcomeViewModel.filterSearchResult(it)
                }

                LazyColumn(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    contentPadding = PaddingValues(bottom = it.calculateBottomPadding())
                ) {
                    currencies.forEach { (firstChar, list) ->
                        stickyHeader {
                            Surface(color = MaterialTheme.colors.background) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .padding(
                                            start = spacing.medium,
                                            end = spacing.medium,
                                            top = spacing.medium
                                        )
                                ) {
                                    Text(
                                        text = firstChar.toString(),
                                        style = MaterialTheme.typography.subtitle1,
                                        modifier = Modifier
                                            .fillMaxWidth(),
                                        textAlign = TextAlign.Start
                                    )
                                }
                            }
                        }

                        items(list) { currency ->
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .clickable {
                                        selectedCountry = if (selectedCountry != currency) {
                                            coroutineScope.launch { bottomSheetScaffoldState.bottomSheetState.expand() }
                                            currency
                                        } else {
                                            coroutineScope.launch { bottomSheetScaffoldState.bottomSheetState.collapse() }
                                            CurrencyModel()
                                        }
                                    }
                                    .fillMaxWidth()
                                    .padding(
                                        horizontal = spacing.medium,
                                        vertical = spacing.small
                                    )
                            ) {
                                TextButton(
                                    onClick = {
                                        selectedCountry = if (selectedCountry != currency) {
                                            coroutineScope.launch { bottomSheetScaffoldState.bottomSheetState.expand() }
                                            currency
                                        } else {
                                            coroutineScope.launch { bottomSheetScaffoldState.bottomSheetState.collapse() }
                                            CurrencyModel()
                                        }
                                    },
                                    modifier = Modifier.fillMaxWidth(),
                                    colors = ButtonDefaults.buttonColors(
                                        backgroundColor = if (selectedCountry == currency)
                                            MaterialTheme.colors.primary
                                        else RoyalBlue.copy(alpha = 0.25f),
                                        contentColor = if (selectedCountry == currency)
                                            contentColorFor(backgroundColor = MaterialTheme.colors.primary)
                                        else MaterialTheme.colors.onSurface
                                    ),
                                    shape = RoundedCornerShape(8.dp),
                                    contentPadding = PaddingValues(20.dp)
                                ) {
                                    Text(
                                        text = buildAnnotatedString {
                                            withStyle(
                                                style = SpanStyle(
                                                    fontWeight = FontWeight.W600,
                                                    fontFamily = Manrope,
                                                    fontSize = 14.sp
                                                )
                                            ) {
                                                append(currency.country.uppercase())
                                            }

                                            withStyle(
                                                style = SpanStyle(
                                                    color = Color.White.copy(alpha = 0.5f),
                                                    fontWeight = FontWeight.Normal,
                                                    fontFamily = Manrope,
                                                    fontSize = 14.sp
                                                )
                                            ) {
                                                append(" (${currency.currencyCode})")
                                            }
                                        },
                                        style = MaterialTheme.typography.subtitle2,
                                        modifier = Modifier.fillMaxWidth(),
                                        textAlign = TextAlign.Start
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ContinueButton(
    currency: CurrencyModel,
    setting: Boolean?,
    navController: NavController,
    welcomeViewModel: WelcomeViewModel
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = spacing.medium,
                vertical = spacing.small
            )
    ) {
        Button(
            onClick = {
                welcomeViewModel.saveCurrency(currency.currencyCode)
                if (setting!!)
                    navController.popBackStack()
                else {
                    navController.popBackStack()
                    welcomeViewModel.saveOnBoardingState(completed = true)
                    welcomeViewModel.createAccounts()
                    navController.navigate(Screen.HomeScreen.route)
                }
            },
            modifier = Modifier
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = contentColorFor(backgroundColor = MaterialTheme.colors.primary)
            ),
            contentPadding = PaddingValues(vertical = 12.dp)
        ) {
            Text(
                text = stringResource(id = R.string.confirm),
                style = MaterialTheme.typography.button
            )
        }
    }
}