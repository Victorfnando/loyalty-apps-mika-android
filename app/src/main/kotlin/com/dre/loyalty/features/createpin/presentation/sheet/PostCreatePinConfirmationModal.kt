/*
 * Created by Andreas Oen on 12/28/20 7:26 PM
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 12/26/20 10:52 AM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.createpin.presentation.sheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dre.loyalty.databinding.SheetConfirmationCreatepinBinding
import com.dre.loyalty.features.createpin.presentation.enums.CreatePinType
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class PostCreatePinConfirmationModal : BottomSheetDialogFragment() {

    var onClickListener: (() -> Unit)? = null
    private var binding: SheetConfirmationCreatepinBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SheetConfirmationCreatepinBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val type = arguments?.getSerializable(CREATE_PIN_TYPE_ARGUMENT) as CreatePinType
        binding?.run {
            btnLogin.setOnClickListener {
                onClickListener?.invoke()
                dismiss()
            }
            tvTitle.text = resources.getText(type.sheetTitle ?: 0)
            tvDesc.text = resources.getText(type.sheetDesc ?: 0)
        }
    }

    override fun onDetach() {
        binding = null
        super.onDetach()
    }

    companion object {
        const val TAG = "PostCreatePinConfirmationModal"
        private const val CREATE_PIN_TYPE_ARGUMENT = "CREATE_PIN_TYPE_ARGUMENT"

        fun newInstance(type: CreatePinType): PostCreatePinConfirmationModal {
            val bundle = Bundle().also { it.putSerializable(CREATE_PIN_TYPE_ARGUMENT, type) }
            val modal = PostCreatePinConfirmationModal()
            modal.arguments = bundle
            return modal
        }
    }
}
