package com.storium.ui.screens.shop

import com.storium.ui.base.BaseViewModel

class ShopViewModel : BaseViewModel<ShopScreenState, ShopIntent, ShopEffect>(ShopScreenState()) {
    override fun reduceIntent(intent: ShopIntent) = Unit
}
