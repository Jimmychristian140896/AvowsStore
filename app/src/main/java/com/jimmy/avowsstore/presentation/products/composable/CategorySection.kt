package com.jimmy.avowsstore.presentation.products.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.jimmy.avowsstore.core.util.capitalizeWords
import com.jimmy.avowsstore.domain.model.ProductCategory
import com.jimmy.avowsstore.presentation.products.ProductsAction
import com.jimmy.avowsstore.presentation.products.ProductsState
import com.jimmy.avowsstore.ui.theme.Black
import com.jimmy.avowsstore.ui.theme.GrayBackground
import com.jimmy.avowsstore.ui.theme.Green
import com.jimmy.avowsstore.ui.theme.GreenBackground
import com.jimmy.avowsstore.ui.theme.GreenBorder
import com.jimmy.avowsstore.ui.theme.Transparent

@Composable
fun CategorySection(
    /*categories: List<ProductCategory>,
    selectedCategory: ProductCategory,
    onCategorySelected: (ProductCategory) -> Unit,*/

    state: ProductsState,
    onAction: (ProductsAction) -> Unit,
    modifier: Modifier = Modifier) {
    LazyRow(
        modifier = modifier
            .fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(state.categories) {
            CategoryItem(
                category = it,
                isSelected = it == state.selectedCategory,
                onClick = {
                    onAction(ProductsAction.SetSelectedCategory(it))
                }
            )
        }
    }
}

@Composable
fun CategoryItem(
    category: ProductCategory,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(50))
            .background(if(isSelected) GreenBackground else GrayBackground)
            .border(1.dp, if(isSelected) GreenBorder else Transparent, RoundedCornerShape(50))
            .clickable {
                onClick()
            }
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(
            text = category.name.capitalizeWords(),
            color = if(isSelected) Green else Black,
            modifier = Modifier
        )
    }
}