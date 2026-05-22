package com.storium.di.product

import com.storium.data.features.product.ProductRepositoryImpl
import com.storium.data.features.product.remote.source.ProductRemoteDataSource
import com.storium.data.features.product.remote.source.ProductRemoteDataSourceImpl
import com.storium.domain.features.product.ProductRepository
import com.storium.domain.features.product.usecase.GetProductsFlowUseCase
import com.storium.domain.features.product.usecase.GetSelectedCategoryIdsFlowUseCase
import com.storium.domain.features.product.usecase.ToggleCategorySelectionUseCase
import com.storium.ui.screens.product.details.ProductDetailsViewModel
import com.storium.ui.screens.shop.ShopViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val productModule = module {
    // Data Sources
    single<ProductRemoteDataSource> { ProductRemoteDataSourceImpl(get()) }

    // Repositories
    single<ProductRepository> { ProductRepositoryImpl(get(), get()) }

    // Use Cases
    factory { GetProductsFlowUseCase(get()) }
    factory { GetSelectedCategoryIdsFlowUseCase(get()) }
    factory { ToggleCategorySelectionUseCase(get()) }

    // ViewModels
    viewModel { ShopViewModel(get(), get(), get(), get()) }
    viewModel { ProductDetailsViewModel(get(), get()) }
}
