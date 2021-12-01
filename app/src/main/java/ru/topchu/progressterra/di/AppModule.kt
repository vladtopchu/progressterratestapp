package ru.topchu.progressterra.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.topchu.iprobonusapi.IpbApi
import ru.topchu.iprobonusapi.IpbApiProvider
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideApi(): IpbApi = IpbApiProvider()
}