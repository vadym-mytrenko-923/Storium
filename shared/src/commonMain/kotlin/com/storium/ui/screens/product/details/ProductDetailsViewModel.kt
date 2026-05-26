package com.storium.ui.screens.product.details

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import com.storium.domain.features.product.usecase.GetProductByIdUseCase
import com.storium.ui.base.BaseViewModel
import com.storium.ui.navigation.app.AppNavigator
import com.storium.ui.navigation.model.AppNavRoute
import com.storium.ui.screens.product.details.mapper.ProductDetailsUiMapper

class ProductDetailsViewModel(
    savedStateHandle: SavedStateHandle,
    private val appNavigator: AppNavigator,
    private val getProductByIdUseCase: GetProductByIdUseCase,
    private val productDetailsUiMapper: ProductDetailsUiMapper,
) : BaseViewModel<ProductDetailsScreenState, ProductDetailsIntent, ProductDetailsEffect>(
    ProductDetailsScreenState(),
) {
    init {
        loadProduct(productId = savedStateHandle.toRoute<AppNavRoute.ProductDetails>().productId)
    }

    override fun reduceIntent(intent: ProductDetailsIntent) {
        launchViewModelScope {
            when (intent) {
                is ProductDetailsIntent.BackClicked -> appNavigator.back()
            }
        }
    }

    private fun loadProduct(productId: Int) {
        launchViewModelScope {
            getProductByIdUseCase(productId).onSuccess { product ->
                updateUiState {
                    copy(
                        isLoading = false,
                        product = productDetailsUiMapper.map(product),
                    )
                }
            }.onFailure {
                updateUiState { copy(isLoading = false) }
            }
        }
    }
}
