package com.sd.bforbanktest.core.network

import com.sd.bforbank.core.environment.EnvironmentGateway
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [NetworkModule::class]
)
internal interface TestComponent {

    val networkBuilder: NetworkBuilder

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance environmentGateway: EnvironmentGateway,
            @BindsInstance api: Api,
        ): TestComponent
    }
}
