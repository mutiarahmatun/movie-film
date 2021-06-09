package com.dicoding.mutiarahmatun.jetpack.moviefilm

import com.dicoding.mutiarahmatun.jetpack.moviefilm.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class BaseApplication : DaggerApplication(){
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
            DaggerAppComponent.builder().application(this).build()

}