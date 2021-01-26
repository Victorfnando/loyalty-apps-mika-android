/*
 * Created by Andreas Oen on 1/9/21 7:32 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/9/21 7:32 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.hospital.presentation.item

import android.view.LayoutInflater
import android.view.ViewGroup
import com.dre.loyalty.R
import com.dre.loyalty.databinding.ItemHospitalBinding
import com.dre.loyalty.core.model.Hospital
import com.mikepenz.fastadapter.binding.AbstractBindingItem

class HospitalListItem(val item: Hospital) : AbstractBindingItem<ItemHospitalBinding>() {

    override val type: Int
        get() = R.id.hospitalItem

    var infoListener: ((String) -> Unit)? = null
    var emergencyListener: ((String) -> Unit)? = null

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup?): ItemHospitalBinding {
        return ItemHospitalBinding.inflate(inflater, parent, false)
    }

    override fun bindView(binding: ItemHospitalBinding, payloads: List<Any>) {
        super.bindView(binding, payloads)
        binding.run {
            tvHospitalName.text = item.name
            tvHospitalAddress.text = item.detail?.address
            tvInfo.text = item.detail?.contactInfo
            tvInfo.setOnClickListener {
                item.detail?.contactInfo?.let { phone ->
                    infoListener?.invoke(phone)
                }
            }
            tvEmergency.text = item.detail?.contactEmergency
            tvEmergency.setOnClickListener {
                item.detail?.contactEmergency?.let { phone ->
                    emergencyListener?.invoke(phone)
                }
            }
        }
    }

    override fun unbindView(binding: ItemHospitalBinding) {
        super.unbindView(binding)
        binding.run {
            tvHospitalName.text = null
            tvHospitalAddress.text = null
            tvInfo.text = null
            tvEmergency.text = null
        }
    }
}
