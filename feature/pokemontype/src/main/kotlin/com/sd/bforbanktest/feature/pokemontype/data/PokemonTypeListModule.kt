package com.sd.bforbanktest.feature.pokemontype.data

import com.sd.bforbanktest.core.network.NetworkBuilder
import com.sd.bforbanktest.feature.pokemontype.domain.PokemonTypeListRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class PokemonTypeListModule {

    @Binds
    internal abstract fun providePokemonTypeListRepository(
        impl: PokemonTypeListRepositoryImpl,
    ): PokemonTypeListRepository

    internal companion object {
        @Provides
        fun providePokemonTypeListServiceService(networkBuilder: NetworkBuilder): PokemonTypeListService =
            networkBuilder.build().create(PokemonTypeListServiceMock::create)
    }
}
