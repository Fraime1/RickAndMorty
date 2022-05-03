package com.fraime.android.rm.presentation.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.fraime.android.rm.R
import com.fraime.android.rm.databinding.FragmentDetailsBinding
import com.fraime.android.rm.presentation.ui.list.adapters.RcAdapter
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsFragment : Fragment() {
    private lateinit var binding: FragmentDetailsBinding
    private val detailsViewModel by viewModel<DetailsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = detailsViewModel
        binding.viewModel?.loadId(arguments?.getInt(RcAdapter.ID_KEY ) ?: 2)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel?.character?.observe(viewLifecycleOwner) {
            binding.viewModel?.apply {
                Picasso.get()
                    .load(it.image)
                    .into(binding.imageView)
                name.value = it?.name
                species.value = it?.species
                gender.value = it?.gender
                status.value = it?.status
                location.value = it?.location?.name
                episodes.value = it?.episode?.size.toString()
            }
        }
    }

}