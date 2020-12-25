/*
 * Created by Andreas Oen on 12/25/20 12:13 PM
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 12/25/20 12:13 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.userdetailform.presentation

import android.content.Context
import android.content.Intent
import com.dre.loyalty.core.platform.BaseActivity
import com.dre.loyalty.core.platform.BaseFragment

class UserDetailFormActivity : BaseActivity() {

    override fun fragment(): BaseFragment = UserDetailFormFragment()

    companion object {
        fun callingIntent(context: Context) = Intent(context, UserDetailFormActivity::class.java)
    }
}
