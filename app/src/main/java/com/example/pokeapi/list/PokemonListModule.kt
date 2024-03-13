package com.example.pokeapi.list

import com.example.pokeapi.common.APIService
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
object PokemonListModule {
    @Provides
    fun provideRetrofitClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/") // Specify your base URL
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient())// Add converter factory for Gson
            .build()
    }

    @Provides
    fun provideAPIService(retrofitClient: Retrofit): APIService {
        return retrofitClient.create(APIService::class.java)
    }

    @Provides
    fun providePokemonListRepository(apiService: APIService): PokemonListRepository {
        return PokemonListRepository(apiService)
    }
}

@Module
@InstallIn(ViewModelComponent::class)
interface PokemonListModuleInterface {
    @Binds
    abstract fun bindPokemonListRepository(impl: PokemonListRepository): PokemonListRepositoryInterface
}