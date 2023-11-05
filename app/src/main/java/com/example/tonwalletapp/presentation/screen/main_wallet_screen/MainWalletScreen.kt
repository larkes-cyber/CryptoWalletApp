package com.example.tonwalletapp.presentation.screen.main_wallet_screen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.*
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.tonwalletapp.R
import com.example.tonwalletapp.domain.mapper.toRoundAmount
import com.example.tonwalletapp.presentation.screen.main_wallet_screen.view.send_ton.SendTonView
import com.example.tonwalletapp.presentation.screen.main_wallet_screen.view.TransactionsListView
import com.example.tonwalletapp.presentation.component.PrimaryButtonApp
import com.example.tonwalletapp.presentation.component.TonCrystalLoadingSpinner
import com.example.tonwalletapp.presentation.navigation.Screen
import com.example.tonwalletapp.presentation.screen.QrCodeScannerView
import com.example.tonwalletapp.presentation.screen.main_wallet_screen.view.receive_ton.ReceiveTonView
import com.example.tonwalletapp.presentation.screen.main_wallet_screen.view.txt_detail.TransactionDetailView
import com.example.tonwalletapp.ui.theme.AppTheme
import com.example.tonwalletapp.until.Constants.RECEIVE_BOTTOM_SHEET_CONTENT
import com.example.tonwalletapp.until.Constants.RECEIVE_BTN_TITLE
import com.example.tonwalletapp.until.Constants.SEND_BOTTOM_SHEET_CONTENT
import com.example.tonwalletapp.until.Constants.SEND_BTN_TITLE
import com.example.tonwalletapp.until.Constants.TRANSACTIONS_BOTTOM_SHEET_CONTENT
import com.example.tonwalletapp.until.Constants.TRANSACTION_BOTTOM_SHEET_CONTENT
import com.example.tonwalletapp.until.copyToClipboard
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
fun MainWalletScreen(
    navController: NavController,
    viewModel: MainWalletViewModel
) {

    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    val walletUIState by viewModel.walletUIState.collectAsState()
    val transactionUIState by viewModel.transactionsUIState.collectAsState()
    val walletAddressUIState by viewModel.walletAddressUIState.collectAsState()
    val currentBottomSheetContentUIState by viewModel.currentBottomSheetContentUIState.collectAsState()
    val sendAddressUIState by viewModel.sendAddressUIState.collectAsState()

    val scaffoldState = rememberBottomSheetScaffoldState()
    val scrollableState = rememberScrollState()


    LaunchedEffect(scaffoldState.bottomSheetState.isCollapsed){
        if(currentBottomSheetContentUIState != TRANSACTIONS_BOTTOM_SHEET_CONTENT && scaffoldState.bottomSheetState.isCollapsed){
            viewModel.changeBottomSheetContext(TRANSACTIONS_BOTTOM_SHEET_CONTENT)
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        BottomSheetScaffold(
            scaffoldState = scaffoldState,
            sheetContent = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(
                            min = if (currentBottomSheetContentUIState == TRANSACTION_BOTTOM_SHEET_CONTENT || currentBottomSheetContentUIState == RECEIVE_BOTTOM_SHEET_CONTENT) 550.dp else 1500.dp,
                            max = if (currentBottomSheetContentUIState == RECEIVE_BOTTOM_SHEET_CONTENT) 470.dp else 1800.dp
                        )
                ) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(AppTheme.colors.background),
                        elevation = 0.dp

                    ) {

                        when (currentBottomSheetContentUIState) {
                            TRANSACTIONS_BOTTOM_SHEET_CONTENT -> {
                                TransactionsListView(
                                    isLoading = transactionUIState.isLoading,
                                    txt = transactionUIState.txt,
                                    justCreatedWallet = transactionUIState.txt != null && transactionUIState.txt!!.isEmpty() && walletAddressUIState != null,
                                    walletAddress = walletAddressUIState
                                ) { index ->
                                    coroutineScope.launch {
                                        viewModel.selectTxt(index)
                                        delay(100)
                                        if (scaffoldState.bottomSheetState.isExpanded) {
                                            scaffoldState.bottomSheetState.collapse()
                                            delay(200)
                                        }
                                        scaffoldState.bottomSheetState.expand()
                                    }
                                }
                            }
                            SEND_BOTTOM_SHEET_CONTENT -> {
                                coroutineScope.launch {
                                    delay(200)
                                    scaffoldState.bottomSheetState.expand()
                                }
                                Log.d("wefgfdsefghgfefg", sendAddressUIState.address)
                                SendTonView(
                                    sendAddr = sendAddressUIState.address,
                                    walletDetail = walletUIState.walletDetail!!,
                                    getTxtFee = { amount -> viewModel.getTxtFee(amount) },
                                    startSending = { amount, addr, msg ->
                                        viewModel.getTxtTransferFlow(
                                            amount,
                                            addr,
                                            msg
                                        )
                                    }
                                ) {isScanActive ->
                                    if(isScanActive){
                                        viewModel.changeBottomSheetContext(TRANSACTIONS_BOTTOM_SHEET_CONTENT)
                                        viewModel.switchScanActive(true)
                                    }else{
                                        coroutineScope.launch {
                                            scaffoldState.bottomSheetState.collapse()
                                            viewModel.changeBottomSheetContext(
                                                TRANSACTIONS_BOTTOM_SHEET_CONTENT
                                            )
                                        }
                                    }
                                }
                                viewModel.switchScanActive(false)
                            }
                            TRANSACTION_BOTTOM_SHEET_CONTENT -> {
                                TransactionDetailView(transactionDetail = transactionUIState.txt!![transactionUIState.selectedTxt!!]){addr ->
                                    viewModel.onSendAddrChange(addr)
                                }
                            }

                            RECEIVE_BOTTOM_SHEET_CONTENT -> {
                                ReceiveTonView(address = walletUIState.walletDetail!!.address)
                            }

                        }
                    }
                }
            },
            drawerGesturesEnabled = true,
            sheetPeekHeight = 400.dp,
            sheetShape = RoundedCornerShape(topEnd = 12.dp, topStart = 12.dp)
        ) {
            SwipeRefresh(
                state = rememberSwipeRefreshState(isRefreshing = walletUIState.isLoading || transactionUIState.isLoading),
                onRefresh = {
                    coroutineScope.launch {
                        scaffoldState.bottomSheetState.collapse()
                    }
                    viewModel.changeBottomSheetContext(TRANSACTIONS_BOTTOM_SHEET_CONTENT)
                    viewModel.loadMainWallet()
                }) {
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
                        if (walletUIState.isLoading) {
                            TonCrystalLoadingSpinner(
                                modifier = Modifier.size(60.dp)
                            )
                        }
                        if (walletUIState.walletDetail != null) {
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
                                            color = AppTheme.colors.background,
                                            modifier = Modifier.combinedClickable(
                                                onClick = { },
                                                onLongClick = {
                                                    context.copyToClipboard(walletUIState.walletDetail!!.address)
                                                }
                                            )
                                        )
                                        Text(
                                            text = walletUIState.walletDetail!!.balance.toRoundAmount()
                                                .toString(),
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
                                if (walletUIState.walletDetail != null) {
                                    viewModel.changeBottomSheetContext(RECEIVE_BOTTOM_SHEET_CONTENT)
                                    coroutineScope.launch {
                                        delay(200)
                                        scaffoldState.bottomSheetState.expand()
                                    }
                                }
                            }
                            PrimaryButtonApp(
                                text = SEND_BTN_TITLE,
                                icon = R.drawable.send,
                                modifier = Modifier.weight(1f)
                            ) {
                                if (walletUIState.walletDetail != null) {
                                    viewModel.changeBottomSheetContext(SEND_BOTTOM_SHEET_CONTENT)
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
                                        viewModel.switchScanActive(true)
                                    }
                            )
                            Icon(
                                painter = painterResource(id = R.drawable.setting),
                                contentDescription = "",
                                tint = AppTheme.colors.background,
                                modifier = Modifier
                                    .size(24.dp)
                                    .clickable {
                                        navController.navigate(Screen.SettingsScreen.route)
                                    }
                            )

                        }
                    }
                    if (currentBottomSheetContentUIState == TRANSACTION_BOTTOM_SHEET_CONTENT || currentBottomSheetContentUIState == RECEIVE_BOTTOM_SHEET_CONTENT) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.Black.copy(alpha = 0.6f))
                        )
                    }
                }
            }
        }
        if(sendAddressUIState.isScanActive) {
            QrCodeScannerView(
                onBack = {
                    viewModel.switchScanActive(false)
                },
                onScanDone = { text ->
                    viewModel.onSendAddrChange(text)
                }
            )
        }
    }
}