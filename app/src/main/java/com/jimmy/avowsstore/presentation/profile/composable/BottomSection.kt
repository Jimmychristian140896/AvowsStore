package com.jimmy.avowsstore.presentation.profile.composable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jimmy.avowsstore.R
import com.jimmy.avowsstore.presentation.productdetail.ProductDetailAction
import com.jimmy.avowsstore.presentation.profile.ProfileAction
import com.jimmy.avowsstore.presentation.profile.ProfileState
import com.jimmy.avowsstore.ui.theme.Green

@Composable
fun BottomSection(
    state: ProfileState,
    onAction: (ProfileAction) -> Unit,
    modifier: Modifier = Modifier) {
    Button(
        onClick = {
            onAction(ProfileAction.Logout)
        },
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        //.padding(horizontal = 16.dp, vertical = 8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Green
        ),
        shape = RoundedCornerShape(8.dp)
    ) {

        Text(
            text = stringResource(R.string.logout),
            color = White,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )

    }
}