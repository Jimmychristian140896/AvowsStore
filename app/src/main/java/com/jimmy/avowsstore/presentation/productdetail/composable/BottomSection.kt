package com.jimmy.avowsstore.presentation.productdetail.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jimmy.avowsstore.presentation.productdetail.ProductDetailAction
import com.jimmy.avowsstore.presentation.productdetail.ProductDetailState
import com.jimmy.avowsstore.ui.theme.Green


@Composable
fun BottomSection(
    state: ProductDetailState,
    onAction: (ProductDetailAction) -> Unit,
    modifier: Modifier = Modifier
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Button(
            onClick = {
                if(!state.isLoadingAddToCart) {
                    onAction(ProductDetailAction.OnAddToCart)
                }
            },
            modifier = Modifier
                .fillMaxWidth(),
                //.padding(horizontal = 16.dp, vertical = 8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Green
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            if(state.isLoadingAddToCart) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(16.dp)
                )
            } else {
                Text(
                    text = "+ Keranjang",
                    color = White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }

}