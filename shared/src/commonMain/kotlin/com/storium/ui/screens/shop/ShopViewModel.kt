package com.storium.ui.screens.shop

import com.storium.domain.features.product.model.Category
import com.storium.domain.features.product.model.Product
import com.storium.domain.features.product.usecase.GetProductsFlowUseCase
import com.storium.ui.base.BaseViewModel
import com.storium.ui.screens.shop.mapper.toUiModels
import com.storium.ui.screens.shop.model.DisplayMode

class ShopViewModel(
    private val getProductsFlowUseCase: GetProductsFlowUseCase,
) : BaseViewModel<ShopScreenState, ShopIntent, ShopEffect>(ShopScreenState()) {

    init {
        observeProducts()
    }

    override fun reduceIntent(intent: ShopIntent) {
        when (intent) {
            is ShopIntent.CategoryToggled -> onCategoryToggled(intent.category)
            is ShopIntent.DisplayModeToggled -> onDisplayModeToggled()
            is ShopIntent.ProductClicked -> Unit
        }
    }

    private fun observeProducts() {
        launchViewModelScope {
            getProductsFlowUseCase().collect { dataState ->
                val selected = currentState.selectedCategories.ifEmpty { dataState.categories }
                val selectedIds = selected.map { it.id }.toSet()

                updateUiState {
                    copy(
                        allProducts = dataState.products,
                        products = dataState.products.filterByCategories(selectedIds).toUiModels(),
                        categories = dataState.categories.toUiModels(selectedIds),
                        selectedCategories = selected,
                        isLoading = dataState.isLoading,
                    )
                }
            }
        }
    }

    private fun onCategoryToggled(category: Category) {
        val current = currentState.selectedCategories
        val allCategories = currentState.categories.map { Category(id = it.id, name = it.name) }
        val updated = if (category in current) current - category else current + category

        val resolved = if (updated.size == allCategories.size) allCategories else updated
        val selectedIds = resolved.map { it.id }.toSet()

        updateUiState {
            copy(
                selectedCategories = resolved,
                categories = categories.map { it.copy(isSelected = it.id in selectedIds) },
                products = allProducts.filterByCategories(selectedIds).toUiModels(),
            )
        }
    }

    private fun onDisplayModeToggled() {
        updateUiState {
            copy(
                displayMode = when (displayMode) {
                    DisplayMode.List -> DisplayMode.Grid
                    DisplayMode.Grid -> DisplayMode.List
                },
            )
        }
    }

    private fun List<Product>.filterByCategories(selectedIds: Set<String>): List<Product> =
        if (selectedIds.isEmpty()) emptyList() else filter { it.category in selectedIds }
}
