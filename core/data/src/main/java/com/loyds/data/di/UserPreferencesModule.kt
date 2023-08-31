package com.loyds.data.di

import android.content.Context
import com.loyds.data.preferences.dataStore
import com.loyds.data.repository.UserPreferencesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserPreferencesModule {

    @Singleton
    @Provides
    fun providesRepository(@ApplicationContext context: Context) =
        UserPreferencesRepository(context.dataStore)
}
