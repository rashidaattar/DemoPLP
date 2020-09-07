package com.demo.demoplp.di.module

import android.app.Application
import android.content.Context
import com.demo.demoplp.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides


/**
 * Created by Rashida on 9/7/20.
 *
 */
@Module
class ContextModule(application: Application) {
    private val context: Context
    private val application: Application
    @Provides
    @ApplicationScope
    fun context(): Context {
        return context
    }

    @Provides
    @ApplicationScope
    fun getApplication(): Application {
        return application
    }

    init {
        this.application = application
        context = application.applicationContext
    }
}