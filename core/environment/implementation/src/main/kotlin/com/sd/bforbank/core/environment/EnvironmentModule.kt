package com.sd.bforbank.core.environment

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class EnvironmentModule {

    @Binds
    @Singleton
    internal abstract fun environmentGateway(impl: Environment): EnvironmentGateway
}
