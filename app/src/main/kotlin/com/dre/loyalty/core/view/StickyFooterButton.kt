/*
 * Created by Andreas Oen on 1/12/21 7:06 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/12/21 7:06 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.core.view

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.widget.LinearLayoutCompat
import com.dre.loyalty.R
import com.dre.loyalty.databinding.ViewStickyFooterButtonBinding

class StickyFooterButton @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayoutCompat(context, attrs, defStyleAttr) {

    private var binding: ViewStickyFooterButtonBinding

    var isButtonEnabled: Boolean = true
        set(value) {
            field = value
            binding.btnSubmit.isEnabled = value
        }

    var buttonText: CharSequence? = null
        set(value) {
            field = value
            binding.btnSubmit.text = value
        }

    var tvFooterText: CharSequence? = null
        set(value) {
            field = value
            binding.tvFooter.text = value
        }

    var tvFooterVisibility: Int? = View.VISIBLE
        set(value) {
            field = value
            binding.tvFooter.visibility = value ?: View.GONE
        }

    var buttonClickListener: (() -> Unit)? = null
        set(value) {
            field = value
            binding.btnSubmit.setOnClickListener {
                value?.invoke()
            }
        }

    var tvFooterClickListener: (() -> Unit)? = null
        set(value) {
            field = value
            binding.tvFooter.setOnClickListener {
                value?.invoke()
            }
        }

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = ViewStickyFooterButtonBinding.inflate(inflater, this, true)
        initAttribute(attrs)
    }

    private fun initAttribute(attrs: AttributeSet?) {
        val attribute = context.obtainStyledAttributes(attrs, R.styleable.StickyFooterButton)
        isButtonEnabled = attribute.getBoolean(R.styleable.StickyFooterButton_footerButtonEnable, false)
        buttonText = attribute.getText(R.styleable.StickyFooterButton_footerButtonText)
        tvFooterText = attribute.getText(R.styleable.StickyFooterButton_footerLabel)
        tvFooterVisibility = attribute.getInt(R.styleable.StickyFooterButton_footerLabelVisibility, View.VISIBLE)
        attribute.recycle()
    }

    fun setFooterGravity(gravity: Int) {
        binding.tvFooter.gravity = gravity
    }
}
