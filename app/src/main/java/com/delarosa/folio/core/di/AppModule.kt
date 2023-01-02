package com.delarosa.folio.core.di

import android.content.Context
import com.delarosa.folio.core.data.datasource.LocalStorageDataSource
import com.delarosa.folio.core.data.repositories.LocalStorageRepositoryImpl
import com.delarosa.folio.core.domain.CoroutinesContextProvider
import com.delarosa.folio.core.presentation.LocalStorageDataSourceImpl
import com.delarosa.folio.core.repositories.LocalStorageRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import java.util.concurrent.atomic.AtomicBoolean
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun provideContextProvider(): CoroutinesContextProvider {
        val job = Job()
        return if (isRunningTest?.get() == true) CoroutinesContextProvider(
            Dispatchers.Unconfined, Dispatchers.Unconfined
        ) else CoroutinesContextProvider(Dispatchers.Main + job, Dispatchers.IO)
    }

    @Provides
    fun provideLocalStorageRepository(localStorageDataSource: LocalStorageDataSource): LocalStorageRepository =
        LocalStorageRepositoryImpl(localStorageDataSource)

    @Provides
    fun provideLocalStorageDataSource(@ApplicationContext context: Context): LocalStorageDataSource =
        LocalStorageDataSourceImpl(context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE))

    var isRunningTest: AtomicBoolean? = null
}

const val PREFERENCE_NAME = "folio"
