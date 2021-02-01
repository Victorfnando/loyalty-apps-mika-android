/*
 *
 * Created by Andreas on 2/1/21 12:26 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 2/1/21 12:26 PM
 * Github Link: https://github.com/oandrz
 * Email: oentoro.andreas@gmail.com
 *
 */

package com.dre.loyalty.features.webview

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.dre.loyalty.databinding.ActivityWebviewBinding
import im.delight.android.webview.AdvancedWebView

class WebViewActivity: AppCompatActivity(), AdvancedWebView.Listener {

    private lateinit var binding: ActivityWebviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebviewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bindToolbar()
        binding.webview.let {
            it.setListener(this, this)
            it.setMixedContentAllowed(false)
            it.loadUrl(intent.extras?.getString(EXTRA_URL))
        }
    }

    override fun onResume() {
        super.onResume()
        binding.webview.onResume()
    }

    override fun onPause() {
        binding.webview.onPause()
        super.onPause()
    }

    override fun onDestroy() {
        binding.webview.onDestroy()
        super.onDestroy()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)
        binding.webview.onActivityResult(requestCode, resultCode, intent)
    }

    override fun onBackPressed() {
        if (!binding.webview.onBackPressed()) {
            return
        }
        super.onBackPressed()
    }

    private fun bindToolbar() {
        setSupportActionBar(binding.toolbarLayout.toolbar)
        supportActionBar?.title = intent.extras?.getString(EXTRA_URL)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onPageStarted(url: String?, favicon: Bitmap?) {
        binding.progress.visibility = View.VISIBLE
    }

    override fun onPageFinished(url: String?) {
        binding.progress.visibility = View.GONE
    }

    override fun onPageError(errorCode: Int, description: String?, failingUrl: String?) {}

    override fun onDownloadRequested(
        url: String?,
        suggestedFilename: String?,
        mimeType: String?,
        contentLength: Long,
        contentDisposition: String?,
        userAgent: String?
    ) {}

    override fun onExternalPageRequest(url: String?) {}

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return true
    }

    companion object {
        private const val EXTRA_URL = "EXTRA_URL"

        fun callingIntent(context: Context, url: String): Intent =
            Intent(context, WebViewActivity::class.java).also {
                it.putExtra(EXTRA_URL, url)
            }
    }
}