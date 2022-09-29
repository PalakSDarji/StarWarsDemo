package com.palak.starwarsdemo.home.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.palak.starwarsdemo.R
import com.palak.starwarsdemo.databinding.ItemLoadingBinding

class LoaderAdapter(val retry : ()->Unit) : LoadStateAdapter<LoaderAdapter.LoaderViewHolder>() {

    class LoaderViewHolder(private val binding: ItemLoadingBinding, private val retry : ()->Unit) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(loadState: LoadState) {
            with(binding) {
                binding.progressBar.isVisible = loadState is LoadState.Loading
                binding.btnTryAgain.isVisible = loadState !is LoadState.Loading
                binding.tvErrorMessage.isVisible = loadState !is LoadState.Loading
                if(loadState is LoadState.Error){
                    binding.tvErrorMessage.text = loadState.error.localizedMessage
                }
                binding.btnTryAgain.setOnClickListener {
                    retry.invoke()
                }
                executePendingBindings()
            }
        }
    }

    override fun onBindViewHolder(holder: LoaderViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoaderViewHolder {
        return LoaderViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_loading, parent, false
            ), retry
        )
    }
}