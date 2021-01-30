/*
 * Created by Andreas Oen on 1/21/21 3:24 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/21/21 2:34 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.profile.presentation.profile.screen.sheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.andrefrsousa.superbottomsheet.SuperBottomSheetFragment
import com.dre.loyalty.R
import com.dre.loyalty.databinding.SheetPhotoSelectorBinding

class PhotoProfileSelectorModal : SuperBottomSheetFragment() {

    private var binding: SheetPhotoSelectorBinding? = null
    var cameraClickedListener: (() -> Unit)? = null
    var galleryClickedListener: (() -> Unit)? = null

    override fun getCornerRadius(): Float {
        return resources.getDimension(R.dimen.bottom_sheet_radius)
    }

    override fun isSheetAlwaysExpanded(): Boolean {
        return true
    }

    override fun getExpandedHeight(): Int {
        return ViewGroup.LayoutParams.WRAP_CONTENT
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = SheetPhotoSelectorBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.run {
            tvGallery.setOnClickListener {
                galleryClickedListener?.invoke()
                dismiss()
            }

            tvCamera.setOnClickListener {
                cameraClickedListener?.invoke()
                dismiss()
            }
        }
    }

    override fun onDestroyView() {
        binding?.tvGallery?.setOnClickListener(null)
        binding?.tvCamera?.setOnClickListener(null)
        binding = null
        super.onDestroyView()
    }

    companion object {
        const val TAG = "PhotoProfileSelector"

        fun newInstance(): PhotoProfileSelectorModal {
            return PhotoProfileSelectorModal()
        }
    }
}
