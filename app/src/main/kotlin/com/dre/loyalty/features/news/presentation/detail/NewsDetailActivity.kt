/*
 * Created by Andreas Oen on 1/3/21 9:33 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/3/21 9:33 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.news.presentation.detail

import android.content.Context
import android.content.Intent
import android.view.MenuItem
import com.dre.loyalty.core.platform.BaseActivity
import com.dre.loyalty.core.platform.BaseFragment

class NewsDetailActivity : BaseActivity() {

    override fun fragment(): BaseFragment = NewsDetailFragment.newInstance()

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return true
    }

    companion object {
        fun callingIntent(context: Context): Intent = Intent(context, NewsDetailActivity::class.java)
    }
}
