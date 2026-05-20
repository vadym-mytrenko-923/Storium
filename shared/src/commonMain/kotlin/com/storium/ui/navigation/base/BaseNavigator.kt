package com.storium.ui.navigation.base

import com.storium.ui.navigation.model.NavigationEffect
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow

abstract class BaseNavigator<R> : Navigator<R> {
    private val effectChannel = Channel<NavigationEffect<R>>(Channel.BUFFERED)
    override val navigationEffect: Flow<NavigationEffect<R>> = effectChannel.receiveAsFlow()

    override suspend fun navigateTo(route: R) {
        effectChannel.send(NavigationEffect.Navigate(route))
    }

    override suspend fun returnTo(route: R) {
        effectChannel.send(NavigationEffect.ReturnTo(route))
    }

    override suspend fun back() {
        effectChannel.send(NavigationEffect.Back)
    }
}
