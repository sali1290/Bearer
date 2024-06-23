package com.example.bearer.model.di

import com.example.bearer.model.repo.ParcelServiceRepo
import com.example.bearer.model.repo.ParcelServiceRepoImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepoModule {

    @Binds
    @Singleton
    fun bindParcelService(parcelServiceRepoImpl: ParcelServiceRepoImpl): ParcelServiceRepo

}