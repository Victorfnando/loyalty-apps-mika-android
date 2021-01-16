/*
 * Created by Andreas Oen on 1/16/21 3:52 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/16/21 3:52 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.invoice.presentation.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dre.loyalty.features.invoice.presentation.screen.InvoiceListPagerFragment

class InvoicePagingAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment =
        InvoiceListPagerFragment.newInstance(position)
}
