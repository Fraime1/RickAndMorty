package com.fraime.android.rm.presentation.ui.list.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.NavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.fraime.android.rm.R
import com.fraime.android.rm.databinding.FragmentListItemBinding
import com.fraime.android.rm.domain.model.Character
import com.fraime.android.rm.presentation.ui.list.ListItemViewModel
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
            binding.viewModel?.toDetails(navController, binding.viewModel?.id?.value)
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
        getItem(position)?.let { holder.bind(it) }
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