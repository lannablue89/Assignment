package com.nab.assignment.di

import androidx.lifecycle.ViewModel
import com.nab.assignment.di.scope.FragmentScope
import com.nab.assignment.ui.weather.WeatherFragment
import com.nab.assignment.ui.weather.WeatherViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class ActivityModule {

    @FragmentScope
    @ContributesAndroidInjector(modules = [FragmentModule::class])
    abstract fun providesWeatherFragment(): WeatherFragment

    @Binds
    @IntoMap
    @ViewModelKey(WeatherViewModel::class)
    abstract fun providesWeatherViewModel(viewModel: WeatherViewModel): ViewModel

}
