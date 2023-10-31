package com.example.tonwalletapp.presentation.screen.main_wallet_screen.view.send_ton

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.tonwalletapp.domain.model.WalletDetail
import com.example.tonwalletapp.until.Constants.ENTER_ADDRESS_TRANSFER_PROGRESS
import com.example.tonwalletapp.until.Constants.ENTER_AMOUNT_TRANSFER_PROGRESS
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.ton.block.AddrStd
import org.ton.block.MsgAddressExt
import org.ton.block.MsgAddressInt
import javax.inject.Inject

@HiltViewModel
class SendTonViewController @Inject constructor():ViewModel() {

    private val _sendTonUIState = MutableStateFlow(SendTonUIState())
    val sendTonUIState:StateFlow<SendTonUIState> = _sendTonUIState

    fun onAddressChange(address:String){
        _sendTonUIState.value = sendTonUIState.value.copy(receiverAddress = address)
    }

    private fun checkAddress(){
        try {
            AddrStd(_sendTonUIState.value.receiverAddress!!)
        }catch (e:Exception){
            _sendTonUIState.value = sendTonUIState.value.copy(invalidAddressException = true)
        }
    }

    fun continueTransfer(){
        when(sendTonUIState.value.transferProgress){
            ENTER_ADDRESS_TRANSFER_PROGRESS -> {
                checkAddress()
                if(_sendTonUIState.value.invalidAddressException.not()){
                    _sendTonUIState.value = sendTonUIState.value.copy(transferProgress = ENTER_AMOUNT_TRANSFER_PROGRESS)
                }
            }
        }
    }

    fun progressBack(){
        _sendTonUIState.value = sendTonUIState.value.copy(transferProgress = sendTonUIState.value.transferProgress - 1)
    }

    fun resetTonSendViewData(){
        _sendTonUIState.value = SendTonUIState()
    }

    fun initWalletInfo(walletDetail: WalletDetail){
        _sendTonUIState.value = sendTonUIState.value.copy(totalTonAmount = walletDetail.balance)
    }

    fun resetAddressExp(){
        _sendTonUIState.value = sendTonUIState.value.copy(invalidAddressException = false)
    }


}