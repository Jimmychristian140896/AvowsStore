package com.jimmy.avowsstore.presentation.summary.composable

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jimmy.avowsstore.R
import com.jimmy.avowsstore.presentation.summary.SummaryAction
import com.jimmy.avowsstore.presentation.summary.SummaryState
import com.jimmy.avowsstore.ui.theme.Green


@Composable
fun BottomSection(
    state: SummaryState,
    onAction: (SummaryAction) -> Unit,
    modifier: Modifier = Modifier
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Button(
            onClick = {
                onAction(SummaryAction.Pay)
            },
            modifier = Modifier
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Green
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                text = stringResource(R.string.pay),
                color = White,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }

}