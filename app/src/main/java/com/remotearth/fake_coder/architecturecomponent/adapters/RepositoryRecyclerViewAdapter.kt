package com.remotearth.fake_coder.architecturecomponent.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.remotearth.fake_coder.architecturecomponent.models.Repository
import com.remotearth.fake_coder.architecturecomponent.callbacks.OnItemClickCallback
import com.remotearth.fake_coder.architecturecomponent.databinding.ItemRepositoryBinding

class RepositoryRecyclerViewAdapter(
    private var callback: OnItemClickCallback?
) : RecyclerView.Adapter<RepositoryViewHolder>() {

    private var items: ArrayList<Repository>? = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val view = ItemRepositoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RepositoryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return return items?.size ?: 0
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        holder.bind(items?.get(position)!!, callback)
    }
}