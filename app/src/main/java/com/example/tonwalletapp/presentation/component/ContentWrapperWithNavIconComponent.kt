package com.example.tonwalletapp.presentation.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ContentWrapperWithNavIconComponent(
    content:@Composable () -> Unit,
    title:@Composable () -> Unit = {},
    callback:() -> Unit,
    elevation: Int = 0
) {

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = {
                        callback()
                    }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            tint = Color.Black,
                            contentDescription = "",
                            modifier = Modifier.size(25.dp)
                        )
                    }
                },
                title = {title()},
                backgroundColor = Color.White,
                elevation = elevation.dp
            )
        }
    ) {

        content()

    }

}