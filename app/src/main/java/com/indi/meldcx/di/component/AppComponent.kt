package com.indi.meldcx.di.component

import android.app.Application
import com.indi.meldcx.MeldCX
import com.indi.meldcx.di.builder.ActivityBuilder
import com.indi.meldcx.di.module.AppModule
import com.indi.meldcx.di.module.AppViewModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule

/**
 * <h1>AppComponent</h1>
 * Injecting MeldCX Modules to dagger
 *
 * @author  Indika Kumara
 * @version 1.0
 * @since   2020-01-18
 */
@Component(modules = [AndroidSupportInjectionModule::class,AppModule::class,ActivityBuilder::class,AppViewModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }
    fun inject(app: MeldCX)
}