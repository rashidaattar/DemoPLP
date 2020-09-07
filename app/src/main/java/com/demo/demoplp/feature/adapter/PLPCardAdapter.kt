package com.demo.demoplp.feature.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.demo.demoplp.databinding.ViewProductCardBinding
import com.demo.demoplp.remote.model.PLPModel


/**
 * Created by Rashida on 9/7/20.
 *
 */

class PLPCardAdapter(var plpList: ArrayList<PLPModel>) : RecyclerView.Adapter<PLPCardViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PLPCardViewHolder {
        return PLPCardViewHolder(
            ViewProductCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PLPCardViewHolder, position: Int) {
        holder.bindData(plpList.get(position))
    }

    override fun getItemCount() = plpList.size
}
class PLPCardViewHolder(var binding: ViewProductCardBinding) : RecyclerView.ViewHolder(binding.root){

    fun bindData(data: PLPModel){
        binding.tvProductName.text = data.phone
        binding.tvProductPrice.text=data.priceEur
        Glide.with(binding.root.context).load(data.picture).into(binding.ivProduct)
    }

}