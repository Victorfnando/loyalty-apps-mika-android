/*
 * Created by Andreas Oen on 1/17/21 4:04 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/17/21 4:04 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.camera

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.PersistableBundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.dre.loyalty.core.platform.BaseActivity
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
class CameraActivity : AppCompatActivity() {

    private val easyImage: EasyImage by lazy {
        EasyImage.Builder(this)
            .setCopyImagesToPublicGalleryFolder(true)
            .setFolderName("Loyalty App")
            .allowMultiple(true)
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showCameraWithPermissionCheck()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        onRequestPermissionsResult(requestCode, grantResults)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_CANCELED) {
            onCancelled()
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

    @NeedsPermission(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
    fun showCamera() {
        easyImage.openCameraForImage(this)
    }

    private fun onCancelled() {
        setResult(RESULT_CANCELED)
        finish()
    }

    private fun onSuccess(uri: Uri) {
        val intent = Intent()
        intent.putExtra(EXTRA_URI, uri.toString())
        setResult(RESULT_OK, intent)
        finish()
    }

    private fun handleImagePicker(requestCode: Int, resultCode: Int, data: Intent?) {
        easyImage.handleActivityResult(requestCode, resultCode, data, this, object : DefaultCallback() {
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
                crop.withOptions(option).start(this@CameraActivity)
            }

            override fun onImagePickerError(error: Throwable, source: MediaSource) {
                super.onImagePickerError(error, source)
                error.printStackTrace()
            }
        })
    }

    private fun handleCrop(data: Intent) {
        UCrop.getOutput(data)?.let {
            onSuccess(it)
        }
    }

    companion object {
        const val EXTRA_URI = "EXTRA_URI"
        const val REQUEST_CODE_CAMERA = 101
        fun callingIntent(context: Context): Intent = Intent(context, CameraActivity::class.java)
    }
}
