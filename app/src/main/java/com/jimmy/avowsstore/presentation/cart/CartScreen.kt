package com.jimmy.avowsstore.presentation.cart

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
import com.jimmy.avowsstore.core.composable.ObserveAsEvents
import com.jimmy.avowsstore.navigation.Route
import com.jimmy.avowsstore.presentation.cart.composable.BottomSection
import com.jimmy.avowsstore.presentation.cart.composable.CartSection
import com.jimmy.avowsstore.presentation.cart.composable.HeaderSection
import com.jimmy.avowsstore.ui.theme.LightDivider
import org.koin.androidx.compose.koinViewModel

@Composable
fun CartScreenRoot(
    navHostController: NavHostController
) {
    val viewModel: CartViewModel = koinViewModel<CartViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()

    ObserveAsEvents(viewModel.eventChannel) { event ->
        when (event) {
            is CartEvent.OnCheckout -> {
                navHostController.navigate(Route.Summary(event.id))
            }

            CartEvent.NavigateBack -> {
                navHostController.navigateUp()
            }
            else -> {
            }
        }
    }
    CartScreen(
        state = state,
        onAction = viewModel::onAction
    )
}


@Preview
@Composable
private fun Preview() {
    CartScreen(
        state = CartState(),
        onAction = {}
    )

}

@Composable
fun CartScreen(
    state: CartState,
    onAction: (CartAction) -> Unit,
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
        CartSection(
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
