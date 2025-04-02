package com.jimmy.avowsstore.presentation.profile.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jimmy.avowsstore.core.util.capitalizeWords
import com.jimmy.avowsstore.presentation.profile.ProfileAction
import com.jimmy.avowsstore.presentation.profile.ProfileState
import com.jimmy.avowsstore.ui.theme.Black
import com.jimmy.avowsstore.ui.theme.Gray
import com.jimmy.avowsstore.ui.theme.Green


@Composable
fun AddressInfoSection(
    state: ProfileState,
    onAction: (ProfileAction) -> Unit,
) {
    val user = state.user
    user?.let {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(
                text = "Address Info",
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
                    text = "City",
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    color = Gray,
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp
                )
                Text(
                    text = "${user.city.capitalizeWords()}",
                    modifier = Modifier
                        .weight(2f)
                        .fillMaxWidth(),
                    color = Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp
                )
            }
            Spacer(Modifier.height(16.dp))


            Row(
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Street",
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    color = Gray,
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp
                )
                Text(
                    text = "${user.street.capitalizeWords()}",
                    modifier = Modifier
                        .weight(2f)
                        .fillMaxWidth(),
                    color = Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp
                )
            }
            Spacer(Modifier.height(16.dp))


            Row(
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Number",
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    color = Gray,
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp
                )
                Text(
                    text = "${user.number}",
                    modifier = Modifier
                        .weight(2f)
                        .fillMaxWidth(),
                    color = Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp
                )
            }
            Spacer(Modifier.height(16.dp))


            Row(
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "ZIP Code",
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    color = Gray,
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp
                )
                Text(
                    text = "${user.zipcode}",
                    modifier = Modifier
                        .weight(2f)
                        .fillMaxWidth(),
                    color = Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp
                )
            }
        }
    }
}