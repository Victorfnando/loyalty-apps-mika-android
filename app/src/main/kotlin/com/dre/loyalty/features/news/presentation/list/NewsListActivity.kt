/*
 * Created by Andreas Oen on 1/3/21 5:00 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/3/21 5:00 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.news.presentation.list

import android.content.Context
import android.content.Intent
import android.view.MenuItem
import com.dre.loyalty.core.platform.BaseActivity
import com.dre.loyalty.core.platform.BaseFragment

class NewsListActivity : BaseActivity() {

    override fun fragment(): BaseFragment = NewsListFragment.newInstance(true)

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return true
    }

    companion object {
        fun callingIntent(context: Context): Intent = Intent(context, NewsListActivity::class.java)
    }
}
