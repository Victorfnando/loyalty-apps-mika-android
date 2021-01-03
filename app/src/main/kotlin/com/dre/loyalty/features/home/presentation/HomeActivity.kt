/*
 * Created by Andreas Oen on 12/30/20 8:15 PM
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 12/30/20 8:15 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.home.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.dre.loyalty.R
import com.dre.loyalty.core.extension.inTransaction
import com.dre.loyalty.core.platform.BaseFragment
import com.dre.loyalty.databinding.ActivityHomeBinding
import com.dre.loyalty.features.hospital.presentation.HospitalListFragment
import com.dre.loyalty.features.invoice.presentation.InvoiceListFragment
import com.dre.loyalty.features.news.presentation.list.NewsListFragment
import com.dre.loyalty.features.profile.presentation.ProfileFragment

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bindBottomNavigation(savedInstanceState)
    }

    private fun bindBottomNavigation(savedInstanceState: Bundle?) {
        binding.navigation.run {
            setOnNavigationItemSelectedListener { item ->
                when(item.itemId) {
                    R.id.menu_home -> {
                        replaceFragment(savedInstanceState, HomeFragment.newInstance())
                        true
                    }
                    R.id.menu_hospital -> {
                        replaceFragment(savedInstanceState, HospitalListFragment.newInstance())
                        true
                    }
                    R.id.menu_invoice -> {
                        replaceFragment(savedInstanceState, InvoiceListFragment.newInstance())
                        true
                    }
                    R.id.menu_news -> {
                        replaceFragment(savedInstanceState, NewsListFragment.newInstance())
                        true
                    }
                    R.id.menu_profile -> {
                        replaceFragment(savedInstanceState, ProfileFragment.newInstance())
                        true
                    }
                    else -> throw IllegalStateException("Unsupported Id")
                }
            }
            selectedItemId = R.id.menu_home
        }
    }

    override fun onBackPressed() {
        (supportFragmentManager.findFragmentById(binding.fragmentContainer.id) as BaseFragment).onBackPressed()
        super.onBackPressed()
    }

    private fun replaceFragment(savedInstanceState: Bundle?, fragment: Fragment) =
        savedInstanceState ?: supportFragmentManager.inTransaction { replace(binding.fragmentContainer.id, fragment) }

    companion object {
        fun callingActivity(context: Context): Intent = Intent(context, HomeActivity::class.java)
    }
}
