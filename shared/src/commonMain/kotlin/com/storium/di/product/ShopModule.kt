package com.storium.di.product

import com.storium.data.features.product.ProductRepositoryImpl
import com.storium.data.features.product.remote.source.ProductRemoteDataSource
import com.storium.data.features.product.remote.source.ProductRemoteDataSourceImpl
import com.storium.domain.features.product.ProductRepository
import com.storium.domain.features.product.usecase.GetProductsFlowUseCase
import com.storium.ui.screens.shop.ShopViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val shopModule = module {
    // Data Sources
    single<ProductRemoteDataSource> { ProductRemoteDataSourceImpl(get()) }

    // Repositories
    single<ProductRepository> { ProductRepositoryImpl(get(), get()) }

    // Use Cases
    factory { GetProductsFlowUseCase(get()) }

    // ViewModels
    viewModel { ShopViewModel(get()) }
}
