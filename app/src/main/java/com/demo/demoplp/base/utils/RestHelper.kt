package com.demo.demoplp.base.utils

import androidx.annotation.NonNull
import androidx.annotation.Nullable
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer

import timber.log.Timber


/**
 * Created by Rashida on 9/7/20.
 *
 */

class RestHelper {

    companion object{
        /**
         * @param request
         * @param responseConsumer
         * @param errorConsumer
         * @param <T>
         * @return response or error after executing the network call
        </T> */
        fun <T> makeRequest(
            schedulerProvider: SchedulerProvider,
            request: Observable<T>,
            @NonNull responseConsumer: Consumer<T>?,
            @Nullable errorConsumer: Consumer<Int?>
        ): Disposable? {
            return request.subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .unsubscribeOn(schedulerProvider.io())
                .subscribe(responseConsumer) { throwable ->
                    errorConsumer.accept(ResponseHelper.getErrorState(throwable))
                    if (throwable.message != null) {
                        Timber.e("State " + throwable.message)
                    }
                }
        }
    }
}