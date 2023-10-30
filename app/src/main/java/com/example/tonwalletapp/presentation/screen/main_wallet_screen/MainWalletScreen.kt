package com.example.tonwalletapp.presentation.screen.main_wallet_screen

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType.Companion.Text
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.tonwalletapp.R
import com.example.tonwalletapp.presentation.navigation.Screen
import com.example.tonwalletapp.presentation.view.PrimaryButtonApp
import com.example.tonwalletapp.presentation.view.TonCrystalLoadingSpinner
import com.example.tonwalletapp.presentation.view.TransactionItemList
import com.example.tonwalletapp.presentation.view.TransactionsLoadingSpinner
import com.example.tonwalletapp.presentation.view.WalletJustCreatedSplash
import com.example.tonwalletapp.ui.theme.AppTheme
import com.example.tonwalletapp.until.Constants.IS_NOT_AUTHORIZED
import com.example.tonwalletapp.until.Constants.RECEIVE_BTN_TITLE
import com.example.tonwalletapp.until.Constants.SEND_BTN_TITLE
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainWalletScreen(
    navController: NavController,
    viewModel: MainWalletViewModel
) {

    val coroutineScope = rememberCoroutineScope()

    val walletUIState by viewModel.walletUIState.collectAsState()
    val transactionUIState by viewModel.transactionsUIState.collectAsState()
    val walletAddressUIState by viewModel.walletAddressUIState.collectAsState()

    val scaffoldState = rememberBottomSheetScaffoldState()


    val scrollableState = rememberScrollState()

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetContent = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 500.dp)

            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(AppTheme.colors.background),
                    elevation = 0.dp

                ) {
                    if(transactionUIState.isLoading){
                        Column(
                            modifier = Modifier
                                .padding(50.dp)
                                .fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            TransactionsLoadingSpinner(
                                tonIconModifier = Modifier
                                    .width(46.dp)
                                    .height(45.dp),
                                dollarIconModifier = Modifier
                                    .width(39.dp)
                                    .height(51.dp),
                                arrowIconModifier = Modifier
                                    .width(40.dp)
                                    .height(38.dp)
                            )
                        }
                    }
                    Column(
                        modifier = Modifier
                            .padding(top = 5.dp)
                    ) {
                        val transactions = transactionUIState.txt
                        transactions?.forEach { txt ->
                            Spacer(modifier = Modifier.height(14.dp))
                            Box(modifier = Modifier.padding(horizontal = 16.dp)) {
                                TransactionItemList(transactionDetail = txt)
                            }
                            Spacer(modifier = Modifier.height(16.dp))
                            Divider(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(1.dp)
                                    .background(AppTheme.colors.secondFormColor)
                            )
                        }
                    }
                }
                if(transactionUIState.txt != null && transactionUIState.txt!!.isEmpty() && walletAddressUIState != null){
                    WalletJustCreatedSplash(address = walletAddressUIState!!)
                }
            }
        },
        drawerGesturesEnabled = true,
        sheetPeekHeight = 400.dp,
        sheetShape = RoundedCornerShape(topEnd = 12.dp, topStart = 12.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(AppTheme.colors.secondBackground)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollableState)
                    .padding(top = 76.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(57.dp)
            ) {
                if(walletUIState.isLoading){
                    TonCrystalLoadingSpinner(
                        modifier = Modifier.size(60.dp)
                    )
                }
                if(walletUIState.walletDetail != null) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(5.dp),
                            verticalAlignment = Alignment.Bottom
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ton_crystal_frame),
                                contentDescription = "",
                                modifier = Modifier.size(60.dp),
                                contentScale = ContentScale.Crop
                            )
                            Column(verticalArrangement = Arrangement.spacedBy(11.dp)) {
                                Text(
                                    text = viewModel.formatAddress(walletUIState.walletDetail!!.address),
                                    fontSize = 15.sp,
                                    color = AppTheme.colors.background
                                )
                                Text(
                                    text = String.format("%.4f", walletUIState.walletDetail!!.balance),
                                    fontSize = 44.sp,
                                    color = AppTheme.colors.background,
                                    fontWeight = FontWeight.Medium
                                )
                            }
                        }
                    }
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.padding(horizontal = 20.dp)
                ) {
                    PrimaryButtonApp(
                        text = RECEIVE_BTN_TITLE,
                        icon = R.drawable.receive,
                        modifier = Modifier.weight(1f)
                    ) {

                    }
                    PrimaryButtonApp(
                        text = SEND_BTN_TITLE,
                        icon = R.drawable.send,
                        modifier = Modifier.weight(1f)
                    ) {
                        coroutineScope.launch {
                            scaffoldState.drawerState.open()
                        }
                    }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
                    .align(Alignment.TopCenter),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column() {
                }
                Row(
                    modifier = Modifier.padding(horizontal = 10.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.scan),
                        contentDescription = "",
                        tint = AppTheme.colors.background,
                        modifier = Modifier
                            .size(24.dp)
                            .clickable {
                            }
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.setting),
                        contentDescription = "",
                        tint = AppTheme.colors.background,
                        modifier = Modifier
                            .size(24.dp)
                            .clickable {

                            }
                    )

                }
            }

        }
    }

}