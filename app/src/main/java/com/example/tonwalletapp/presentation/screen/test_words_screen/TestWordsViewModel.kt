package com.example.tonwalletapp.presentation.screen.test_words_screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.tonwalletapp.domain.usecase.UseGetLocalSecretWords
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TestWordsViewModel @Inject constructor(
    private val useGetLocalSecretWords: UseGetLocalSecretWords
):ViewModel() {

    private var checkWords:List<Pair<Int, String>>? = null
    var numbers:List<Int>? = null

    val wordState1 = mutableStateOf("")
    val wordState2 = mutableStateOf("")
    val wordState3 = mutableStateOf("")

    private val _statusState = mutableStateOf(TestWordsStatusState())
    val statusState = _statusState


    init {
        val words = useGetLocalSecretWords.execute()
        val s: MutableSet<Int> = mutableSetOf()
        while (s.size < 4) { s.add((0..19).random()) }
        val n = s.toList().sorted()
        checkWords = n.map { Pair(it+1, words[it]) }
        numbers = n.map { it+1 }
    }

    fun checkWords(){
        if(wordState1.value.filter { !it.isWhitespace() } != checkWords!![0].second
            || wordState2.value.filter { !it.isWhitespace() } != checkWords!![1].second
            || wordState3.value.filter { !it.isWhitespace() } != checkWords!![2].second
        ) _statusState.value = TestWordsStatusState(inCorrect = true)
        else _statusState.value = TestWordsStatusState(succeed = true)

    }

    private fun resetWords(){
        wordState1.value = ""
        wordState2.value = ""
        wordState3.value = ""
    }

    fun tryAgain(){
        resetWords()
        _statusState.value = TestWordsStatusState()
    }

}