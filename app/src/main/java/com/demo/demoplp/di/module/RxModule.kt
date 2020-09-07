package com.demo.demoplp.di.module

import com.demo.demoplp.base.utils.SchedulerProvider
import com.demo.demoplp.base.viewmodel.AndroidSchedulerProvider
import com.demo.demoplp.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides


/**
 * Created by Rashida on 9/7/20.
 *
 */
@Module
class RxModule {
    @Provides
    @ApplicationScope
    fun schedulerProvider(): SchedulerProvider {
        return AndroidSchedulerProvider()
    }
}