package com.delarosa.folio.home.di

import com.delarosa.folio.home.data.repositories.DogRepositoryImpl
import com.delarosa.folio.home.data.source.RemoteDogDatasource
import com.delarosa.folio.home.domain.repositories.DogRepository
import com.delarosa.folio.home.presentation.server.DogService
import com.delarosa.folio.home.presentation.server.RemoteDogDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DogModule {

    @Provides
    fun provideDogRepositoryImpl(remoteDogDatasource: RemoteDogDatasource):
        DogRepository = DogRepositoryImpl(remoteDogDatasource)

    @Provides
    fun provideRemoteDogDataSource(dogService: DogService):
        RemoteDogDatasource = RemoteDogDataSourceImpl(dogService)

    @Provides
    @Singleton
    fun provideDogService(retrofit: Retrofit): DogService =
        retrofit.newBuilder().build().create(DogService::class.java)
}
