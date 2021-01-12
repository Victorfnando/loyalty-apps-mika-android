/*
 * Created by Andreas Oen on 1/11/21 8:26 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/11/21 8:26 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.core.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.appcompat.widget.LinearLayoutCompat
import com.dre.loyalty.R
import com.dre.loyalty.databinding.ViewTextviewLabelBinding

class TextViewWithLabel @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayoutCompat(context, attrs, defStyleAttr) {

    private var binding: ViewTextviewLabelBinding

    var label: CharSequence? = null
        set(value) {
            field = value
            binding.tvLabel.text = value
        }

    var text: CharSequence? = null
        set(value) {
            field = value
            binding.tvContent.text = value
        }

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = ViewTextviewLabelBinding.inflate(inflater, this, true)
        setWillNotDraw(false)
        initAttribute(attrs)
    }

    private fun initAttribute(attrs: AttributeSet?) {
        val attribute = context.obtainStyledAttributes(attrs, R.styleable.TextViewWithLabel)
        label = attribute.getText(R.styleable.TextViewWithLabel_tvLabel)
        text = attribute.getText(R.styleable.TextViewWithLabel_tvText)
        attribute.recycle()
    }
}
