package com.demo.demoplp.viewmodel

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import com.demo.demoplp.base.utils.SchedulerProvider
import com.demo.demoplp.base.viewmodel.BaseViewModel
import com.demo.demoplp.base.viewmodel.SingleLiveEvent
import com.demo.demoplp.remote.api.PLPApi
import com.demo.demoplp.remote.model.PLPModel
import io.reactivex.Single
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

    val productsMap = SingleLiveEvent<HashMap<String, ArrayList<PLPModel>>>()
    val filteredMap = SingleLiveEvent<HashMap<String, ArrayList<PLPModel>>>()
    private val error = SingleLiveEvent<Int>()
    val showProgress = SingleLiveEvent<Boolean>()
    private var listData = arrayListOf<PLPModel>()


    @RequiresApi(Build.VERSION_CODES.N)
    fun getList() {
        showProgress.postValue(true)
        execute(schedulerProvider, plpApi.getList(), { dataList ->
            run {
                showProgress.postValue(false)
                Timber.d("result size :" + dataList.size)
                this.listData = dataList as ArrayList<PLPModel>
                if (dataList.isNullOrEmpty().not()) {
                    this.productsMap.postValue(getMapFromDataList(dataList))
                }
            }
        }, error)
    }

    private fun getMapFromDataList(
        dataList: List<PLPModel>,
    ): HashMap<String, ArrayList<PLPModel>> {
        val productsMap = hashMapOf<String, ArrayList<PLPModel>>()
        for (plpModel in dataList) {
            if (productsMap.containsKey(plpModel.brand)) {
                productsMap[plpModel.brand]?.add(plpModel)
            } else {
                val plpModelList = arrayListOf<PLPModel>()
                plpModelList.add(plpModel)
                productsMap[plpModel.brand] = plpModelList
            }
        }
        return productsMap
    }

    fun queryData(query:String){
        val filteredList = arrayListOf<PLPModel>()
        if(query.equals(RESET_SEARCH_STRING))
            filteredMap.postValue(productsMap.value)
        else{
            for(plpModel in listData){
                if(plpModel.phone.contains(query,true)||plpModel.brand.contains(query,true))
                    filteredList.add(plpModel)
            }
            filteredMap.postValue(getMapFromDataList(filteredList))
        }


    }

    companion object{
        const val RESET_SEARCH_STRING = "reset"
    }

}