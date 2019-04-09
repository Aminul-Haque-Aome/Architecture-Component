package com.remotearth.fake_coder.architecturecomponent.adapters

import androidx.recyclerview.widget.RecyclerView
import com.remotearth.fake_coder.architecturecomponent.models.Repository
import com.remotearth.fake_coder.architecturecomponent.callbacks.OnItemClickCallback
import com.remotearth.fake_coder.architecturecomponent.databinding.ItemRepositoryBinding

class RepositoryViewHolder(
    private var bindingView: ItemRepositoryBinding
) : RecyclerView.ViewHolder(bindingView.root) {

    fun bind(item: Repository, callback: OnItemClickCallback?) {
        bindingView.repository = item

        if (callback != null) {
            bindingView.root.setOnClickListener {
                callback.onItemClicked(item)
            }
        }

        bindingView.executePendingBindings()
    }
}