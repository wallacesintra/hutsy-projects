package com.example.hilt_tutorial.di

import android.content.Context
import androidx.room.Room
import com.example.hilt_tutorial.data.local.UserDatabase
import com.example.hilt_tutorial.data.repository.PostsRepositoryImpl
import com.example.hilt_tutorial.domain.repository.PostsRepository
import com.example.hilt_tutorial.network.api.PlaceholderApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    private const val BASE_URL ="https://jsonplaceholder.typicode.com/"


    @Singleton
    @Provides
    fun providePlaceholderApi(): PlaceholderApi {
        return Retrofit.Builder()
//            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(PlaceholderApi::class.java)
    }

    @Provides
    @Singleton
    fun providesPostRepositoryIml(api: PlaceholderApi): PostsRepository{
        return PostsRepositoryImpl(api)
    }


    @Provides
    @Singleton
    fun provideContext(@ApplicationContext context: Context): Context {
        return context
    }


    @Provides
    @Singleton
    fun providesLocalUsersDetails(context: Context): UserDatabase {
        return Room.databaseBuilder(
            context,
            UserDatabase::class.java,
            "users.db"
        ).build()
    }

}