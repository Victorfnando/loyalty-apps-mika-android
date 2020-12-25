/*
 * Created by Andreas Oen on 12/25/20 4:18 PM
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 12/25/20 4:18 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.userdetailform.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatRadioButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.LinearLayoutCompat
import com.dre.loyalty.databinding.SheetGenderBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class GenderSheetModal : BottomSheetDialogFragment() {

    var onItemClickListener: ((String) -> Unit)? = null
    private var binding: SheetGenderBinding? = null
    private var selectedItem: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SheetGenderBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.let {
            bindItem(it.layoutGenderMan, it.tvGenderMan, it.radioMan)
            bindItem(it.layoutGenderWoman, it.tvGenderWoman, it.radioWoman)
        }
    }

    private fun bindItem(
        genderLayoutGroup: LinearLayoutCompat,
        genderTv: AppCompatTextView,
        genderRadio: AppCompatRadioButton
    ) {
        genderLayoutGroup.setOnClickListener {
            onItemClickListener?.invoke(genderTv.text.toString())
        }
        selectedItem?.let {
            if (it == genderTv.text.toString()) {
                genderRadio.isSelected = true
            }
        }
    }

    override fun onDetach() {
        binding = null
        super.onDetach()
    }

    companion object {
        const val TAG = "GenderSheetModal"
        const val SELECTED_GENDER_KEY = "SELECTED_GENDER_KEY"

        fun newInstance(selectedItem: String? = null): GenderSheetModal {
            val args = Bundle()
            args.putString(SELECTED_GENDER_KEY, selectedItem)
            val fragment = GenderSheetModal()
            fragment.arguments = args
            return fragment
        }
    }
}
