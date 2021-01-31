package com.nab.assignment.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nab.assignment.ui.weather.WeatherViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton
import kotlin.reflect.KClass

/**
 * Created by Lan Tran on 1/31/21.
 */

@Singleton
class ViewModelFactory @Inject constructor(
//    private val viewModels: MutableMap<Class<out ViewModel>, Provider<ViewModel>>
    private val viewModels: @JvmSuppressWildcards Map<Class<out ViewModel>, Provider<ViewModel>>
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val viewModel = viewModels[modelClass]
        if (viewModel != null) {
            @Suppress("UNCHECKED_CAST")
            return viewModel.get() as T
        } else {
            throw Exception("Unknown class $modelClass")
        }
    }
}

@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
@MapKey
internal annotation class ViewModelKey(val value: KClass<out ViewModel>)

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun providesViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(WeatherViewModel::class)
    internal abstract fun providesWeatherViewModel(viewModel: WeatherViewModel): ViewModel

}