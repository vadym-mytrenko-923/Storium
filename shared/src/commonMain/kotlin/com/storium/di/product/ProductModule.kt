package com.storium.di.product

import com.storium.data.features.product.ProductRepositoryImpl
import com.storium.data.features.product.remote.source.ProductRemoteDataSource
import com.storium.data.features.product.remote.source.ProductRemoteDataSourceImpl
import com.storium.domain.features.product.ProductRepository
import com.storium.domain.features.product.usecase.GetProductByIdUseCase
import com.storium.domain.features.product.usecase.GetProductsFlowUseCase
import com.storium.domain.features.product.usecase.GetSelectedCategoryIdsFlowUseCase
import com.storium.domain.features.product.usecase.ToggleCategorySelectionUseCase
import com.storium.ui.screens.product.details.ProductDetailsViewModel
import com.storium.ui.screens.product.details.mapper.ProductDetailsUiMapper
import com.storium.ui.screens.product.details.mapper.ReviewUiMapper
import com.storium.ui.screens.product.utils.ReviewDateFormatter
import com.storium.ui.screens.shop.ShopViewModel
import com.storium.util.date.DateFormatter
import org.koin.core.module.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

private const val REVIEW_DATE_FORMATTER = "reviewDate"

val productModule = module {
    // Data Sources
    single<ProductRemoteDataSource> { ProductRemoteDataSourceImpl(get()) }

    // Repositories
    single<ProductRepository> { ProductRepositoryImpl(get(), get()) }

    // Mappers
    factory { ReviewUiMapper(get(named(REVIEW_DATE_FORMATTER))) }
    factory { ProductDetailsUiMapper(get()) }

    // Use Cases
    factory { GetProductByIdUseCase(get()) }
    factory { GetProductsFlowUseCase(get()) }
    factory { GetSelectedCategoryIdsFlowUseCase(get()) }
    factory { ToggleCategorySelectionUseCase(get()) }

    // ViewModels
    viewModel { ShopViewModel(get(), get(), get(), get()) }
    viewModel { ProductDetailsViewModel(get(), get(), get(), get()) }

    // Formatters
    single<DateFormatter>(named(REVIEW_DATE_FORMATTER)) { ReviewDateFormatter() }
}
