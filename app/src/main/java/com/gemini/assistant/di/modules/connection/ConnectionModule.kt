package com.gemini.assistant.di.modules.connection

import android.content.Context
import com.gemini.assistant.data.internet_connection.InternetConnectionHandler
import com.gemini.assistant.domain.connection.ConnectionHandler
import com.gemini.assistant.domain.connection.ConnectivityTypeHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped

@Module(includes = [ConnectivityTypeModule::class])
@InstallIn(ViewModelComponent::class)
object ConnectionModule {

    @Provides
    @ViewModelScoped
    fun provideConnectionHandler(
        @ApplicationContext context: Context,
        connectivityTypeHandler: ConnectivityTypeHandler
    ): ConnectionHandler {
        return InternetConnectionHandler(
            context = context,
            connectivityTypeHandler = connectivityTypeHandler
        )
    }
}