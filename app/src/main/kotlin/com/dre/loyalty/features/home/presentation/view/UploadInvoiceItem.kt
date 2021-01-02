/*
 * Created by Andreas Oen on 1/2/21 3:50 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/2/21 3:50 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.home.presentation.view

import android.view.LayoutInflater
import android.view.ViewGroup
import com.dre.loyalty.R
import com.dre.loyalty.databinding.ItemUploadBinding
import com.mikepenz.fastadapter.binding.AbstractBindingItem

class UploadInvoiceItem(val uploadButtonListener: () -> Unit) : AbstractBindingItem<ItemUploadBinding>() {

    override val type: Int
        get() = R.id.uploadItem

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup?): ItemUploadBinding {
        return ItemUploadBinding.inflate(inflater, parent, false)
    }

    override fun bindView(binding: ItemUploadBinding, payloads: List<Any>) {
        super.bindView(binding, payloads)
        binding.btnUploadInvoice.setOnClickListener {
            uploadButtonListener.invoke()
        }
    }

    override fun unbindView(binding: ItemUploadBinding) {
        super.unbindView(binding)
        binding.btnUploadInvoice.setOnClickListener(null)
    }
}
