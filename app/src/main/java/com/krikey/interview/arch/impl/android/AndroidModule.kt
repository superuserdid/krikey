package com.krikey.interview.arch.impl.android

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class AndroidModule(private val context: Context) {

    @Provides
    fun providesContext() = context

    @Provides
    fun cacheDirectory() = context.cacheDir

}