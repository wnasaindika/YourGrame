package com.indi.yourgrame.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.indi.yourgrame.ui.vm.YourGrameViewModel
import com.indi.yourgrame.ui.vm.YourGrameViewModelFactory
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

/**
 * <h1>AppViewModule</h1>
 * Adding YourGrame View Module dependencies to AppViewModule.
 * this class contains all ViewModel and ViewModel related bind
 *
 * @author  Indika Kumara
 * @version 1.0
 * @since   2020-01-18
 */
@Module
abstract class AppViewModule {
    @Binds
    @IntoMap
    @ViewModelKey(YourGrameViewModel::class)
    internal abstract fun bindYourGrameViewModel(viewModel: YourGrameViewModel): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(factory: YourGrameViewModelFactory): ViewModelProvider.Factory

    @MustBeDocumented
    @Target(
        AnnotationTarget.FUNCTION,
        AnnotationTarget.CONSTRUCTOR,
        AnnotationTarget.PROPERTY_GETTER,
        AnnotationTarget.PROPERTY_SETTER
    )
    @Retention(AnnotationRetention.RUNTIME)
    @MapKey
    internal annotation class ViewModelKey(val value: KClass<out ViewModel>)
}