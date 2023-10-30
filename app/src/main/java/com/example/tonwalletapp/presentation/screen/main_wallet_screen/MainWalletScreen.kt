package com.example.tonwalletapp.presentation.screen.main_wallet_screen

import android.util.Log
import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType.Companion.Text
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.tonwalletapp.R
import com.example.tonwalletapp.presentation.navigation.Screen
import com.example.tonwalletapp.presentation.screen.main_wallet_screen.view.SendTonView
import com.example.tonwalletapp.presentation.screen.main_wallet_screen.view.TransactionsListView
import com.example.tonwalletapp.presentation.view.PrimaryButtonApp
import com.example.tonwalletapp.presentation.view.TonCrystalLoadingSpinner
import com.example.tonwalletapp.presentation.view.TransactionItemList
import com.example.tonwalletapp.presentation.view.TransactionsLoadingSpinner
import com.example.tonwalletapp.presentation.view.WalletJustCreatedSplash
import com.example.tonwalletapp.ui.theme.AppTheme
import com.example.tonwalletapp.until.Constants
import com.example.tonwalletapp.until.Constants.IS_NOT_AUTHORIZED
import com.example.tonwalletapp.until.Constants.RECEIVE_BTN_TITLE
import com.example.tonwalletapp.until.Constants.SEND_BOTTOM_SHEET_CONTENT
import com.example.tonwalletapp.until.Constants.SEND_BTN_TITLE
import com.example.tonwalletapp.until.Constants.TRANSACTIONS_BOTTOM_SHEET_CONTENT
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
    val currentBottomSheetContentUIState by viewModel.currentBottomSheetContentUIState.collectAsState()
    
    val scaffoldState = rememberBottomSheetScaffoldState()
    val scrollableState = rememberScrollState()

    LaunchedEffect(scaffoldState.bottomSheetState.isCollapsed){
        if(currentBottomSheetContentUIState != TRANSACTIONS_BOTTOM_SHEET_CONTENT && scaffoldState.bottomSheetState.isCollapsed){
            viewModel.changeBottomSheetContext(TRANSACTIONS_BOTTOM_SHEET_CONTENT)
        }
    }

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetContent = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 1500.dp)

            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(AppTheme.colors.background),
                    elevation = 0.dp

                ) {

                    when(currentBottomSheetContentUIState){
                        TRANSACTIONS_BOTTOM_SHEET_CONTENT ->{
                            TransactionsListView(
                                isLoading = transactionUIState.isLoading,
                                txt = transactionUIState.txt,
                                justCreatedWallet = transactionUIState.txt != null && transactionUIState.txt!!.isEmpty() && walletAddressUIState != null,
                                walletAddress = walletAddressUIState
                            )
                        }
                        SEND_BOTTOM_SHEET_CONTENT -> {
                            SendTonView()
                        }
                    }
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
                        viewModel.changeBottomSheetContext(SEND_BOTTOM_SHEET_CONTENT)
                        coroutineScope.launch {
                            scaffoldState.bottomSheetState.expand()
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