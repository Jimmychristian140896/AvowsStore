package com.jimmy.avowsstore.presentation.summary.composable

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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.jimmy.avowsstore.core.util.shimmerEffect
import com.jimmy.avowsstore.core.util.toFormattedCurrency
import com.jimmy.avowsstore.domain.model.TransactionProduct
import com.jimmy.avowsstore.presentation.summary.SummaryAction
import com.jimmy.avowsstore.presentation.summary.SummaryState
import com.jimmy.avowsstore.ui.theme.Black
import com.jimmy.avowsstore.ui.theme.Gray
import com.jimmy.avowsstore.ui.theme.LightDivider


@Composable
fun SummaryLoadingSection(
    modifier: Modifier = Modifier
) {

    LazyColumn(
        modifier = modifier
            .fillMaxSize(),
        //verticalArrangement = Arrangement.spacedBy(16.dp)

    ) {


        item {
            TransactionLoadingSection()
        }
        items(6) {
            TransactionProductLoadingItem(
            )
        }
        item {
            Spacer(Modifier.height(16.dp))
            HorizontalDivider(
                thickness = 1.dp,
                color = LightDivider
            )
            SummaryProductLoadingSection(
            )
        }
    }


}


@Composable
fun TransactionProductLoadingItem(
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
        Box(
            modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(8.dp))
                .shimmerEffect()
        )
        Spacer(Modifier.width(16.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Box(
                modifier = modifier
                    .fillMaxWidth(0.7f)
                    .height(24.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .shimmerEffect()

            )
            Spacer(Modifier.height(16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Box(
                    modifier = modifier
                        .width(80.dp)
                        .height(24.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .shimmerEffect()

                )

            }
        }
    }
}

@Composable
fun SummaryProductLoadingSection(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .padding(16.dp)
    ) {
        Box(
            modifier = modifier
                .width(80.dp)
                .height(28.dp)
                .clip(RoundedCornerShape(4.dp))
                .shimmerEffect()

        )
        Spacer(Modifier.height(16.dp))


        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = modifier
                    .width(70.dp)
                    .height(24.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .shimmerEffect()

            )
            Spacer(
                Modifier
                    .weight(1f)
                    .fillMaxWidth())
            Box(
                modifier = modifier
                    .width(100.dp)
                    .height(28.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .shimmerEffect()

            )
        }

    }
}

@Composable
fun TransactionLoadingSection(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .padding(16.dp)
    ) {
        Box(
            modifier = modifier
                .width(80.dp)
                .height(28.dp)
                .clip(RoundedCornerShape(4.dp))
                .shimmerEffect()

        )

        Spacer(Modifier.height(16.dp))
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = modifier
                    .width(70.dp)
                    .height(24.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .shimmerEffect()

            )
            Spacer(
                Modifier
                    .weight(1f)
                    .fillMaxWidth())
            Box(
                modifier = modifier
                    .width(100.dp)
                    .height(28.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .shimmerEffect()

            )
        }
        Spacer(Modifier.height(16.dp))
        HorizontalDivider(
            thickness = 1.dp,
            color = LightDivider
        )

        Spacer(Modifier.height(16.dp))

        Box(
            modifier = modifier
                .width(80.dp)
                .height(28.dp)
                .clip(RoundedCornerShape(4.dp))
                .shimmerEffect()

        )

    }
}