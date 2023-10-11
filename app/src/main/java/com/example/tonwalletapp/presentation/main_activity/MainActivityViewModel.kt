package com.example.tonwalletapp.presentation.main_activity

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.tonwalletapp.domain.usecase.user_usecase.UseInitializeTonLiteClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val useInitializeTonLiteClient: UseInitializeTonLiteClient
):ViewModel() {

    private val _liteClientUIState = MutableStateFlow(LiteClientUIState())
    val liteClientUIState:StateFlow<LiteClientUIState> = _liteClientUIState

    fun initClient(){
        CoroutineScope(Dispatchers.IO).launch {
            _liteClientUIState.value = LiteClientUIState(isLoading = true)
            val res = useInitializeTonLiteClient.execute()
            if(res.data != null) _liteClientUIState.value = LiteClientUIState(isSuccessful = true)
            else Log.d("sdfsdfsdfsdfsdf",res.message!!)
        }
    }


}