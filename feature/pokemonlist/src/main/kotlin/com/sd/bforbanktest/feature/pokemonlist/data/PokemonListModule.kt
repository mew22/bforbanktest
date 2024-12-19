package com.sd.bforbanktest.feature.pokemonlist.data

import com.sd.bforbanktest.core.network.NetworkBuilder
import com.sd.bforbanktest.feature.pokemonlist.data.mock.MockService
import com.sd.bforbanktest.feature.pokemonlist.domain.PokemonListItemRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class PokemonListModule {

    @Binds
    internal abstract fun providePokemonListRepository(
        impl: PokemonListItemRepositoryImpl,
    ): PokemonListItemRepository

    internal companion object {
        @Provides
        fun providePokemonListServiceService(networkBuilder: NetworkBuilder): PokemonListService =
            networkBuilder.build().create(MockService::create)
    }
}
