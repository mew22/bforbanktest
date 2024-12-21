package com.sd.bforbanktest.feature.pokemondetail.data

import com.sd.bforbanktest.core.network.NetworkBuilder
import com.sd.bforbanktest.feature.pokemondetail.data.mock.MockService
import com.sd.bforbanktest.feature.pokemondetail.domain.PokemonDetailRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class PokemonDetailModule {

    @Binds
    internal abstract fun providePokemonDetailRepository(
        impl: PokemonDetailRepositoryImpl,
    ): PokemonDetailRepository

    internal companion object {
        @Provides
        fun providePokemonDetailServiceService(
            networkBuilder: NetworkBuilder
        ): PokemonDetailService = networkBuilder.build().create(MockService::create)
    }
}
