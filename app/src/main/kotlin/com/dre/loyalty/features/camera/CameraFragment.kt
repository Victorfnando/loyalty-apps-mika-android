/*
 * Created by Andreas Oen on 1/17/21 4:05 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/17/21 4:05 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.camera

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.dre.loyalty.core.platform.BaseFragment
import com.yalantis.ucrop.UCrop
import com.yalantis.ucrop.UCropActivity
import permissions.dispatcher.NeedsPermission
import permissions.dispatcher.RuntimePermissions
import pl.aprilapps.easyphotopicker.DefaultCallback
import pl.aprilapps.easyphotopicker.EasyImage
import pl.aprilapps.easyphotopicker.MediaFile
import pl.aprilapps.easyphotopicker.MediaSource

@RuntimePermissions
class CameraFragment : BaseFragment() {

    private val easyImage: EasyImage by lazy {
        EasyImage.Builder(requireContext())
            .setCopyImagesToPublicGalleryFolder(true)
            .setFolderName("Loyalty App")
            .allowMultiple(true)
            .build()
    }

    private var listener: Listener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as Listener
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showCameraWithPermissionCheck()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_CANCELED) {
            listener?.onCancelled()
        } else if (resultCode == Activity.RESULT_OK) {
            if (requestCode == UCrop.REQUEST_CROP) {
                data?.let {
                    handleCrop(it)
                }
            } else {
                handleImagePicker(requestCode, resultCode, data)
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        onRequestPermissionsResult(requestCode, grantResults)
    }

    override fun onDetach() {
        listener = null
        super.onDetach()
    }

    private fun handleImagePicker(requestCode: Int, resultCode: Int, data: Intent?) {
        easyImage.handleActivityResult(requestCode, resultCode, data, requireActivity(), object : DefaultCallback() {
            override fun onMediaFilesPicked(imageFiles: Array<MediaFile>, source: MediaSource) {
                val sourceUri = Uri.fromFile(imageFiles.first().file)
                val crop = UCrop.of(sourceUri, sourceUri)
                val option = UCrop.Options()
                option.run {
                    setAllowedGestures(UCropActivity.ALL, UCropActivity.ALL, UCropActivity.ALL)
                    withAspectRatio(3f, 4f)
                    setShowCropFrame(false)
                    setShowCropGrid(false)
                    setCompressionQuality(100)
                }
                crop.withOptions(option).start(requireContext(), this@CameraFragment)
            }

            override fun onImagePickerError(error: Throwable, source: MediaSource) {
                super.onImagePickerError(error, source)
                error.printStackTrace()
            }
        })
    }

    private fun handleCrop(data: Intent) {
        UCrop.getOutput(data)?.let {
            listener?.onSuccess(it)
        }
    }

    @NeedsPermission(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
    fun showCamera() {
        easyImage.openCameraForImage(this)
    }

    companion object {
        fun newInstance(): CameraFragment {
            return CameraFragment()
        }
    }

    interface Listener {
        fun onCancelled()
        fun onSuccess(uri: Uri)
    }
}
