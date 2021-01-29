/*
 * Created by Andreas Oen on 12/28/20 7:26 PM
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 12/26/20 10:52 AM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.core.view.sheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.andrefrsousa.superbottomsheet.SuperBottomSheetFragment
import com.bumptech.glide.Glide
import com.dre.loyalty.core.platform.util.enumtype.ConfirmationSheetType
import com.dre.loyalty.databinding.SheetConfirmationBinding

class ConfirmationSheetModal : SuperBottomSheetFragment() {

    var primaryButtonClickListener: (() -> Unit)? = null
    var secondaryButtonClickListener: (() -> Unit)? = null

    private var binding: SheetConfirmationBinding? = null
    private lateinit var sheetType: ConfirmationSheetType

    override fun isSheetAlwaysExpanded(): Boolean {
        return true
    }

    override fun getExpandedHeight(): Int {
        return ViewGroup.LayoutParams.WRAP_CONTENT
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sheetType = arguments?.getSerializable(ARGUMENT_SHEET_TYPE) as ConfirmationSheetType
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = SheetConfirmationBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindBannerImage()
        bindTitle()
        bindDescription()
        bindPrimaryButton()
        bindSecondaryButton()
    }

    private fun bindSecondaryButton() {
        binding?.btnSecondary?.run {
            if (sheetType.secondaryButtonText != null) {
                text = resources.getString(sheetType.secondaryButtonText!!)
            } else {
                visibility = View.GONE
            }
            setOnClickListener {
                secondaryButtonClickListener?.invoke()
                dismiss()
            }
        }
    }

    private fun bindPrimaryButton() {
        binding?.btnPrimary?.run {
            text = resources.getString(sheetType.primaryButtonText)
            setOnClickListener {
                primaryButtonClickListener?.invoke()
                dismiss()
            }
        }
    }

    private fun bindDescription() {
        binding?.tvConfirmationDesc?.text = resources.getString(sheetType.description)
    }

    private fun bindTitle() {
        binding?.tvConfirmationTitle?.text = resources.getString(sheetType.title)
    }

    private fun bindBannerImage() {
        binding?.ivBanner?.let {
            Glide.with(requireContext())
                .load(ContextCompat.getDrawable(requireContext(), sheetType.image))
                .into(it)
        }
    }

    override fun onDetach() {
        binding?.ivBanner?.let { Glide.with(requireContext()).clear(it) }
        binding = null
        super.onDetach()
    }

    companion object {
        const val TAG = "ConfirmationSheetModal"
        private const val ARGUMENT_SHEET_TYPE = "ARGUMENT_SHEET_TYPE"

        fun newInstance(type: ConfirmationSheetType): ConfirmationSheetModal {
            return ConfirmationSheetModal().also {
                val bundle = Bundle()
                bundle.putSerializable(ARGUMENT_SHEET_TYPE, type)
                it.arguments = bundle
            }
        }
    }
}
