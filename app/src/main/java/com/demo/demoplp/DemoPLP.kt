package com.demo.demoplp

import android.app.Application
import com.demo.demoplp.di.DaggerMainAppComponent
import com.demo.demoplp.di.MainAppComponent
import com.demo.demoplp.di.module.ContextModule
import com.demo.demoplp.di.module.NetworkModule
import timber.log.Timber
import timber.log.Timber.DebugTree


/**
 * Created by Rashida on 9/7/20.
 *
 */

class DemoPLP : Application() {
    private var mainAppComponent: MainAppComponent? = null


    fun getMainAppComponent(): MainAppComponent? {
        return mainAppComponent
    }

    override fun onCreate() {
        super.onCreate()
        demoPLP = this
        initTimber()
        mainAppComponent = DaggerMainAppComponent.builder()
            .contextModule(ContextModule(this))
            .networkModule(NetworkModule()).build()
    }

    companion object {
        private var demoPLP: DemoPLP? = null
        fun initTimber() {
            if (BuildConfig.DEBUG) {
                Timber.plant(DebugTree())
            }
        }
        fun get(): DemoPLP? {
            return demoPLP
        }

    }

}