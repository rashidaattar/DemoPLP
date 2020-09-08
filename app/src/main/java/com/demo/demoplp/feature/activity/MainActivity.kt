package com.demo.demoplp.feature.activity

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.demo.demoplp.DemoPLP
import com.demo.demoplp.R
import com.demo.demoplp.base.presentation.BaseActivity
import com.demo.demoplp.databinding.ActivityMainBinding
import com.demo.demoplp.feature.adapter.PLPPagerAdapter
import com.demo.demoplp.viewmodel.ListViewModel
import timber.log.Timber
import javax.inject.Inject

class MainActivity : BaseActivity<ActivityMainBinding>() {

    @Inject
    lateinit var listViewModel: ListViewModel

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initObservers()
        binding?.tabLayout?.setupWithViewPager(binding?.pager)
        listViewModel.getList()

    }

    override fun onNewIntent(intent: Intent?) {
        if (Intent.ACTION_SEARCH == intent?.action) {
            intent.getStringExtra(SearchManager.QUERY)?.also { query ->
                Timber.d("serach query received : $query")
                listViewModel.queryData(query)
            }
        }
    }

    private fun initObservers() {
        listViewModel.productsMap.observe(this, {
            binding?.pager?.adapter = PLPPagerAdapter(supportFragmentManager, it)
        })

        listViewModel.showProgress.observe(this, {
            binding?.progressBar?.visibility = if (it == true) View.VISIBLE else View.GONE
        })

        listViewModel.filteredMap.observe(this, {
            Timber.d("filter map received sine is ${it.size}")
            binding?.progressBar?.visibility = View.GONE
            if (it.isNotEmpty())
                binding?.pager?.adapter = PLPPagerAdapter(supportFragmentManager, it)
            else
                Toast.makeText(this,"No products found for your search",Toast.LENGTH_SHORT).show()
        })
    }

    override fun initViews() {
        DemoPLP.get()?.getMainAppComponent()?.inject(this)
    }

    override fun getLayout() = R.layout.activity_main

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the options menu from XML
        val inflater = menuInflater
        inflater.inflate(R.menu.options_menu, menu)

        // Get the SearchView and set the searchable configuration
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        (menu.findItem(R.id.action_search).actionView as SearchView).apply {
            // Assumes current activity is the searchable activity
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
            setIconifiedByDefault(true) // Do not iconify the widget; expand it by default

            this.setOnCloseListener(object : SearchView.OnCloseListener {
                override fun onClose(): Boolean {
                    Timber.d("closing search")
                    this@apply.setQuery(
                        ListViewModel.RESET_SEARCH_STRING,
                        true
                    ) //adding text as reset here as setquery implementation has is empty check
                    this@apply.setIconified(true)
                    return true
                }
            })
        }

        return true
    }
}