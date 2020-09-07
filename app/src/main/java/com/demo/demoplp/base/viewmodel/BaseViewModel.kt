package com.demo.demoplp.base.viewmodel

import android.app.Application
import android.os.Build
import androidx.annotation.CallSuper
import androidx.annotation.NonNull
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.demo.demoplp.base.utils.ResponseHelper
import com.demo.demoplp.base.utils.RestHelper
import com.demo.demoplp.base.utils.SchedulerProvider
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import java.util.function.Consumer


/**
 * Created by Rashida on 9/7/20.
 *
 */

open class BaseViewModel(@NonNull application: Application) :
    AndroidViewModel(application) {
    @NonNull
    private val compositeDisposable = CompositeDisposable()
    @CallSuper
    override protected fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    protected fun <T> execute(
        schedulerProvider: SchedulerProvider,
        requestObservable: Observable<T>,
        responseLiveData: Consumer<T>,
        errorLiveData: MutableLiveData<Int?>
    ) {
        compositeDisposable.add(
            RestHelper.makeRequest(schedulerProvider, requestObservable,
                { responseEntity ->
                    if (responseEntity != null) responseLiveData.accept(
                        responseEntity
                    ) else errorLiveData.postValue(ResponseHelper.OTHER)
                }
            ) { errorEntity -> errorLiveData.postValue(errorEntity) }!!
        )
    }
}
