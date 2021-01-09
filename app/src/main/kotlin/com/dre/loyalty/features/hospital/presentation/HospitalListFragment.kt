/*
 * Created by Andreas Oen on 12/31/20 6:30 PM
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 12/31/20 6:30 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.hospital.presentation

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
import com.dre.loyalty.core.platform.BaseFragment
import com.dre.loyalty.databinding.FragmentHospitalListBinding
import com.dre.loyalty.features.hospital.presentation.entity.Hospital
import com.dre.loyalty.features.hospital.presentation.item.HospitalListItem
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter

class HospitalListFragment : BaseFragment() {

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
    }

    private fun bindToolbar() {
        (activity as AppCompatActivity).run {
            setSupportActionBar(binding?.toolbarLayout?.toolbar)
            supportActionBar?.setTitle(R.string.hospitalList_screen_title)
        }
    }

    private fun bindHospitalList() {
        binding?.rvHospital?.run {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            adapter = FastAdapter.with(hospitalListItem)
        }
        val hospitals = listOf(
                Hospital(
                        "Mitra Keluarga Bekasi",
                        "Jl. Jendral Ahmad Yani, Kayuringin Jaya, Kota Bekasi, Jawa Barat 17144",
                        "(021) 885333333",
                        "(021) 885333333"
                ),
                Hospital(
                        "Mitra Keluarga Bekasi Timur",
                        "Jl. Jendral Ahmad Yani, Kayuringin Jaya, Kota Bekasi, Jawa Barat 17144",
                        "(021) 885333333",
                        "(021) 885333333"
                ),
                Hospital(
                        "Mitra Keluarga Bekasi Bintaro",
                        "Jl. Jendral Ahmad Yani, Kayuringin Jaya, Kota Bekasi, Jawa Barat 17144",
                        "(021) 885333333",
                        "(021) 885333333"
                ),
                Hospital(
                        "Mitra Keluarga Bekasi",
                        "Jl. Jendral Ahmad Yani, Kayuringin Jaya, Kota Bekasi, Jawa Barat 17144",
                        "(021) 885333333",
                        "(021) 885333333"
                ),
                Hospital(
                        "Mitra Keluarga Bekasi Timur",
                        "Jl. Jendral Ahmad Yani, Kayuringin Jaya, Kota Bekasi, Jawa Barat 17144",
                        "(021) 885333333",
                        "(021) 885333333"
                ),
                Hospital(
                        "Mitra Keluarga Bekasi Bintaro",
                        "Jl. Jendral Ahmad Yani, Kayuringin Jaya, Kota Bekasi, Jawa Barat 17144",
                        "(021) 885333333",
                        "(021) 885333333"
                )
        )
        hospitals.map {
            hospitalListItem.add(
                HospitalListItem(it).also {
                    it.infoListener = {

                    }
                    it.emergencyListener = {

                    }
                }
            )
        }
        hospitalListItem.itemFilter.filterPredicate = { item, constraint ->
            item.item.name.toLowerCase().contains(constraint.toString())
        }
    }

    companion object {
        fun newInstance(): HospitalListFragment {
            return HospitalListFragment()
        }
    }
}
