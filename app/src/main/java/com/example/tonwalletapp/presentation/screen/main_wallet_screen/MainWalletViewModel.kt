package com.example.tonwalletapp.presentation.screen.main_wallet_screen

import androidx.lifecycle.ViewModel
import com.example.tonwalletapp.domain.mapper.toFormattedAddress
import com.example.tonwalletapp.domain.mapper.toRoundAmount
import com.example.tonwalletapp.domain.usecase.wallet_usecase.UseGetTransactionFee
import com.example.tonwalletapp.domain.usecase.wallet_usecase.UseGetTransactionsByAddress
import com.example.tonwalletapp.domain.usecase.wallet_usecase.UseGetWalletInfo
import com.example.tonwalletapp.domain.usecase.wallet_usecase.UseGetWallets
import com.example.tonwalletapp.domain.usecase.wallet_usecase.UseMakeTransaction
import com.example.tonwalletapp.presentation.screen.main_wallet_screen.view.SendAddressUIState
import com.example.tonwalletapp.until.Constants.FEE
import com.example.tonwalletapp.until.Constants.SEND_BOTTOM_SHEET_CONTENT
import com.example.tonwalletapp.until.Constants.TRANSACTIONS_BOTTOM_SHEET_CONTENT
import com.example.tonwalletapp.until.Constants.TRANSACTION_BOTTOM_SHEET_CONTENT
import com.example.tonwalletapp.until.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class MainWalletViewModel @Inject constructor(
    private val useGetWallets: UseGetWallets,
    private val useGetWalletInfo: UseGetWalletInfo,
    private val useGetTransactionsByAddress: UseGetTransactionsByAddress,
    private val useGetTransactionFee: UseGetTransactionFee,
    private val useMakeTransaction: UseMakeTransaction
):ViewModel() {

    private val _walletUIState = MutableStateFlow(WalletUIState())
    val walletUIState:StateFlow<WalletUIState> = _walletUIState

    private val _transactionsUIState = MutableStateFlow(TransactionsUIState())
    val transactionsUIState:StateFlow<TransactionsUIState> = _transactionsUIState

    private val _walletAddressUIState = MutableStateFlow<String?>(null)
    val walletAddressUIState:StateFlow<String?> = _walletAddressUIState

    private val _currentBottomSheetContentUIState = MutableStateFlow(TRANSACTIONS_BOTTOM_SHEET_CONTENT)
    val currentBottomSheetContentUIState:StateFlow<Int> = _currentBottomSheetContentUIState

    private val _sendAddressUIState = MutableStateFlow(SendAddressUIState())
    val sendAddressUIState:StateFlow<SendAddressUIState> = _sendAddressUIState

    init {
         loadMainWallet()
    }

    fun loadMainWallet(){
        _walletUIState.value = WalletUIState(isLoading = true)
        useGetWallets.invoke().onEach {res ->
            when(res){
                is Resource.Loading -> {
                    _walletUIState.value = WalletUIState(isLoading = true)
                    _transactionsUIState.value = TransactionsUIState(isLoading = true)
                }
                is Resource.Success -> {
                    val wallets = res.data
                    _walletAddressUIState.value = wallets!![0].address
                    getWalletDetailInfo()
                    getTransactions()
                }
                is Resource.Error -> {
                    _walletUIState.value = WalletUIState(error = res.message!!)
                }
            }
        }.launchIn(CoroutineScope(Dispatchers.IO))
    }
    private fun getTransactions(){

        useGetTransactionsByAddress.invoke(walletAddressUIState.value!!).onEach {res ->
            when(res){
                is Resource.Loading -> {
                    _transactionsUIState.value = TransactionsUIState(isLoading = true)
                }
                is Resource.Success -> {
                    _transactionsUIState.value = TransactionsUIState(txt = res.data ?: listOf())
                }
                is Resource.Error -> {
                    _transactionsUIState.value = TransactionsUIState(error = res.message!!)
                }
            }
        }.launchIn(CoroutineScope(Dispatchers.IO))

    }

    fun changeBottomSheetContext(content:Int){
        _currentBottomSheetContentUIState.value = content
    }

    fun formatAddress(address:String):String{
        return address.toFormattedAddress()
    }

    private fun getWalletDetailInfo(){
        useGetWalletInfo.invoke(walletAddressUIState.value!!).onEach {res ->
            when(res){
                is Resource.Loading -> {
                    _walletUIState.value = WalletUIState(isLoading = true)
                }
                is Resource.Success -> {
                    _walletUIState.value = WalletUIState(walletDetail = res.data)
                }
                is Resource.Error -> {
                    _walletUIState.value = WalletUIState(error = res.message!!)
                }
            }
        }.launchIn(CoroutineScope(Dispatchers.IO))
    }

    fun onSendAddrChange(text:String){
        _sendAddressUIState.value = SendAddressUIState(address = text)
        _currentBottomSheetContentUIState.value = SEND_BOTTOM_SHEET_CONTENT
    }

    fun switchScanActive(bool: Boolean){
        _sendAddressUIState.value = SendAddressUIState(isScanActive = bool)
    }

    fun getTxtTransferFlow(amount: Float, receiverAddr:String, message:String?):Flow<Resource<String>>{

        val totalAmount = if(amount + FEE > walletUIState.value.walletDetail!!.balance) amount - FEE else amount

        return useMakeTransaction.invoke(amount = totalAmount.toFloat().toRoundAmount().toFloat(), receiverAddr = receiverAddr, senderAddr = walletUIState.value.walletDetail!!.address, message = message)
    }

    fun getTxtFee(amount:Float):Float{
        return useGetTransactionFee.execute(amount = amount)
    }

    fun selectTxt(index:Int){
        _transactionsUIState.value = transactionsUIState.value.copy(selectedTxt = index)
        _currentBottomSheetContentUIState.value = TRANSACTION_BOTTOM_SHEET_CONTENT
    }

}