package com.indi.yourgrame.ui.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider
/**
 * <h1>YourGrameViewModelFactory</h1>
 * Helper class to store ViewModels in dagger
 *
 * @author  Indika Kumara
 * @version 1.0
 * @since   2020-01-18
 */
class YourGrameViewModelFactory @Inject constructor(private val creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        var creator: Provider<out ViewModel>? = creators[modelClass]
        if (creator == null) {
            for ((key, value) in creators) {
                if (modelClass.isAssignableFrom(key)) {
                    creator = value
                    break
                }
            }
        }
        if (creator == null)  throw IllegalArgumentException("Unknown model class $modelClass")

        return (creator.get() as T)
    }
}