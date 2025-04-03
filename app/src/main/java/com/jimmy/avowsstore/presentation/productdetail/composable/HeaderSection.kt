package com.jimmy.avowsstore.presentation.productdetail.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Badge
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jimmy.avowsstore.presentation.productdetail.ProductDetailAction
import com.jimmy.avowsstore.presentation.productdetail.ProductDetailState
import com.jimmy.avowsstore.presentation.products.ProductsAction
import com.jimmy.avowsstore.presentation.products.ProductsState
import com.jimmy.avowsstore.ui.theme.Black


@Composable
fun HeaderSection(
    state: ProductDetailState,
    onAction: (ProductDetailAction) -> Unit,
    modifier: Modifier = Modifier
) {
    Row (
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        IconButton(
            onClick = {
                onAction(ProductDetailAction.NavigateBack)
            }
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Default.ArrowBack,
                contentDescription = "Back"
            )
        }
        Text(
            text = "",
            fontSize = 16.sp,
            color = Black,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        )
        Box(

        ) {
            IconButton(
                onClick = {
                    onAction(ProductDetailAction.CartClicked)
                },
            ) {
                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = "Cart"
                )
            }

            if(state.isLoadingCartCount) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(4.dp)
                        .size(12.dp)
                )
            }
            if (state.cartCount > 0) {
                Badge(
                    modifier = modifier
                        .align(Alignment.TopEnd)
                        .padding(4.dp)
                ) {
                    Text(text = if(state.cartCount > 99) "99+" else state.cartCount.toString())
                }
            }
        }
    }
}