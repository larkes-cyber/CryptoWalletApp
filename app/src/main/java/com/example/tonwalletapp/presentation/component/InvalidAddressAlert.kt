package com.example.tonwalletapp.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tonwalletapp.R
import com.example.tonwalletapp.ui.theme.AppTheme
import com.example.tonwalletapp.until.Constants.ADDRESS_DOESNT_BELONG_TON_TITLE
import com.example.tonwalletapp.until.Constants.INVALID_ADDRESS_TITLE

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun InvalidAddressAlert(
    onClose:() -> Unit = {}
) {

    Card(
        backgroundColor = AppTheme.colors.alertBackground,
        elevation = 0.dp,
        shape = RoundedCornerShape(6.dp),
        modifier = Modifier.fillMaxWidth(),
        onClick = {
            onClose()
        }
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(15.dp),
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.subtract_icon),
                contentDescription = "",
                modifier = Modifier.size(24.dp),
                tint = AppTheme.colors.background
            )
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(
                    text = INVALID_ADDRESS_TITLE,
                    fontSize = 14.sp,
                    color = AppTheme.colors.background,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = ADDRESS_DOESNT_BELONG_TON_TITLE,
                    fontSize = 14.sp,
                    color = AppTheme.colors.background,
                    fontWeight = FontWeight.Normal
                )
            }

            IconButton(onClick = {
                onClose()
            }) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "",
                    modifier = Modifier.size(14.dp),
                    tint = AppTheme.colors.background
                )
            }
        }
    }

}