package com.jimmy.avowsstore.presentation.cart

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.jimmy.avowsstore.R
import com.jimmy.avowsstore.core.composable.GeneralErrorSection
import com.jimmy.avowsstore.core.composable.ObserveAsEvents
import com.jimmy.avowsstore.core.ui.SnackBarAction
import com.jimmy.avowsstore.core.ui.SnackBarController
import com.jimmy.avowsstore.core.ui.SnackBarEvent
import com.jimmy.avowsstore.navigation.Route
import com.jimmy.avowsstore.presentation.cart.composable.BottomSection
import com.jimmy.avowsstore.presentation.cart.composable.CartLoadingSection
import com.jimmy.avowsstore.presentation.cart.composable.CartSection
import com.jimmy.avowsstore.presentation.cart.composable.EmptySection
import com.jimmy.avowsstore.presentation.cart.composable.HeaderSection
import com.jimmy.avowsstore.presentation.cart.composable.SelectedCountSection
import com.jimmy.avowsstore.presentation.productdetail.ProductDetailAction
import com.jimmy.avowsstore.ui.theme.LightDivider
import com.jimmy.avowsstore.ui.theme.LightestDivider
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun CartScreenRoot(
    navHostController: NavHostController
) {
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
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

            CartEvent.OnStartShoppingClicked -> {
                navHostController.navigate(Route.Products)
            }

            CartEvent.OnNoProductSelected -> {
                coroutineScope.launch {
                    SnackBarController.sendEvent(
                        SnackBarEvent(
                            message = context.getString(R.string.you_have_not_selected_any_items_for_checkout),
                        )
                    )
                }
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
        if(state.isLoading) {
            CartLoadingSection(
                modifier = Modifier
                    .weight(1f)
            )
        } else if(state.error != null) {
            GeneralErrorSection(
                onTryAgain = {
                    onAction(CartAction.OnTryAgain)
                },
                message = state.error.asString()
            )
        }
        else if(state.cart != null && state.cart.products.isEmpty()) {
            EmptySection(
                state = state,
                onAction = onAction
            )
        } else {
            val countChecked = state.cart?.products?.count { it.isChecked } ?: 0
            AnimatedVisibility(
                visible = countChecked> 0,
            ) {
                Column {
                    SelectedCountSection(
                        countChecked = countChecked,
                        state = state,
                        onAction = onAction,
                        /*
                modifier = Modifier
                    .animateItem(
                        fadeInSpec = tween(300),
                        fadeOutSpec = tween(300)
                    )*/
                    )
                    HorizontalDivider(
                        color = LightestDivider,
                        thickness = 1.dp,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
            }


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
}
