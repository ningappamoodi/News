package com.example.news.di.component

import android.app.Application
import com.example.news.base.BaseApplication
import com.example.news.di.module.ActivityBindingModule
import com.example.news.di.module.ApplicationModule
import com.example.news.di.module.ContextModule
import com.example.news.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, ActivityBindingModule::class, ViewModelModule::class,
    AndroidSupportInjectionModule::class, ContextModule::class])
interface ApplicationComponent : AndroidInjector<DaggerApplication> {

    fun inject(application: BaseApplication)

    @Component.Builder
    interface Builder {

        @BindsInstance
       fun application(application: Application) : Builder

        fun  build() : ApplicationComponent
    }
}