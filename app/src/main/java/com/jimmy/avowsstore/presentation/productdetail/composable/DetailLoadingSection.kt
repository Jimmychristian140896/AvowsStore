package com.jimmy.avowsstore.presentation.productdetail.composable

import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.jimmy.avowsstore.core.util.capitalizeWords
import com.jimmy.avowsstore.core.util.shimmerEffect
import com.jimmy.avowsstore.core.util.toFormattedCurrency
import com.jimmy.avowsstore.core.util.toSimpleReadableCount
import com.jimmy.avowsstore.presentation.productdetail.ProductDetailAction
import com.jimmy.avowsstore.presentation.productdetail.ProductDetailState
import com.jimmy.avowsstore.ui.theme.Black
import com.jimmy.avowsstore.ui.theme.Gray
import com.jimmy.avowsstore.ui.theme.Green
import com.jimmy.avowsstore.ui.theme.LightDivider
import com.jimmy.avowsstore.ui.theme.Red
import com.jimmy.avowsstore.ui.theme.Yellow


@Composable
fun DetailLoadingSection(
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {

            Box(
                //contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .shimmerEffect()
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {

                Box(
                    modifier = modifier
                        .width(100.dp)
                        .height(28.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .shimmerEffect()

                )
                Spacer(Modifier.height(16.dp))

                Box(
                    modifier = modifier
                        .fillMaxWidth(0.7f)
                        .height(24.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .shimmerEffect()

                )
                Spacer(Modifier.height(4.dp))

                Box(
                    modifier = modifier
                        .fillMaxWidth(0.3f)
                        .height(24.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .shimmerEffect()

                )

                Spacer(Modifier.height(16.dp))

                Box(
                    modifier = modifier
                        .width(70.dp)
                        .height(24.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .shimmerEffect()

                )
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

                Box(
                    modifier = modifier
                        .width(80.dp)
                        .height(24.dp)
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
                            .width(80.dp)
                            .height(24.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .shimmerEffect()

                    )
                    Spacer(Modifier.weight(1f))

                    Box(
                        modifier = modifier
                            .width(80.dp)
                            .height(24.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .shimmerEffect()

                    )
                    Spacer(Modifier.weight(1f))
                }
                Spacer(Modifier.height(16.dp))
                HorizontalDivider(
                    color = LightDivider,
                    thickness = 1.dp,
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Spacer(Modifier.height(16.dp))
                Box(
                    modifier = modifier
                        .width(80.dp)
                        .height(24.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .shimmerEffect()

                )
                Spacer(Modifier.height(16.dp))
                Box(
                    modifier = modifier
                        .fillMaxWidth(0.7f)
                        .height(24.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .shimmerEffect()

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