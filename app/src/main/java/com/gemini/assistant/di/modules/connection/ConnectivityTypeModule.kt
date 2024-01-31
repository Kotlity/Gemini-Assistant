package com.gemini.assistant.di.modules.connection

import com.gemini.assistant.data.internet_connection.InternetConnectivityTypeHandler
import com.gemini.assistant.domain.connection.ConnectivityTypeHandler
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class ConnectivityTypeModule {

    @Binds
    @ViewModelScoped
    abstract fun bindConnectivityTypeHandler(internetConnectivityTypeHandler: InternetConnectivityTypeHandler): ConnectivityTypeHandler
}