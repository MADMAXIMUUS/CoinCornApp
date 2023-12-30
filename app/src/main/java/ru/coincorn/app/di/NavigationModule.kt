package ru.coincorn.app.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.coincorn.app.navigation.AppNavigator
import ru.coincorn.app.navigation.AppNavigatorImpl
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class NavigationModule {

    @Binds
    @Singleton
    @MainNavigation
    abstract fun bindMainNavigation(
        appNavigatorImpl: AppNavigatorImpl
    ): AppNavigator

    @Binds
    @Singleton
    @NestedNavigation
    abstract fun bindNestedNavigation(
        appNavigatorImpl: AppNavigatorImpl
    ): AppNavigator
}