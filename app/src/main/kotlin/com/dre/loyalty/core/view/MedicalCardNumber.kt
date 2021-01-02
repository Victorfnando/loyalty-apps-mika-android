package com.dre.loyalty.core.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.dre.loyalty.databinding.ViewMedicalCardBinding

class MedicalCardNumber @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var binding: ViewMedicalCardBinding

    var name: CharSequence? = null
        set(value) {
            field = value
            binding.tvCardName.text = value
        }

    var medicalNumber: CharSequence? = null
        set(value) {
            field = value
            binding.tvMedicalNumber.text = value
        }

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = ViewMedicalCardBinding.inflate(inflater, this, true)
        setWillNotDraw(false)
    }
}
