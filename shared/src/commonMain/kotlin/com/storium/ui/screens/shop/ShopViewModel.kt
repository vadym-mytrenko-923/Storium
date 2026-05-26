package com.storium.ui.screens.shop

import com.storium.domain.features.product.usecase.GetProductsFlowUseCase
import com.storium.domain.features.product.usecase.GetProductsSearchQueryFlowUseCase
import com.storium.domain.features.product.usecase.GetSelectedCategoryIdsFlowUseCase
import com.storium.domain.features.product.usecase.SetProductsSearchQueryUseCase
import com.storium.domain.features.product.usecase.ToggleCategorySelectionUseCase
import com.storium.ui.base.BaseViewModel
import com.storium.ui.navigation.app.AppNavigator
import com.storium.ui.navigation.model.AppNavRoute
import com.storium.ui.screens.shop.mapper.toUiModels
import com.storium.ui.screens.shop.model.DisplayMode
import kotlinx.coroutines.flow.combine

class ShopViewModel(
    private val appNavigator: AppNavigator,
    private val getProductsFlowUseCase: GetProductsFlowUseCase,
    private val getSelectedCategoryIdsFlowUseCase: GetSelectedCategoryIdsFlowUseCase,
    private val getProductsSearchQueryFlowUseCase: GetProductsSearchQueryFlowUseCase,
    private val toggleCategorySelectionUseCase: ToggleCategorySelectionUseCase,
    private val setProductsSearchQueryUseCase: SetProductsSearchQueryUseCase,
) : BaseViewModel<ShopScreenState, ShopIntent, ShopEffect>(ShopScreenState()) {

    init {
        observeProducts()
    }

    override fun reduceIntent(intent: ShopIntent) {
        launchViewModelScope {
            when (intent) {
                is ShopIntent.CategoryToggled -> toggleCategorySelectionUseCase(intent.categoryId)
                is ShopIntent.DisplayModeToggled -> onDisplayModeToggled()
                is ShopIntent.ProductClicked -> appNavigator.navigateTo(AppNavRoute.ProductDetails(intent.productId))
                is ShopIntent.SearchToggled -> onSearchToggled()
                is ShopIntent.SearchQueryChanged -> setProductsSearchQueryUseCase(intent.query)
            }
        }
    }

    private fun observeProducts() {
        launchViewModelScope {
            combine(
                getProductsFlowUseCase(),
                getSelectedCategoryIdsFlowUseCase(),
                getProductsSearchQueryFlowUseCase(),
            ) { dataState, selectedCategoryIds, searchQuery ->
                currentState.copy(
                    products = dataState.products.toUiModels(),
                    categories = dataState.categories.toUiModels(selectedIds = selectedCategoryIds),
                    isLoading = dataState.isLoading,
                    searchQuery = searchQuery,
                )
            }.collect { uiState ->
                updateUiState { uiState }
            }
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

    private suspend fun onSearchToggled() {
        if (currentState.isSearchActive) {
            setProductsSearchQueryUseCase("")
        }

        updateUiState { copy(isSearchActive = !currentState.isSearchActive) }
    }
}
