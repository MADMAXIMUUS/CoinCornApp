package ru.coincorn.app.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MainNavigation

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class NestedNavigation