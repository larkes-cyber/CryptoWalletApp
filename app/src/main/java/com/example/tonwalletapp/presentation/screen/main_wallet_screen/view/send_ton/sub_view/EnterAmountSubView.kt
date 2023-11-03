package com.example.tonwalletapp.presentation.screen.main_wallet_screen.view.send_ton.sub_view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tonwalletapp.R
import com.example.tonwalletapp.domain.mapper.toFormattedAddress
import com.example.tonwalletapp.presentation.component.DigitalKeyboard
import com.example.tonwalletapp.presentation.component.PrimaryButtonApp
import com.example.tonwalletapp.presentation.screen.main_wallet_screen.view.send_ton.component.AmountTextField
import com.example.tonwalletapp.ui.theme.AppTheme
import com.example.tonwalletapp.until.Constants.ContinueBtnTitle
import com.example.tonwalletapp.until.Constants.INCORRECT_AMOUNT
import com.example.tonwalletapp.until.Constants.INSUFFICIENT_FUNDS_ERROR
import com.example.tonwalletapp.until.Constants.MIN_AMOUNT_ERROR

@Composable
fun EnterAmountSubView(
    receiverAddress:String,
    maxAmount:Float,
    onDone:(Float) -> Unit
) {

    val maxAmountSelectedUIState = remember {
        mutableStateOf(false)
    }

    val amountException = remember {
        mutableStateOf("")
    }

    val enteredAmount = remember {
        mutableStateOf("")
    }

    fun enterAmount(syb:String = "", onDelete:Boolean = false){
        if(maxAmountSelectedUIState.value) return
        if(syb.isNotEmpty()){
            val newValue = enteredAmount.value + syb
            if(newValue.length > 6) return
            if(newValue[0] == '0' && newValue.length == 2 && newValue[1] != '.'){
                enteredAmount.value = "${newValue[0]}.${newValue[1]}"
                return
            }
            if(newValue.filter { it == '.' }.length <= 1 && (newValue != ".")){
                enteredAmount.value = newValue
            }
        }
        if(onDelete){
            enteredAmount.value = enteredAmount.value.dropLast(1)
        }
    }

    fun getFloatAmount(amount:String):Float? {
        return try {
            if (amount[amount.length - 1] == '.') amount.dropLast(1)
            amount.toFloat()
        }catch (e:Exception){
            null
        }

    }

    LaunchedEffect(enteredAmount.value){
        if(getFloatAmount(enteredAmount.value.ifEmpty { "0" })!! > maxAmount){
           amountException.value = INSUFFICIENT_FUNDS_ERROR
        }else{
            amountException.value = ""
        }
    }

    Column(
        modifier = Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
            Text(
                text = "Send to:",
                fontSize = 15.sp,
                color = AppTheme.colors.strokeFormColor
            )
            Text(
                text = receiverAddress.toFormattedAddress(),
                fontSize = 15.sp,
                color = AppTheme.colors.primaryTitle
            )
        }

        AmountTextField(
            amount = enteredAmount.value,
            exception = amountException.value
        )


        Column() {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    Text(
                        text = "Send all",
                        fontSize = 15.sp,
                        color = AppTheme.colors.primaryTitle
                    )
                    Image(
                        painter = painterResource(id = R.drawable.ton_crystal_frame),
                        contentDescription = "",
                        modifier = Modifier.size(18.dp),
                        contentScale = ContentScale.Crop
                    )
                    Text(
                        text = maxAmount.toString(),
                        fontSize = 15.sp,
                        color = AppTheme.colors.primaryTitle
                    )
                }
                Switch(
                    checked = maxAmountSelectedUIState.value,
                    onCheckedChange = {
                        maxAmountSelectedUIState.value = it
                        enteredAmount.value = if(maxAmountSelectedUIState.value) maxAmount.toString() else "0"
                    },
                    colors = SwitchDefaults.colors(
                        disabledCheckedThumbColor = AppTheme.colors.strokeFormColor,
                        disabledCheckedTrackColor = AppTheme.colors.background,
                        checkedThumbColor = AppTheme.colors.background ,
                        checkedTrackColor = AppTheme.colors.primaryBackground
                    )
                )

            }
            PrimaryButtonApp(text = ContinueBtnTitle, modifier = Modifier.fillMaxWidth()) {
                if(getFloatAmount(enteredAmount.value) == null) {
                    amountException.value = INCORRECT_AMOUNT
                    return@PrimaryButtonApp
                }
                if(getFloatAmount(enteredAmount.value.ifEmpty { "0" })!! < 0.009){
                    amountException.value = MIN_AMOUNT_ERROR
                }
                if(amountException.value.isEmpty()) onDone(enteredAmount.value.toFloat())
            }
            Spacer(modifier = Modifier.height(16.dp))
            DigitalKeyboard(
                onDotClick = {
                    enterAmount(".")
                },
                onBtnClick = {
                    enterAmount(it.toString())
                },
                onDeleteBtnClick = {
                    enterAmount(onDelete = true)
                }
            )
        }

    }


}