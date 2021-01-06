/*
 * Created by Andreas Oen on 1/6/21 7:26 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/6/21 7:26 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.uploadinvoice.presentation

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.dre.loyalty.R
import com.dre.loyalty.core.platform.BaseFragment
import com.dre.loyalty.databinding.FragmentUploadInvoiceBinding
import com.dre.loyalty.features.news.presentation.list.NewsListFragment

class UploadInvoiceFragment : BaseFragment() {

    private var binding: FragmentUploadInvoiceBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUploadInvoiceBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getString(ARGS_IMAGE_URI)?.let {
            Glide.with(this)
                .load(Uri.parse(it))
                .into(binding!!.ivInvoice)
        }
        bindToolbar()
    }

    private fun bindToolbar() {
        (activity as AppCompatActivity).run {
            setSupportActionBar(binding?.toolbarLayout?.toolbar)
            supportActionBar?.setDisplayShowTitleEnabled(false)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }

    companion object {
        private const val ARGS_IMAGE_URI = "ARGS_IMAGE_URI"

        fun newInstance(imageUri: String): UploadInvoiceFragment {
            val args = Bundle()
            args.putString(ARGS_IMAGE_URI, imageUri)
            val fragment = UploadInvoiceFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
