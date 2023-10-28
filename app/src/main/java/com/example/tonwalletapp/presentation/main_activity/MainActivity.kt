package com.example.tonwalletapp.presentation.main_activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.tonwalletapp.presentation.navigation.Navigation
import com.example.tonwalletapp.presentation.view.TonCrystalLoadingSpinner
import com.example.tonwalletapp.presentation.view.TransactionsLoadingSpinner
import com.example.tonwalletapp.ui.theme.AppTheme
import com.example.tonwalletapp.ui.theme.TonWalletAppTheme
import com.example.tonwalletapp.until.Constants.WAIT_WHILE_TON_CONFIG_IS_LOADING
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewmodel: MainActivityViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val liteClientUIState by viewmodel.liteClientUIState.collectAsState()

            LaunchedEffect(Unit){
                viewmodel.initClient()
            }

            val navController = rememberNavController()
            TonWalletAppTheme {
                if(liteClientUIState.isSuccessful) Navigation(navController = navController)
                else LoadingComponent()
            }
        }
    }
}

@Composable
fun LoadingComponent(){
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        TonCrystalLoadingSpinner(
            modifier = Modifier.size(120.dp)
        )
    }
}
