package com.example.news.di.module

import com.example.news.view.main.MainActivity
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector
    abstract fun contributeMainActivity() : MainActivity
}