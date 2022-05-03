package com.fraime.android.rm.presentation.ui.list.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fraime.android.rm.R
import com.fraime.android.rm.databinding.FragmentListProgressBinding

class CharacterLoadStateAdapter : LoadStateAdapter<CharacterLoadStateAdapter.ProgressHolder>() {

    class ProgressHolder(private val binding: FragmentListProgressBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(loadState: LoadState) = with(binding) {
                progressBar.isVisible = loadState is LoadState.Loading
            }
    }

    override fun onBindViewHolder(holder: ProgressHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): ProgressHolder {
        val binding = DataBindingUtil.inflate<FragmentListProgressBinding>(
            LayoutInflater.from(parent.context),
            R.layout.fragment_list_progress,
            parent,
            false
        )
        return ProgressHolder(binding)
    }
}