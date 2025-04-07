package com.jimmy.avowsstore.presentation.profile.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.jimmy.avowsstore.core.util.shimmerEffect
import com.jimmy.avowsstore.ui.theme.LightDivider


@Composable
fun LoadingSection(
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
            Spacer(Modifier.weight(1f).fillMaxWidth())
            Box(
                modifier = modifier
                    .width(100.dp)
                    .height(28.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .shimmerEffect()

            )
        }

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
            Spacer(Modifier.weight(1f).fillMaxWidth())
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
            Spacer(Modifier.weight(1f).fillMaxWidth())
            Box(
                modifier = modifier
                    .width(100.dp)
                    .height(28.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .shimmerEffect()

            )
        }

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
            Spacer(Modifier.weight(1f).fillMaxWidth())
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