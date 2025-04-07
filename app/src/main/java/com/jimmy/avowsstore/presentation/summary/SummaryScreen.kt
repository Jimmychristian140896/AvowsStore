package com.jimmy.avowsstore.presentation.summary

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.jimmy.avowsstore.core.composable.GeneralErrorSection
import com.jimmy.avowsstore.core.composable.ObserveAsEvents
import com.jimmy.avowsstore.navigation.Route
import com.jimmy.avowsstore.presentation.cart.CartAction
import com.jimmy.avowsstore.presentation.summary.composable.BottomSection
import com.jimmy.avowsstore.presentation.summary.composable.HeaderSection
import com.jimmy.avowsstore.presentation.summary.composable.SummaryLoadingSection
import com.jimmy.avowsstore.presentation.summary.composable.SummarySection
import com.jimmy.avowsstore.ui.theme.LightDivider
import org.koin.androidx.compose.koinViewModel

@Composable
fun SummaryScreenRoot(
    navHostController: NavHostController

) {
    val viewModel: SummaryViewModel = koinViewModel<SummaryViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()

    ObserveAsEvents(viewModel.eventChannel) { event ->
        when (event) {
            SummaryEvent.Close -> {
                navHostController.navigate(Route.Products) {
                    popUpTo(Route.Products) {
                        inclusive = true
                    }
                }
            }
            else -> {
            }
        }
    }
    SummaryScreen(
        state = state,
        onAction = viewModel::onAction
    )
}


@Preview
@Composable
private fun Preview() {
    SummaryScreen(
        state = SummaryState(),
        onAction = {}
    )

}

@Composable
fun SummaryScreen(
    state: SummaryState,
    onAction: (SummaryAction) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = White)
    ) {
        HeaderSection(
            state = state,
            onAction = onAction
        )
        HorizontalDivider(
            color = LightDivider,
            thickness = 1.dp,
            modifier = Modifier
                .fillMaxWidth()
        )
        if(state.isLoading) {
            SummaryLoadingSection(
                modifier = Modifier
                    .weight(1f)
            )
        } else if(state.error != null) {
            GeneralErrorSection(
                onTryAgain = {
                    onAction(SummaryAction.OnTryAgain)
                },
                message = state.error.asString()
            )
        } else {
            SummarySection(
                state = state,
                onAction = onAction,
                modifier = Modifier
                    .weight(1f)
            )

            HorizontalDivider(
                color = LightDivider,
                thickness = 1.dp,
                modifier = Modifier
                    .fillMaxWidth()
            )
            BottomSection(
                state = state,
                onAction = onAction
            )
        }
    }
}
