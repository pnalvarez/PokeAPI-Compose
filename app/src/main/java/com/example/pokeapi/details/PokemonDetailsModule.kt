package com.example.pokeapi.details

import com.example.pokeapi.common.APIService
import com.example.pokeapi.list.PokemonListRepository
import com.example.pokeapi.list.PokemonListRepositoryInterface
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object PokemonDetailsModule {
    @Provides
    fun providePokemonDetailsRepository(apiService: APIService): PokemonDetailsRepository {
        return PokemonDetailsRepository(apiService)
    }
}

@Module
@InstallIn(ViewModelComponent::class)
interface PokemonDetailsModuleInterface {
    @Binds
    abstract fun bindPokemonDetailsRepository(impl: PokemonDetailsRepository): PokemonDetailsRepositoryInterface
}

