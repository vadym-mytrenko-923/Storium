package com.storium.ui.screens.product.details

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import com.storium.ui.base.BaseViewModel
import com.storium.ui.navigation.app.AppNavigator
import com.storium.ui.navigation.model.AppNavRoute

class ProductDetailsViewModel(
    savedStateHandle: SavedStateHandle,
    private val appNavigator: AppNavigator,
) : BaseViewModel<ProductDetailsScreenState, ProductDetailsIntent, ProductDetailsEffect>(
    ProductDetailsScreenState(
        productId = savedStateHandle.toRoute<AppNavRoute.ProductDetails>().productId,
    ),
) {
    override fun reduceIntent(intent: ProductDetailsIntent) {
        launchViewModelScope {
            when (intent) {
                is ProductDetailsIntent.BackClicked -> appNavigator.back()
            }
        }
    }
}
