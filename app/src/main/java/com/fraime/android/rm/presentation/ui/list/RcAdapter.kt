package com.fraime.android.rm.presentation.ui.list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.paging.PagingData
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.fraime.android.rm.R
import com.fraime.android.rm.databinding.FragmentListItemBinding
import com.fraime.android.rm.domain.model.Character
import com.squareup.picasso.Picasso

class RcAdapter(
    private val viewLifecycleOwner: LifecycleOwner,
    private val navController: NavController,
    private val owner : ViewModelStoreOwner
) :
    PagingDataAdapter<Character, RcAdapter.CharacterViewHolder>(CharacterDiffItemCallback) {

    inner class CharacterViewHolder(private val binding: FragmentListItemBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        init {
            binding.lifecycleOwner = viewLifecycleOwner
            itemView.setOnClickListener(this)
        }

        fun bind(character: Character) {
            Picasso.get()
                .load(character.image)
                .into(binding.iconCharacter)
            binding.viewModel?.character = character
            binding.executePendingBindings()
        }

        override fun onClick(v: View?) {
            navController.navigate(
                R.id.action_listFragment_to_detailsFragment,
                bundleOf(ID_KEY to binding.viewModel?.id?.value)
            )
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding = DataBindingUtil.inflate<FragmentListItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.fragment_list_item,
            parent,
            false
        )
        binding.viewModel = ListItemViewModel().apply {
            ViewModelProvider(owner).get(ListItemViewModel::class.java)
        }
        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }

    companion object {
        const val ID_KEY = "idKey"
    }

    private object CharacterDiffItemCallback : DiffUtil.ItemCallback<Character>() {
        override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem == newItem
        }

    }
}