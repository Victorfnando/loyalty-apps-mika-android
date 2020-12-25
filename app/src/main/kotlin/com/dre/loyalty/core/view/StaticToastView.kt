/*
 * Created by Andreas Oen on 12/25/20 2:32 PM
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 12/25/20 2:32 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.core.view

import android.content.Context
import android.text.Html
import android.text.Spanned
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.text.HtmlCompat
import com.dre.loyalty.R
import com.dre.loyalty.databinding.ViewStaticToastBinding

class StaticToastView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayoutCompat(context, attrs, defStyleAttr) {
    private var binding: ViewStaticToastBinding

    var label: CharSequence? = null
        set(value) {
            field = value
            binding.tvToastLabel.text = value
            binding.tvToastLabel.visibility = if (value.isNullOrEmpty()) {
                View.GONE
            } else {
                View.VISIBLE
            }
        }

    var desc: CharSequence? = null
        set(value) {
            field = value
            binding.tvToastDesc.text = value
        }

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = ViewStaticToastBinding.inflate(inflater, this, true)
        setWillNotDraw(false)
        initAttribute(attrs)
    }

    private fun initAttribute(attrs: AttributeSet?) {
        val attribute = context.obtainStyledAttributes(attrs, R.styleable.StaticToastView)
        label = attribute.getText(R.styleable.StaticToastView_label)
        desc = attribute.getText(R.styleable.StaticToastView_desc)
        attribute.recycle()
    }

    fun fromHtml(html: String) {
        binding.tvToastDesc.text = HtmlCompat.fromHtml(html, HtmlCompat.FROM_HTML_MODE_LEGACY)
    }
}
