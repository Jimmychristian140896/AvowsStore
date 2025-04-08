package com.jimmy.avowsstore.presentation.cart.composable

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jimmy.avowsstore.R
import com.jimmy.avowsstore.core.util.toFormattedCurrency
import com.jimmy.avowsstore.presentation.cart.CartAction
import com.jimmy.avowsstore.presentation.cart.CartState
import com.jimmy.avowsstore.presentation.productdetail.ProductDetailAction
import com.jimmy.avowsstore.presentation.productdetail.ProductDetailState
import com.jimmy.avowsstore.ui.theme.Black
import com.jimmy.avowsstore.ui.theme.Green


@Composable
fun BottomSection(
    state: CartState,
    onAction: (CartAction) -> Unit,
    modifier: Modifier = Modifier
) {

    val allChecked = state.cart?.products?.all { it.isChecked } ?: false
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Checkbox(
            checked = allChecked,
            onCheckedChange = {
                if(!allChecked) {
                    onAction(CartAction.OnCheckAll)
                } else {
                    onAction(CartAction.OnUncheckAll)
                }
            }
        )

        Text(
            text = if(!allChecked) "All" else "",
            color = Black,
            fontSize = 12.sp,
            modifier = Modifier
        )
        Text(
            text = state.cart?.products?.filter { it.isChecked }?.sumOf { it.price*it.quantity }?.toFormattedCurrency() ?: "-",
            color = Black,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.End,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        )
        Spacer(Modifier.width(16.dp))

        Button(
            onClick = {
                if(!state.isLoadingCheckout) {
                    onAction(CartAction.OnCheckout)
                }
            },
            modifier = Modifier
                .wrapContentSize()
                .widthIn(min = 160.dp),
            //.padding(horizontal = 16.dp, vertical = 8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Green
            ),
            shape = RoundedCornerShape(8.dp)
        ) {

            if(state.isLoadingCheckout) {
                CircularProgressIndicator(
                    color = White,
                    modifier = Modifier.size(24.dp)
                )
            } else {
                Text(
                    text = stringResource(R.string.checkout)
                            +if(state.cart?.products?.filter { it.isChecked }?.isNotEmpty() == true) " (${state.cart.products.filter { it.isChecked }.size})" else "",
                    color = White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }

        }
    }

}