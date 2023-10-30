package com.example.tonwalletapp.presentation.screen.main_wallet_screen.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.tonwalletapp.domain.model.TransactionDetail
import com.example.tonwalletapp.presentation.view.TransactionItemList
import com.example.tonwalletapp.presentation.view.TransactionsLoadingSpinner
import com.example.tonwalletapp.presentation.view.WalletJustCreatedSplash
import com.example.tonwalletapp.ui.theme.AppTheme

@Composable
fun TransactionsListView(
    isLoading:Boolean,
    txt:List<TransactionDetail>?,
    justCreatedWallet:Boolean,
    walletAddress:String?
) {

        if(isLoading){
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
    LazyColumn(
            modifier = Modifier
                .padding(top = 5.dp)
        ) {
            itemsIndexed(txt ?: listOf()){ index, item ->
                Spacer(modifier = Modifier.height(14.dp))
                Box(modifier = Modifier.padding(horizontal = 16.dp)) {
                    TransactionItemList(transactionDetail = item)
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
        if(justCreatedWallet){
            WalletJustCreatedSplash(address = walletAddress!!)
        }
    }
