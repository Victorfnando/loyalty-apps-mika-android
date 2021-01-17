/*
 * Created by Andreas Oen on 1/17/21 4:04 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/17/21 4:04 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.camera

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.dre.loyalty.core.platform.BaseActivity
import com.dre.loyalty.core.platform.BaseFragment

class CameraActivity : BaseActivity(), CameraFragment.Listener {
    override fun fragment(): BaseFragment = CameraFragment.newInstance()

    override fun onCancelled() {
        setResult(RESULT_CANCELED)
        finish()
    }

    override fun onSuccess(uri: Uri) {
        val intent = Intent()
        intent.putExtra(EXTRA_URI, uri.toString())
        setResult(RESULT_OK, intent)
        finish()
    }

    companion object {
        const val EXTRA_URI = "EXTRA_URI"
        const val REQUEST_CODE_CAMERA = 101
        fun callingIntent(context: Context): Intent = Intent(context, CameraActivity::class.java)
    }
}
