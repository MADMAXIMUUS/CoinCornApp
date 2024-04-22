package ru.coincorn.app.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.coincorn.app.core.notification.NotificationHelper
import ru.coincorn.app.core.notification.PushEventBus
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NotificationModule {

    @Singleton
    @Provides
    fun provideNotificationHelper(@ApplicationContext appContext: Context): NotificationHelper {
        return NotificationHelper(appContext)
    }

    @Singleton
    @Provides
    fun providePushEventBus(): PushEventBus = PushEventBus()

}