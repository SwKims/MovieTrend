package com.ksw.movietrend.di

import com.squareup.inject.assisted.dagger2.AssistedModule
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

/**
 * Created by KSW on 2021-02-09
 */

@InstallIn(FragmentComponent::class)
@AssistedModule
@Module
interface AssistedInject {

}