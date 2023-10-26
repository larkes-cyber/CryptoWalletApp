package com.example.tonwalletapp.presentation.screen.test_time_screen

data class TestTimeUIState(
    val words:List<Pair<Int, String>> = listOf(),
    val firstWordTextFiled:String = "",
    val secondWordTextFiled:String = "",
    val thirdWordTextFiled:String = "",
    val activeIncorrectWordDialog:Boolean = false,
    val isLoading:Boolean = false
)