/*
 * Created by Andreas Oen on 12/28/20 7:26 PM
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 12/26/20 10:52 AM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.userdetailform.presentation.sheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dre.loyalty.databinding.SheetConfirmationProfileBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ConfirmationSheetModal : BottomSheetDialogFragment() {

    var onClickListener: (() -> Unit)? = null
    private var binding: SheetConfirmationProfileBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SheetConfirmationProfileBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.run {
            btnChange.setOnClickListener {
                dismiss()
            }
            btnConfirm.setOnClickListener {
                onClickListener?.invoke()
                dismiss()
            }
        }
    }

    override fun onDetach() {
        binding = null
        super.onDetach()
    }

    companion object {
        const val TAG = "ConfirmationSheetModal"

        fun newInstance(): ConfirmationSheetModal {
            return ConfirmationSheetModal()
        }
    }
}
