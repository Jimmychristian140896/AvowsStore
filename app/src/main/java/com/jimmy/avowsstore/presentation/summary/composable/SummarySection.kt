package com.jimmy.avowsstore.presentation.summary.composable

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.jimmy.avowsstore.R
import com.jimmy.avowsstore.core.util.toFormattedCurrency
import com.jimmy.avowsstore.core.util.toFormattedString
import com.jimmy.avowsstore.core.util.toFormattedTime
import com.jimmy.avowsstore.core.util.toLocalDateTime
import com.jimmy.avowsstore.domain.model.TransactionProduct
import com.jimmy.avowsstore.presentation.cart.CartAction
import com.jimmy.avowsstore.presentation.cart.CartState
import com.jimmy.avowsstore.presentation.summary.SummaryAction
import com.jimmy.avowsstore.presentation.summary.SummaryState
import com.jimmy.avowsstore.ui.theme.Black
import com.jimmy.avowsstore.ui.theme.Gray
import com.jimmy.avowsstore.ui.theme.LightDivider


@Composable
fun SummarySection(
    state: SummaryState,
    onAction: (SummaryAction) -> Unit,
    modifier: Modifier = Modifier
) {

    LazyColumn(
        modifier = modifier
            .fillMaxSize(),
        //verticalArrangement = Arrangement.spacedBy(16.dp)

    ) {

        state.transaction?.let {
            item {
                TransactionSection(
                    state = state,
                    onAction = onAction
                )
            }
            items(state.transaction.products) {
                TransactionProductItem(
                    it, state, onAction
                )
            }
            item {
                Spacer(Modifier.height(16.dp))
                HorizontalDivider(
                    thickness = 1.dp,
                    color = LightDivider
                )
                SummaryProductSection(
                    state = state,
                    onAction = onAction
                )
            }
        }

    }
}

@Composable
fun TransactionProductItem(
    product: TransactionProduct,
    state: SummaryState,
    onAction: (SummaryAction) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        /*Checkbox(
            checked = cartProduct.isChecked,
            onCheckedChange = {
                onAction(CartAction.OnProductCheckedChange(cartProduct))
            }
        )
        Spacer(Modifier.width(16.dp))*/
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
            Spacer(Modifier.height(12.dp))
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
                )
                Text(
                    text = " x ${product.quantity}",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 14.sp,
                    color = Black,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier
                )



            }

        }
    }
}

@Composable
fun SummaryProductSection(
    state: SummaryState,
    onAction: (SummaryAction) -> Unit,
    modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(R.string.summary),
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
                text = stringResource(R.string.total_value, state.transaction?.products?.size?.toString() ?: "0"),
                modifier = Modifier
                    .wrapContentSize(),
                color = Gray,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp
            )
            Spacer(
                Modifier
                    .weight(1f)
                    .fillMaxWidth())
            Text(
                text = state.transaction?.products?.sumOf { it.price*it.quantity }?.toFormattedCurrency() ?: "",
                modifier = Modifier
                    .wrapContentSize(),
                color = Black,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }

    }
}



@Composable
fun TransactionSection(
    state: SummaryState,
    onAction: (SummaryAction) -> Unit,
    modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(R.string.transaction),
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
                text = stringResource(R.string.date),
                modifier = Modifier
                    .wrapContentSize(),
                color = Gray,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp
            )
            Spacer(
                Modifier
                    .weight(1f)
                    .fillMaxWidth())
            Text(
                text = state.transaction?.date?.toLocalDateTime()?.toFormattedString() ?: "",
                modifier = Modifier
                    .wrapContentSize(),
                color = Black,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }
        Spacer(Modifier.height(16.dp))
        HorizontalDivider(
            thickness = 1.dp,
            color = LightDivider
        )

        Spacer(Modifier.height(16.dp))
        Text(
            text = stringResource(R.string.products),
            fontSize = 16.sp,
            color = Black,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
        )


    }
}