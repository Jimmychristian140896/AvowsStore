@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.jimmy.avowsstore.presentation.cart.composable

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.jimmy.avowsstore.R
import com.jimmy.avowsstore.core.util.shimmerEffect
import com.jimmy.avowsstore.core.util.toFormattedCurrency
import com.jimmy.avowsstore.domain.model.CartProduct
import com.jimmy.avowsstore.presentation.cart.CartAction
import com.jimmy.avowsstore.presentation.cart.CartState
import com.jimmy.avowsstore.presentation.products.ProductsAction
import com.jimmy.avowsstore.presentation.products.composable.ProductLoadingItem
import com.jimmy.avowsstore.ui.theme.Black
import com.jimmy.avowsstore.ui.theme.Gray
import com.jimmy.avowsstore.ui.theme.Green

@Composable
fun CartSection(
    state: CartState,
    onAction: (CartAction) -> Unit,
    modifier: Modifier = Modifier
) {
    val pullToRefreshState = rememberPullToRefreshState()
    PullToRefreshBox(
        state = pullToRefreshState,
        isRefreshing = state.isPullToRefresh,
        onRefresh = {
            onAction(CartAction.OnPullToRefresh)
        },
        modifier = modifier
            .fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            //verticalArrangement = Arrangement.spacedBy(16.dp)

        ) {


            state.cart?.let {
                items(state.cart.products) {
                    CartProductItem(
                        it, state, onAction
                    )
                }
            }


        }
    }
}

@Composable
fun SelectedCountSection(
    countChecked: Int,
    state: CartState,
    onAction: (CartAction) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "$countChecked selected product",
            fontSize = 12.sp,
            color = Black
        )

        Text(
            text = stringResource(R.string.delete),
            fontSize = 12.sp,
            color = Green,
            fontWeight = Bold,
            modifier = Modifier
                .clickable {
                    onAction(CartAction.OnDeleteAllSelected)
                }
        )

    }
}

@Composable
fun CartProductItem(
    product: CartProduct,
    state: CartState,
    onAction: (CartAction) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Checkbox(
            checked = product.isChecked,
            onCheckedChange = {
                onAction(CartAction.OnProductCheckedChange(product))
            }
        )
        Spacer(Modifier.width(16.dp))
        AsyncImage(
            model = product.imageUrl,
            contentDescription = product.name,
            placeholder = painterResource(R.drawable.ic_image_placeholder),
            modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(8.dp))
        )
        Spacer(Modifier.width(16.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = product.name,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = 12.sp,
                color = Black
            )
            Spacer(Modifier.height(16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = product.price.toFormattedCurrency(),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 14.sp,
                    color = Black,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                )

                Spacer(Modifier.width(16.dp))
                Row(
                    modifier = Modifier
                        .wrapContentWidth()
                        .clip(RoundedCornerShape(50))
                        .border(1.dp, Gray, RoundedCornerShape(50))
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (product.quantity > 1) {
                        Text(
                            text = "-",
                            fontSize = 18.sp,
                            color = Black,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .clip(RoundedCornerShape(4.dp))
                                .width(16.dp)
                                .clickable {
                                    onAction(CartAction.OnProductQuantityMinus(product))
                                }
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = stringResource(R.string.delete),
                            tint = Black,
                            modifier = Modifier
                                .size(16.dp)
                                .clickable {
                                    onAction(CartAction.OnProductDelete(product))
                                }
                        )
                    }
                    Spacer(Modifier.width(8.dp))
                    Text(
                        text = product.quantity.toString(),
                        fontSize = 12.sp,
                        color = Black,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .width(48.dp)
                    )
                    Spacer(Modifier.width(8.dp))
                    Text(
                        text = "+",
                        fontSize = 18.sp,
                        color = Black,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .width(16.dp)
                            .clickable {
                                onAction(CartAction.OnProductQuantityPlus(product))
                            }
                    )
                }

            }

        }
    }
}



