package com.demo.demoplp.feature.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.demo.demoplp.databinding.FragmentPlpBinding
import com.demo.demoplp.feature.adapter.PLPCardAdapter
import com.demo.demoplp.remote.model.PLPModel


/**
 * Created by Rashida on 9/7/20.
 *
 */
class PLPFragment(var plpList: ArrayList<PLPModel>) :Fragment(){

    private var binding: FragmentPlpBinding? = null



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPlpBinding.inflate(inflater, container, false)
        val view = binding?.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if(plpList.isNullOrEmpty().not()){
            binding?.listView.apply {
                this?.layoutManager = GridLayoutManager(this?.context, 2)
                this?.adapter = PLPCardAdapter(plpList)

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}

