package com.example.tonwalletapp.presentation.screen.test_time_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tonwalletapp.domain.usecase.wallet_usecase.UseGetFirstWalletWords
import com.example.tonwalletapp.until.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class TestTimeViewModel @Inject constructor(
    private val useGetFirstWalletWords: UseGetFirstWalletWords
):ViewModel() {

    private val _testTimeUIState = MutableStateFlow(TestTimeUIState())
    val testTimeUIState:StateFlow<TestTimeUIState> = _testTimeUIState

    private val _hasBeenDoneUIState = MutableStateFlow(false)
    val hasBeenDoneUIState:StateFlow<Boolean> = _hasBeenDoneUIState

    init {
        useGetFirstWalletWords.invoke().onEach {res ->
            when(res){
                is Resource.Success -> {
                    val shuffledWords = res.data!!.mapIndexed { index, item ->
                        Pair(index + 1, item)
                    }.shuffled()
                    _testTimeUIState.value = TestTimeUIState(
                        words = listOf(shuffledWords[0], shuffledWords[1], shuffledWords[2]).sortedBy { it.first }
                    )
                }
                is Resource.Error -> {}
                else -> {}
            }
        }.launchIn(viewModelScope)
    }

    fun onFirstWordChange(word:String){
        _testTimeUIState.value = testTimeUIState.value.copy(firstWordTextFiled = word)
    }

    fun onSecondWordChange(word:String){
        _testTimeUIState.value = testTimeUIState.value.copy(secondWordTextFiled = word)
    }

    fun onThirdWordChange(word:String){
        _testTimeUIState.value = testTimeUIState.value.copy(thirdWordTextFiled = word)
    }

    fun dismissDialog(){
        _testTimeUIState.value = testTimeUIState.value.copy(activeIncorrectWordDialog = false)
    }

    fun onDone(){
        val correctWord = testTimeUIState.value.words
        if(
            testTimeUIState.value.firstWordTextFiled != correctWord[0].second
            || testTimeUIState.value.secondWordTextFiled != correctWord[1].second
            || testTimeUIState.value.thirdWordTextFiled != correctWord[2].second
        ){
            _testTimeUIState.value = testTimeUIState.value.copy(activeIncorrectWordDialog = true)
            return
        }
        _hasBeenDoneUIState.value = true
    }

}