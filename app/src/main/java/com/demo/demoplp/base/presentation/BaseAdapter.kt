package com.demo.demoplp.base.presentation

import androidx.recyclerview.widget.RecyclerView


/**
 * Created by Rashida on 9/7/20.
 *
 */
abstract class BaseAdapter<T, E : RecyclerView.ViewHolder?> :
    RecyclerView.Adapter<E?>() {
    protected var list: List<T>? = null
    fun setData(list: List<T>?) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return if (list != null && !list!!.isEmpty()) list!!.size else 0
    }
}