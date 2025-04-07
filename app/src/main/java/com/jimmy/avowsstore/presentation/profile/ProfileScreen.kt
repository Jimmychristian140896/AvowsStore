@file:OptIn(ExperimentalMaterial3Api::class)

package com.jimmy.avowsstore.presentation.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.jimmy.avowsstore.core.composable.GeneralErrorSection
import com.jimmy.avowsstore.core.composable.ObserveAsEvents
import com.jimmy.avowsstore.core.util.capitalizeWords
import com.jimmy.avowsstore.presentation.products.ProductsScreen
import com.jimmy.avowsstore.presentation.profile.composable.AddressInfoSection
import com.jimmy.avowsstore.presentation.profile.composable.BottomSection
import com.jimmy.avowsstore.presentation.profile.composable.LoadingSection
import com.jimmy.avowsstore.presentation.profile.composable.PersonalInfoSection
import com.jimmy.avowsstore.presentation.profile.composable.ProfileInfoSection
import com.jimmy.avowsstore.presentation.summary.SummaryAction
import com.jimmy.avowsstore.presentation.summary.composable.SummaryLoadingSection
import com.jimmy.avowsstore.ui.theme.Black
import com.jimmy.avowsstore.ui.theme.Green
import com.jimmy.avowsstore.ui.theme.LightDivider
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProfileScreenBottomSheet(
    onDismiss: () -> Unit,
    onLogout: () -> Unit,
    modifier: Modifier = Modifier
) {


    val viewModel: ProfileViewModel = koinViewModel<ProfileViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()

    ObserveAsEvents(viewModel.eventChannel) { event ->
        when (event) {
            ProfileEvent.Logout -> {
                onLogout()
            }
            else -> {
            }
        }
    }

    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true, // Skip the middle

    )
    ModalBottomSheet(
        onDismissRequest = {
            onDismiss()
        },
        modifier = modifier,
        sheetState = sheetState
    ) {

        if(state.isLoading) {
            LoadingSection(
            )
        } else if(state.error != null) {
            GeneralErrorSection(
                onTryAgain = {
                    viewModel.onAction(ProfileAction.OnTryAgain)
                },
                message = state.error?.asString()
            )
        } else {
            ProfileScreen(
                state = state,
                onAction = viewModel::onAction
            )
        }
    }
}


@Preview
@Composable
private fun Preview() {
    ProfileScreen(
        state = ProfileState(),
        onAction = {}
    )

}

@Composable
fun ProfileScreen(
    state: ProfileState,
    onAction: (ProfileAction) -> Unit,
) {
    val user = state.user

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        user?.let {

            ProfileInfoSection(
                state = state,
                onAction = onAction
            )

            HorizontalDivider(
                color = LightDivider,
                thickness = 1.dp,
                modifier = Modifier
                    .fillMaxWidth()
            )
            PersonalInfoSection (
                state = state,
                onAction = onAction
            )
            HorizontalDivider(
                color = LightDivider,
                thickness = 1.dp,
                modifier = Modifier
                    .fillMaxWidth()
            )
            AddressInfoSection (
                state = state,
                onAction = onAction
            )
            BottomSection(
                state = state,
                onAction = onAction
            )


        }
    }

}
