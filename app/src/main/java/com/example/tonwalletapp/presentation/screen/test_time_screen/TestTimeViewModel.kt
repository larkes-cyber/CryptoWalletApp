package com.example.tonwalletapp.presentation.screen.test_time_screen

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class TestTimeViewModel():ViewModel() {

    private val _testTimeUIState = MutableStateFlow(TestTimeUIState())
    val testTimeUIState:StateFlow<TestTimeUIState> = _testTimeUIState

    private val _hasBeenDoneUIState = MutableStateFlow(false)
    val hasBeenDoneUIState:StateFlow<Boolean> = _hasBeenDoneUIState

    init {
        _testTimeUIState.value = TestTimeUIState(
            words = listOf(
                Pair(5, "network"),
                Pair(15, "banana"),
                Pair(18, "coffee")
            )
        )
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