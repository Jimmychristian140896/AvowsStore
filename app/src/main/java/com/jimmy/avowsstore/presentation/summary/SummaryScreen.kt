package com.jimmy.avowsstore.presentation.summary

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.jimmy.avowsstore.core.composable.ObserveAsEvents
import org.koin.androidx.compose.koinViewModel

@Composable
fun SummaryScreenRoot(
    navHostController: NavHostController

) {
    val viewModel: SummaryViewModel = koinViewModel<SummaryViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()

    ObserveAsEvents(viewModel.eventChannel) { event ->
        when (event) {
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

}
