package com.hdeva.expensr.di

import android.content.Context
import androidx.fragment.app.FragmentManager
import com.hdeva.expensr.ui.base.BaseActivity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext

@Module
@InstallIn(ActivityComponent::class)
class ActivityModule {

    @Provides
    fun provideFragmentManager(@ActivityContext context: Context): FragmentManager {
        return (context as BaseActivity<*>).supportFragmentManager
    }
}
