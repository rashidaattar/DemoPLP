package com.demo.demoplp.viewmodel

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import com.demo.demoplp.base.utils.SchedulerProvider
import com.demo.demoplp.base.viewmodel.BaseViewModel
import com.demo.demoplp.base.viewmodel.SingleLiveEvent
import com.demo.demoplp.remote.api.PLPApi
import com.demo.demoplp.remote.model.PLPModel
import timber.log.Timber


/**
 * Created by Rashida on 9/7/20.
 *
 */
class ListViewModel(
    application: Application,
    var schedulerProvider: SchedulerProvider,
    var plpApi: PLPApi
) : BaseViewModel(application) {

    val listdata = SingleLiveEvent<HashMap<String,ArrayList<PLPModel>>>()
    private val error = SingleLiveEvent<Int>()
    val showProgress = SingleLiveEvent<Boolean>()


    @RequiresApi(Build.VERSION_CODES.N)
    fun getList() {
        showProgress.postValue(true)
        execute(schedulerProvider, plpApi.getList(), { dataList ->
            run {
                showProgress.postValue(false)
                Timber.d("result size :" + dataList.size)
                if (dataList.isNullOrEmpty().not()) {
                    var productsMap = hashMapOf<String,ArrayList<PLPModel>>()
                    for(plpModel in dataList){
                        if(productsMap.containsKey(plpModel.brand)){
                            productsMap[plpModel.brand]?.add(plpModel)
                        }

                        else{
                            val plpModelList= arrayListOf<PLPModel>()
                            plpModelList.add(plpModel)
                            productsMap[plpModel.brand] = plpModelList
                        }
                    }
                    listdata.postValue(productsMap)
                }
            }
        }, error)
    }

}