package com.demo.demoplp.di.module

import android.app.Application
import com.demo.demoplp.base.utils.SchedulerProvider
import com.demo.demoplp.di.qualifier.RetrofitQualifier
import com.demo.demoplp.di.scope.ApplicationScope
import com.demo.demoplp.remote.api.PLPApi
import com.demo.demoplp.viewmodel.ListViewModel
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit


/**
 * Created by Rashida on 9/7/20.
 *
 */
@Module
class PLPListModule {
    @Provides
    fun plpAPI(@RetrofitQualifier retrofit: Retrofit): PLPApi {
        return retrofit.create(PLPApi::class.java)
    }

    @Provides
    @ApplicationScope
    fun plpViewModel(
        application: Application,
        schedulerProvider: SchedulerProvider,
        plpApi: PLPApi
    ): ListViewModel {
        return ListViewModel(application, schedulerProvider, plpApi)
    }
}