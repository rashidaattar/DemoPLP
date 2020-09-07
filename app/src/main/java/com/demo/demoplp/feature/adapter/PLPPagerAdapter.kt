package com.demo.demoplp.feature.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.demo.demoplp.feature.fragment.PLPFragment
import com.demo.demoplp.remote.model.PLPModel


/**
 * Created by Rashida on 9/7/20.
 *
 */

class PLPPagerAdapter(fm: FragmentManager?,var productsMap:HashMap<String,ArrayList<PLPModel>>) : FragmentStatePagerAdapter(fm){
    override fun getCount() = productsMap.size

    override fun getItem(position: Int): PLPFragment? {
        return productsMap.get(productsMap.keys.toTypedArray()[position])?.let { PLPFragment(it) }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return productsMap.keys.toTypedArray().get(position)
    }

}