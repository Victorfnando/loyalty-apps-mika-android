/*
 * Created by Andreas Oen on 12/24/20 6:38 PM
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 12/24/20 6:38 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.core.view

import android.content.Context
import android.text.InputFilter
import android.text.InputFilter.LengthFilter
import android.text.InputType
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.widget.LinearLayoutCompat
import com.dre.loyalty.R
import com.dre.loyalty.databinding.ViewPrefixedittextWithLabelBinding


class PrefixEditTextWithLabel @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayoutCompat(context, attrs, defStyleAttr) {

    private var binding: ViewPrefixedittextWithLabelBinding

    var error: CharSequence? = null
        set(value) {
            field = value
            binding.tvError.apply {
                text = value
                visibility = if (value.isNullOrEmpty()) {
                    View.GONE
                } else {
                    View.VISIBLE
                }
            }
        }

    var label: CharSequence? = null
        set(value) {
            field = value
            binding.tvLabel.apply {
                text = value
                visibility = if (value.isNullOrEmpty()) {
                    View.GONE
                } else {
                    View.VISIBLE
                }
            }
        }

    var hint: CharSequence? = null
        set(value) {
            field = value
            editText.hintText = value
            editText.hint = value
        }

    var inputType: Int = InputType.TYPE_NULL
        set(value) {
            field = value
            editText.inputType = value
        }

    var prefix: CharSequence? = null
        set(value) {
            field = value
            editText.setPrefix(value)
        }
    var maxLength: Int = Int.MAX_VALUE
        set(value) {
            field = value
            editText.filters = arrayOf<InputFilter>(LengthFilter(maxLength))
        }

    val editText: PrefixEditText
        get() = binding.etInput

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = ViewPrefixedittextWithLabelBinding.inflate(inflater, this, true)
        setWillNotDraw(false)
        initAttribute(attrs)
    }

    private fun initAttribute(attrs: AttributeSet?) {
        val attribute = context.obtainStyledAttributes(attrs, R.styleable.PrefixEditTextWithLabel)
        error = attribute.getText(R.styleable.PrefixEditTextWithLabel_error)
        label = attribute.getText(R.styleable.PrefixEditTextWithLabel_label)
        hint = attribute.getText(R.styleable.PrefixEditTextWithLabel_android_hint)
        prefix = attribute.getText(R.styleable.PrefixEditTextWithLabel_prefixText)
        inputType = attribute.getInt(R.styleable.PrefixEditTextWithLabel_android_inputType, InputType.TYPE_NULL)
        maxLength = attribute.getInt(R.styleable.PrefixEditTextWithLabel_android_maxLength, Int.MAX_VALUE)
        attribute.recycle()
    }
}
