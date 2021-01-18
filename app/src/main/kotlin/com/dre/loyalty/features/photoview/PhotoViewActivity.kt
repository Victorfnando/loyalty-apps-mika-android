/*
 * Created by Andreas Oen on 1/18/21 7:15 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/18/21 7:15 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.photoview

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.dre.loyalty.databinding.ActivityPhotoViewBinding

class PhotoViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPhotoViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPhotoViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        bindToolbar()
        val url = intent.extras?.getString(EXTRA_URL).orEmpty()
        Glide.with(this)
            .load(url)
            .into(binding.ivPhoto)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return true
    }

    private fun bindToolbar() {
       setSupportActionBar(binding.toolbarLayout.toolbar)
       supportActionBar?.setDisplayShowTitleEnabled(false)
       supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    companion object {
        private const val EXTRA_URL = "EXTRA_URL"

        fun callingIntent(context: Context, url: String): Intent = Intent(
            context, PhotoViewActivity::class.java
        ).also {
            it.putExtra(EXTRA_URL, url)
        }
    }
}
