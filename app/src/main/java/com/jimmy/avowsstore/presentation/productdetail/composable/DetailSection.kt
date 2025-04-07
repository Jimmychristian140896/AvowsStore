package com.jimmy.avowsstore.presentation.productdetail.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.jimmy.avowsstore.R
import com.jimmy.avowsstore.core.util.capitalizeWords
import com.jimmy.avowsstore.core.util.toFormattedCurrency
import com.jimmy.avowsstore.core.util.toSimpleReadableCount
import com.jimmy.avowsstore.presentation.productdetail.ProductDetailAction
import com.jimmy.avowsstore.presentation.productdetail.ProductDetailState
import com.jimmy.avowsstore.ui.theme.Black
import com.jimmy.avowsstore.ui.theme.ErrorRed
import com.jimmy.avowsstore.ui.theme.Gray
import com.jimmy.avowsstore.ui.theme.Green
import com.jimmy.avowsstore.ui.theme.LightDivider
import com.jimmy.avowsstore.ui.theme.Red
import com.jimmy.avowsstore.ui.theme.Yellow

@Composable
fun DetailSection(
    state: ProductDetailState,
    onAction: (ProductDetailAction) -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    val product = state.product

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        product?.let {
            AsyncImage(
                model = product.imageUrl,
                contentDescription = product.name,
                placeholder = painterResource(R.drawable.ic_image_placeholder),
                //contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
            )
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Text(
                    text = product.price.toFormattedCurrency(),
                    fontSize = 24.sp,
                    color = Red,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Spacer(Modifier.height(16.dp))
                Text(
                    text = product.name,
                    fontSize = 14.sp,
                    color = Black,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    lineHeight = 16.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Spacer(Modifier.height(16.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = stringResource(R.string.rating),
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
            }
            HorizontalDivider(
                color = LightDivider,
                thickness = 16.dp,
                modifier = Modifier
                    .fillMaxWidth()
            )
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Text(
                    text = stringResource(R.string.product_detail),
                    fontSize = 16.sp,
                    color = Black,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Spacer(Modifier.height(16.dp))


                Row(
                    modifier = Modifier,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(R.string.category),
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth(),
                        color = Black,
                        fontWeight = FontWeight.Normal,
                        fontSize = 12.sp
                    )
                    Text(
                        text = product.category.capitalizeWords(),
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth(),
                        color = Green,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp
                    )
                }
                Spacer(Modifier.height(16.dp))
                HorizontalDivider(
                    color = LightDivider,
                    thickness = 1.dp,
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Spacer(Modifier.height(16.dp))
                Text(
                    text = stringResource(R.string.product_description),
                    fontSize = 16.sp,
                    color = Black,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Spacer(Modifier.height(16.dp))
                Text(
                    text = product.description,
                    modifier = Modifier
                        .fillMaxWidth(),
                    color = Black,
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp
                )
            }

            HorizontalDivider(
                color = LightDivider,
                thickness = 16.dp,
                modifier = Modifier
                    .fillMaxWidth()
            )


        }
    }
}