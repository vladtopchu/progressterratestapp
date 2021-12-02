package ru.topchu.progressterra.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import ru.topchu.iprobonusapi.IpbApi
import ru.topchu.progressterra.data.repository.IpbRepositoryImpl
import ru.topchu.progressterra.domain.repository.IpbRepository
import ru.topchu.progressterra.utils.Constants.API_KEY
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideApi(): IpbApi = IpbApi.Builder().setAccessKey(API_KEY).build()

    @Singleton
    @Provides
    fun provideRepository(ipbApi: IpbApi): IpbRepository = IpbRepositoryImpl(ipbApi)

    @ApplicationScope
    @Provides
    @Singleton
    fun provideApplicationScope() = CoroutineScope(SupervisorJob())
}

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class ApplicationScope