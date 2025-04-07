package com.jimmy.avowsstore.presentation.products.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jimmy.avowsstore.R
import com.jimmy.avowsstore.presentation.products.ProductsAction
import com.jimmy.avowsstore.presentation.products.ProductsState
import com.jimmy.avowsstore.ui.theme.Black
import com.jimmy.avowsstore.ui.theme.Red
import com.jimmy.avowsstore.ui.theme.WhiteBackground

@Composable
fun HeaderSection(
    state: ProductsState,
    onAction: (ProductsAction) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(R.string.products),
            fontSize = 16.sp,
            color = Black,
            modifier = Modifier
                .padding(start = 8.dp)
                .weight(1f)
                .fillMaxWidth()
        )



        Box(

        ) {
            IconButton(
                onClick = {
                    onAction(ProductsAction.CartClicked)
                },
            ) {
                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = stringResource(R.string.cart)
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

        IconButton(
            onClick = {
                onAction(ProductsAction.ProfileClicked)
            }
        ) {
            Icon(
                imageVector = Icons.Rounded.Person,
                contentDescription = stringResource(R.string.profile)
            )
        }
    }
}