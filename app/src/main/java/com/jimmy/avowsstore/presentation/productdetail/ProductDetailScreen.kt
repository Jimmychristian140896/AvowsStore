package com.jimmy.avowsstore.presentation.productdetail

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
import com.jimmy.avowsstore.core.ui.showToast
import com.jimmy.avowsstore.navigation.Route
import com.jimmy.avowsstore.presentation.productdetail.composable.BottomLoadingSection
import com.jimmy.avowsstore.presentation.productdetail.composable.BottomSection
import com.jimmy.avowsstore.presentation.productdetail.composable.DetailLoadingSection
import com.jimmy.avowsstore.presentation.productdetail.composable.DetailSection
import com.jimmy.avowsstore.presentation.productdetail.composable.HeaderSection
import com.jimmy.avowsstore.presentation.products.ProductsAction
import com.jimmy.avowsstore.presentation.products.ProductsEvent
import com.jimmy.avowsstore.ui.theme.LightDivider
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProductDetailScreenRoot(
    navHostController: NavHostController

) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val viewModel: ProductDetailViewModel = koinViewModel<ProductDetailViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()

    ObserveAsEvents(viewModel.eventChannel) { event ->
        when (event) {
            ProductDetailEvent.NavigateBack -> {
                navHostController.navigateUp()
            }

            is ProductDetailEvent.AddToCartFailed -> {
                context.showToast(event.message.asString(context))
            }
            ProductDetailEvent.AddToCartSuccess -> {
                coroutineScope.launch {
                    SnackBarController.sendEvent(
                        SnackBarEvent(
                            message = context.getString(R.string.added_to_cart),
                            action = SnackBarAction(
                                name = context.getString(R.string.open_cart),
                                action = {
                                    viewModel.onAction(ProductDetailAction.CartClicked)
                                }
                            )
                        )
                    )
                }
                //context.showToast("Added to Cart")
            }

            is ProductDetailEvent.CartClicked -> {
                navHostController.navigate(Route.Cart)
            }
            else -> {
            }
        }
    }
    ProductDetailScreen(
        state = state,
        onAction = viewModel::onAction
    )
}


@Preview
@Composable
private fun Preview() {
    ProductDetailScreen(
        state = ProductDetailState(),
        onAction = {}
    )

}

@Composable
fun ProductDetailScreen(
    state: ProductDetailState,
    onAction: (ProductDetailAction) -> Unit,
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
            DetailLoadingSection(
                modifier = Modifier
                    .weight(1f)
            )
            HorizontalDivider(
                color = LightDivider,
                thickness = 1.dp,
                modifier = Modifier
                    .fillMaxWidth()
            )
            BottomLoadingSection()
        } else if(state.error != null) {
            GeneralErrorSection(
                onTryAgain = {
                    onAction(ProductDetailAction.OnTryAgain)
                },
                message = state.error.asString()
            )
        } else {
            DetailSection(
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
