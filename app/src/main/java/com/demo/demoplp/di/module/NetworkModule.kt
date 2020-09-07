package com.demo.demoplp.di.module

import com.demo.demoplp.di.qualifier.RetrofitQualifier
import com.demo.demoplp.di.scope.ApplicationScope
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


/**
 * Created by Rashida on 9/7/20.
 *
 */

@Module
class NetworkModule {
    /**
     * this method will build an okhttp
     *
     * @return
     */
    @Provides
    fun okhttpClientBuilder(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(READ_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .connectTimeout(CONNECT_TIMEOUT.toLong(), TimeUnit.SECONDS).build()
    }

    @Provides
    @ApplicationScope
    @RetrofitQualifier
    fun retrofitNews(okHttpClient: OkHttpClient?): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.jsonbin.io/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    companion object {
        private const val READ_TIMEOUT = 30
        private const val CONNECT_TIMEOUT = 15
    }
}