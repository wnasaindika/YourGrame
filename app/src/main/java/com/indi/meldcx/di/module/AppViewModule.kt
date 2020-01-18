package com.indi.meldcx.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.indi.meldcx.ui.vm.MeldCXViewModel
import com.indi.meldcx.ui.vm.MeldCXViewModelFactory
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

@Module
abstract class AppViewModule {
    @Binds
    @IntoMap
    @ViewModelKey(MeldCXViewModel::class)
    internal abstract fun bindMeldCXViewModel(viewModel: MeldCXViewModel): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(factory: MeldCXViewModelFactory): ViewModelProvider.Factory

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