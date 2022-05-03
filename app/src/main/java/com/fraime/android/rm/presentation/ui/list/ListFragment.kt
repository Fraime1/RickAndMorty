package com.fraime.android.rm.presentation.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.fraime.android.rm.R
import com.fraime.android.rm.databinding.FragmentListBinding
import com.fraime.android.rm.presentation.ui.list.adapters.CharacterLoadStateAdapter
import com.fraime.android.rm.presentation.ui.list.adapters.RcAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListFragment : Fragment() {
    private lateinit var binding: FragmentListBinding
    private val listViewModel by viewModel<ListViewModel>()

    private val adapter by lazy (LazyThreadSafetyMode.NONE){
        RcAdapter(this, findNavController(), this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false)
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
        }
        binding.recyclerView.adapter = adapter.withLoadStateFooter(CharacterLoadStateAdapter())
        adapter.addLoadStateListener { state: CombinedLoadStates ->
            binding.recyclerView.isVisible = state.refresh != LoadState.Loading
            binding.progress.isVisible = state.refresh == LoadState.Loading
        }
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listViewModel.listCharacter.observe(viewLifecycleOwner){
            adapter.submitData(lifecycle, it)
        }
    }

}