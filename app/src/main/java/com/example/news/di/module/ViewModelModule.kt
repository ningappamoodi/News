package com.example.news.di.module

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.news.db.DBRepository
import com.example.news.di.util.ViewModelFactory
import com.example.news.viewmodel.HeadLinesViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Singleton
import kotlin.reflect.KClass

@Singleton
@Module
abstract class ViewModelModule {


    @MustBeDocumented
    @Target(
        AnnotationTarget.FUNCTION)
    @Retention(AnnotationRetention.RUNTIME)
    @MapKey
    annotation class ViewModelKey(val value: KClass<out ViewModel>)

    @Binds
    @IntoMap
    @ViewModelKey(HeadLinesViewModel::class)
      abstract fun bindHeadLinesViewModel(viewModel: HeadLinesViewModel): ViewModel

    @Binds
   abstract  fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
