/*
 * Created by Andreas Oen on 12/31/20 6:30 PM
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 12/31/20 6:30 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.hospital.presentation.screen

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dre.loyalty.R
import com.dre.loyalty.core.networking.exception.Failure
import com.dre.loyalty.core.platform.extension.observe
import com.dre.loyalty.core.platform.extension.viewModel
import com.dre.loyalty.core.platform.navigation.Navigator
import com.dre.loyalty.core.platform.BaseFragment
import com.dre.loyalty.databinding.FragmentHospitalListBinding
import com.dre.loyalty.features.hospital.presentation.entity.EmptyViewState
import com.dre.loyalty.core.model.Hospital
import com.dre.loyalty.core.platform.functional.Event
import com.dre.loyalty.core.view.sheet.ConfirmationSheetModal
import com.dre.loyalty.features.hospital.presentation.item.HospitalListItem
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.mikepenz.fastadapter.listeners.ItemFilterListener
import javax.inject.Inject

class HospitalListFragment : BaseFragment() {

    @Inject
    lateinit var navigator: Navigator

    private lateinit var vm: HospitalListViewModel

    private var binding: FragmentHospitalListBinding? = null

    private val hospitalListItem: ItemAdapter<HospitalListItem> by lazy {
        ItemAdapter<HospitalListItem>()
    }

    private val hospitalListener = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(s: Editable?) {
            hospitalListItem.filter(s.toString())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        vm = viewModel(viewModelFactory) {
            observe(emptyViewState, ::renderEmptyViewState)
            observe(hospitalList, ::renderHospitalList)
            observe(failure, ::renderError)
            observe(loading, ::renderLoading)
            observe(failure, ::showFailureSheet)
            observe(navigateMap, ::showMap)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHospitalListBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindToolbar()
        binding?.etSearch?.editText?.addTextChangedListener(hospitalListener)
        bindHospitalList()
        vm.loadData()
    }

    override fun onDestroyView() {
        (activity as AppCompatActivity).setSupportActionBar(null)
        binding?.etSearch?.editText?.removeTextChangedListener(hospitalListener)
        binding = null
        super.onDestroyView()
    }

    private fun bindToolbar() {
        (activity as AppCompatActivity).run {
            setSupportActionBar(binding?.toolbarLayout?.toolbar)
            supportActionBar?.setTitle(R.string.hospitalList_title)
        }
    }

    private fun bindHospitalList() {
        binding?.rvHospital?.run {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            adapter = FastAdapter.with(listOf(hospitalListItem)).also {
                it.onClickListener = { _, _, item, _ ->
                    item.item.detail?.let { detail ->
                        vm.handleItemClicked(
                            detail.latitude,
                            detail.longitude,
                            item.item.name
                        )
                    }
                    true
                }
            }
        }
        hospitalListItem.itemFilter.run {
            filterPredicate = { item, constraint ->
                item.item.name.toLowerCase().contains(constraint.toString())
            }
            itemFilterListener = object : ItemFilterListener<HospitalListItem> {
                override fun itemsFiltered(constraint: CharSequence?, results: List<HospitalListItem>?) {
                    vm.handleItemWereFiltered(results?.map { it.item } ?: emptyList())
                }

                override fun onReset() {
                    vm.handleListReset()
                }
            }
        }
    }

    private fun renderEmptyViewState(state: EmptyViewState?) {
        state?.visibility?.let {
            binding?.emptyLayout?.visibility = state.visibility
        }
    }

    private fun renderHospitalList(hospitals: List<Hospital>?) {
        if (hospitals == null || hospitals.isEmpty()) {
            if (hospitalListItem.adapterItemCount == 0) {
                renderEmptyViewState(EmptyViewState(View.VISIBLE))
            }
            return
        }
        hospitals.map { hospital ->
            hospitalListItem.add(
                HospitalListItem(hospital).also { item ->
                    item.infoListener = {
                        navigator.showDialPage(requireContext(), it)
                    }
                    item.emergencyListener = {
                        navigator.showDialPage(requireContext(), it)
                    }
                }
            )
        }
    }

    private fun renderError(error: Failure?) {
        if (error == null) return
        renderEmptyViewState(EmptyViewState(View.VISIBLE))
    }

    private fun renderLoading(visibility: Int?) {
        binding?.progress?.visibility = visibility ?: View.GONE
    }

    private fun showFailureSheet(failure: Failure?) {
        if (failure == null) return
        val sheet = getNetworkErrorSheet(failure)?.also {
            it.primaryButtonClickListener = {
                vm.loadData()
            }
            it.secondaryButtonClickListener = {
                navigator.showSetting(requireContext())
            }
        }
        sheet?.show(requireActivity().supportFragmentManager, ConfirmationSheetModal.TAG)
    }

    private fun showMap(event: Event<Triple<Double, Double, String>>?) {
        event?.getIfNotHandled()?.let {
            navigator.showMap(requireContext(), it)
        }
    }

    companion object {
        fun newInstance(): HospitalListFragment {
            return HospitalListFragment()
        }
    }
}
