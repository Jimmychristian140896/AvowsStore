package com.jimmy.avowsstore.presentation.products

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
import com.jimmy.avowsstore.presentation.products.composable.CategorySection
import com.jimmy.avowsstore.presentation.products.composable.HeaderSection
import com.jimmy.avowsstore.presentation.products.composable.ProductsSection
import com.jimmy.avowsstore.presentation.profile.ProfileScreenBottomSheet
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProductsScreenRoot(
    navHostController: NavHostController

) {
    val viewModel: ProductsViewModel = koinViewModel<ProductsViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()

    ObserveAsEvents(viewModel.eventChannel) { event ->
        when (event) {
            is ProductsEvent.ProductClicked -> {
                navHostController.navigate(Route.ProductDetail(event.product.id))
            }
            is ProductsEvent.ProfileClicked -> {
                navHostController.navigate(Route.Profile)
            }
            is ProductsEvent.CartClicked -> {
                navHostController.navigate(Route.Cart)
            }
            is ProductsEvent.Logout -> {
                navHostController.navigate(Route.Auth) {
                    popUpTo(Route.Main) {
                        inclusive = true
                    }
                }
            }
            else -> {
            }
        }
    }
    ProductsScreen(
        state = state,
        onAction = viewModel::onAction
    )
}


@Preview
@Composable
private fun Preview() {
    ProductsScreen(
        state = ProductsState(),
        onAction = {}
    )

}

@Composable
fun ProductsScreen(
    state: ProductsState,
    onAction: (ProductsAction) -> Unit,
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
        if(state.errorCategories != null || state.errorProducts != null) {
            GeneralErrorSection(
                onTryAgain = {
                    onAction(ProductsAction.OnTryAgain)
                },
                message = if(state.errorCategories != null) state.errorCategories.asString() else state.errorProducts?.asString()
            )
        } else {

            CategorySection(
                state = state,
                onAction = onAction
            )
            Spacer(modifier = Modifier.height(16.dp))


            ProductsSection(
                state = state,
                onAction = onAction
            )
        }

    }
    if(state.showProfileDialog) {
        ProfileScreenBottomSheet(
            onDismiss = {
                onAction(ProductsAction.DismissProfileDialog)
            },
            onLogout = {
                onAction(ProductsAction.Logout)
            }
        )
    }
}
