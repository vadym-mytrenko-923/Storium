package com.storium.ui.navigation.model

sealed interface NavigationEffect<out R> {
    data class Navigate<R>(val route: R) : NavigationEffect<R>
    data class ReturnTo<R>(val route: R) : NavigationEffect<R>
    data object Back : NavigationEffect<Nothing>
}
