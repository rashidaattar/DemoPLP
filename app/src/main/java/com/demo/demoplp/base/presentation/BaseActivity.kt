package com.demo.demoplp.base.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding


/**
 * Created by Rashida on 9/7/20.
 *
 */
abstract class BaseActivity<B : ViewDataBinding?> : AppCompatActivity() {
    protected var binding: B? = null
    var TAG: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        TAG = localClassName
        //init data binding
        binding = DataBindingUtil.setContentView(this, getLayout())
        initViews()
    }

    protected abstract fun initViews()
    abstract fun getLayout(): Int
}
