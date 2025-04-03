package com.jimmy.avowsstore.presentation.products.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.jimmy.avowsstore.core.util.shimmerEffect
import com.jimmy.avowsstore.core.util.toFormattedCurrency
import com.jimmy.avowsstore.core.util.toSimpleReadableCount
import com.jimmy.avowsstore.domain.model.Product
import com.jimmy.avowsstore.presentation.products.ProductsAction
import com.jimmy.avowsstore.presentation.products.ProductsState
import com.jimmy.avowsstore.ui.theme.Black
import com.jimmy.avowsstore.ui.theme.Gray
import com.jimmy.avowsstore.ui.theme.TextColor
import com.jimmy.avowsstore.ui.theme.Yellow

@Composable
fun ProductsSection(
    state: ProductsState,
    onAction: (ProductsAction) -> Unit,
    modifier: Modifier = Modifier) {
    val lazyState = rememberLazyGridState()
    LazyVerticalGrid(
        modifier = modifier
            .fillMaxSize(),
        state = lazyState,
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        if(state.isLoadingProducts) {
            items(6) {
                ProductLoadingItem()
            }
        } else {
            items(state.products) {
                ProductItem(
                    product = it,
                    onClick = {
                        onAction(ProductsAction.ProductClicked(it))
                    }
                )

            }
        }
    }
}

@Composable
fun ProductItem(
    product: Product,
    onClick: () -> Unit,
    modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .clickable {
                onClick()
            }
    ) {
        AsyncImage(
            model = product.imageUrl,
            contentDescription = product.name,
            //contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .aspectRatio(1f)
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = product.name,
            fontSize = 12.sp,
            color = Black,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            lineHeight = 14.sp,
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = product.price.toFormattedCurrency(),
            fontSize = 12.sp,
            color = Black,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = "Rating",
                tint = Yellow,
                modifier = Modifier.size(16.dp)
            )
            Spacer(Modifier.width(4.dp))

            Text(
                text = "${product.rating} (${product.ratingCount.toSimpleReadableCount()})",
                modifier = Modifier
                    .fillMaxWidth(),
                color = Gray,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp
            )
        }
        Spacer(Modifier.width(8.dp))
    }

}

@Composable
fun ProductLoadingItem(
    modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .aspectRatio(1f)
                .shimmerEffect()
        )
        Spacer(Modifier.height(8.dp))
        Box(
            modifier = modifier
                .fillMaxWidth(0.7f)
                .height(24.dp)
                .clip(RoundedCornerShape(4.dp))
                .shimmerEffect()

        )
        Spacer(Modifier.height(2.dp))
        Box(
            modifier = modifier
                .fillMaxWidth(0.3f)
                .height(24.dp)
                .clip(RoundedCornerShape(4.dp))
                .shimmerEffect()

        )
        Spacer(Modifier.height(8.dp))
        Box(
            modifier = modifier
                .width(80.dp)
                .height(24.dp)
                .clip(RoundedCornerShape(4.dp))
                .shimmerEffect()

        )
        Spacer(Modifier.height(8.dp))
        Box(
            modifier = modifier
                .width(120.dp)
                .height(24.dp)
                .clip(RoundedCornerShape(4.dp))
                .shimmerEffect()

        )
        Spacer(Modifier.width(8.dp))
    }

}