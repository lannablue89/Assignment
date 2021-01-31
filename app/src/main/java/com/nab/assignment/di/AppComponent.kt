package com.nab.assignment.di

import com.nab.assignment.MyApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Created by Lan Tran on 1/31/21.
 */

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    ViewModelModule::class,
    AppModule::class,
    NetworkModule::class,
])
interface AppComponent : AndroidInjector<MyApplication> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application:MyApplication): AppComponent
    }

}