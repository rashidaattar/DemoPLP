package com.demo.demoplp.di

import android.app.Application
import com.demo.demoplp.feature.activity.MainActivity
import com.demo.demoplp.base.utils.SchedulerProvider
import com.demo.demoplp.di.module.ContextModule
import com.demo.demoplp.di.module.NetworkModule
import com.demo.demoplp.di.module.PLPListModule
import com.demo.demoplp.di.module.RxModule
import com.demo.demoplp.di.scope.ApplicationScope
import dagger.Component


/**
 * Created by Rashida on 9/7/20.
 *
 */

@ApplicationScope
@Component(modules = [ContextModule::class,
    NetworkModule::class,
    RxModule::class,PLPListModule::class])
interface MainAppComponent {
    fun getApplication(): Application

    fun getSchedulerProvider(): SchedulerProvider

    fun inject(mainActivity: MainActivity)

}