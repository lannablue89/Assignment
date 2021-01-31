package com.nab.assignment.di

import android.content.Context
import android.content.SharedPreferences
import com.nab.assignment.MainActivity
import com.nab.assignment.MyApplication
import com.nab.assignment.di.scope.ActivityScope
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import javax.inject.Singleton

/**
 * Created by Lan Tran on 1/31/21.
 */

@Module
abstract class AppModule {
    companion object {
        @Singleton
        @Provides
        fun providesSharedPreferences(context: Context): SharedPreferences {
            return context.getSharedPreferences("app-pref", Context.MODE_PRIVATE)
        }
    }

    @Binds
    @Singleton
    abstract fun providesContext(application: MyApplication): Context

    @ActivityScope
    @ContributesAndroidInjector(modules = [ActivityModule::class])
    abstract fun mainActivity(): MainActivity
}