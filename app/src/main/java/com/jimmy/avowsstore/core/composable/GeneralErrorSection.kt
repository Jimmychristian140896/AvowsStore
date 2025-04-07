package com.jimmy.avowsstore.core.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jimmy.avowsstore.R
import com.jimmy.avowsstore.presentation.cart.CartAction
import com.jimmy.avowsstore.presentation.cart.CartState
import com.jimmy.avowsstore.ui.theme.Black
import com.jimmy.avowsstore.ui.theme.Gray
import com.jimmy.avowsstore.ui.theme.Green




@Composable
fun GeneralErrorSection(
    onTryAgain: () -> Unit,
    message: String? = null,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Default.Error,
                contentDescription = stringResource(R.string.error),
                modifier = Modifier
                    .size(100.dp)
            )
            Spacer(Modifier.height(16.dp))
            Text(
                text = stringResource(R.string.there_is_an_error),
                fontSize = 16.sp,
                color = Black,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            )
            Spacer(Modifier.height(16.dp))
            Text(
                text = message ?: stringResource(R.string.please_try_again_later),
                fontSize = 12.sp,
                color = Gray,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            )
            Spacer(Modifier.height(42.dp))
            Button(
                onClick = {

                    onTryAgain()

                },
                modifier = Modifier
                    .fillMaxWidth(),
                //.padding(horizontal = 16.dp, vertical = 8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Green
                ),
                shape = RoundedCornerShape(8.dp)
            ) {

                Text(
                    text = stringResource(R.string.try_again),
                    color = White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )

            }
        }
    }
}