package com.demo.demoplp.feature.activity

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import com.demo.demoplp.DemoPLP
import com.demo.demoplp.R
import com.demo.demoplp.base.presentation.BaseActivity
import com.demo.demoplp.databinding.ActivityMainBinding
import com.demo.demoplp.feature.adapter.PLPPagerAdapter
import com.demo.demoplp.viewmodel.ListViewModel
import javax.inject.Inject

class MainActivity : BaseActivity<ActivityMainBinding>() {

    @Inject
    lateinit var listViewModel: ListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initObservers()
    }

    private fun initObservers() {
        listViewModel.listdata.observe(this, {
            binding?.progressBar?.visibility= View.GONE
            binding?.tabLayout?.setupWithViewPager(binding?.pager)
            binding?.pager?.adapter = PLPPagerAdapter(supportFragmentManager, it)
        })

        listViewModel.showProgress.observe(this, {
            binding?.progressBar?.visibility = if(it == true) View.VISIBLE else View.GONE
        })
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun initViews() {
        DemoPLP.get()?.getMainAppComponent()?.inject(this)
        listViewModel.getList()
    }

    override fun getLayout() = R.layout.activity_main
}