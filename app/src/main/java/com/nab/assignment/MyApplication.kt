package com.nab.assignment

import com.nab.assignment.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

/**
 * Created by Lan Tran on 1/31/21.
 */

class MyApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication>  =
        DaggerAppComponent.factory().create(this)

}