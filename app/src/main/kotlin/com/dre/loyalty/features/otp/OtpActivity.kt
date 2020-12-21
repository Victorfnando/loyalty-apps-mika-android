/*
 *  Created by Andreas Oentoro on 12/20/20 4:56 PM
 *  Copyright (c) 2020 . All rights reserved.
 *  Last modified 12/20/20 4:56 PM
 *  Github Profile: https://github.com/oandrz
 */

package com.dre.loyalty.features.otp

import android.content.Context
import android.content.Intent
import com.dre.loyalty.core.platform.BaseActivity
import com.dre.loyalty.core.platform.BaseFragment

class OtpActivity : BaseActivity() {

    companion object {
        fun callingIntent(context: Context): Intent = Intent(context, OtpActivity::class.java)
    }

    override fun fragment(): BaseFragment = OtpFragment.newInstance()
}
