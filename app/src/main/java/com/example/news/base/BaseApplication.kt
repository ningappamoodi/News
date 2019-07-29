package com.example.news.base

import com.example.news.di.component.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class BaseApplication : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
       val component =  DaggerApplicationComponent.builder().application(this).build()
        component.inject(this)

        return component
    }
}