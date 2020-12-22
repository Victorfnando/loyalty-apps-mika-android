/*
 * Created by Andreas Oen on 12/22/20 8:13 PM
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 12/22/20 8:10 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.core.view

import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import android.text.TextPaint
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import com.dre.loyalty.R

private const val DRAWABLE_START = 0
private const val DRAWABLE_END = 2
class PrefixEditText @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = R.style.PrefixEditText
) : AppCompatEditText(context, attrs, defStyleAttr) {

    private var prefixTextWidth: Int = 0
    private var prefixPosX: Float = -1f
    private var paddingStartOriginal: Int = 0
    private var drawablePaddingOriginal: Int = 0

    var prefixText: CharSequence? = null
    var hintText: CharSequence? = null

    private val prefixBackgroundPaint by lazy {
        Paint().apply {
            color = ContextCompat.getColor(context, R.color.gray_3)
        }
    }

    private val prefixTextPaint by lazy {
        TextPaint().apply {
            textSize = context.resources.getDimensionPixelSize(R.dimen.text_size_12sp).toFloat() * 0.98f
            typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
            color = ContextCompat.getColor(context, R.color.colorTextPrimary)
        }
    }

    private var drawableStart: Drawable? = null
    private var drawableStartOnClickListener: OnClickListener? = null
    private var drawableEnd: Drawable? = null
    private var drawableEndOnClickListener: OnClickListener? = null

    init {
        init(context, attrs)
    }

    private fun init(context: Context, attributeSet: AttributeSet?) {
        if (attributeSet != null) {
            val typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.PrefixEditText)
            prefixText = typedArray.getString(R.styleable.PrefixEditText_prefix)
            setPrefix(prefixText)
            typedArray.recycle()
        }
    }

    fun setPrefix(prefix: CharSequence?) {
        prefixText = prefix
        prefixTextWidth = prefixText?.toString()?.let {
            val widths = FloatArray(it.length)
            paint.getTextWidths(it, widths)
            widths.sum().toInt()
        } ?: 0
        paddingStartOriginal = paddingStart
        drawablePaddingOriginal = compoundDrawablePadding
        hintText = hint
        setDrawableStart(null)
    }

    fun setDrawableStart(drawable: Drawable?) {
        if (prefixText.isNullOrBlank()) {
            drawableStart = drawable
            setCompoundDrawablesRelativeWithIntrinsicBounds(
                drawable,
                null,
                drawableEnd,
                null
            )
        }
    }

    fun setDrawableStartOnClickListener(onClickListener: OnClickListener?) {
        drawableStartOnClickListener = onClickListener
    }

    fun setDrawableEnd(drawable: Drawable?) {
        drawableEnd = drawable
        setCompoundDrawablesRelativeWithIntrinsicBounds(
            drawableStart,
            null,
            drawable,
            null
        )
    }

    fun setDrawableEndOnCLickListener(onClickListener: OnClickListener?) {
        drawableEndOnClickListener = onClickListener
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event?.takeIf { isEnabled }?.let { event ->
            compoundDrawables[DRAWABLE_START]?.bounds?.width()
                ?.takeIf { drawableStartOnClickListener != null && event.x < paddingLeft + it }
                ?.let {
                    if (event.action == MotionEvent.ACTION_UP) {
                        drawableStartOnClickListener?.onClick(this)
                    }
                    return true
                }

            compoundDrawables[DRAWABLE_END]?.bounds?.width()
                ?.takeIf { drawableEndOnClickListener != null && event.x > width - paddingRight - it }
                ?.let {
                    if (event.action == MotionEvent.ACTION_UP) {
                        drawableEndOnClickListener?.onClick(this)
                    }
                    return true
                }


            if (!prefixText.isNullOrBlank()) {
                val isInBound = event.x < ((paddingStartOriginal * 2) + prefixTextWidth)
                if (isInBound) {
                    return true
                }
            }
        }

        return super.onTouchEvent(event)
    }


    override fun onFocusChanged(focused: Boolean, direction: Int, previouslyFocusedRect: Rect?) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect)
        setCompoundDrawablesRelativeWithIntrinsicBounds(
            drawableStart,
            null,
            drawableEnd,
            null
        )
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        if (prefixPosX < 0) {
            prefixPosX = compoundPaddingLeft.toFloat()
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (!prefixText.isNullOrBlank()) {
            drawPrefixView(canvas)
        }
    }

    private fun drawPrefixView(canvas: Canvas?) {
        drawBackground(canvas)
        drawText(canvas)
        val prefixPadding = ((paddingStartOriginal * 2) + prefixTextWidth)
        setPadding(
            prefixPadding + (paddingStartOriginal / 2),
            paddingTop,
            paddingRight,
            paddingBottom
        )
    }

    private fun drawBackground(canvas: Canvas?) {
        val prefixPadding = ((paddingStartOriginal * 2) + prefixTextWidth)
        canvas?.drawPath(
            getLeftRoundedRectPath(
                0f, 0f, prefixPadding.toFloat(), height.toFloat(),
                context.resources.getDimensionPixelSize(R.dimen.edittext_radius).toFloat()
            ), prefixBackgroundPaint
        )
    }

    private fun drawText(canvas: Canvas?) {
        canvas?.drawText(
            prefixText?.toString().orEmpty(),
            prefixPosX,
            getLineBounds(0, null).toFloat(),
            prefixTextPaint
        )
    }

    private fun getLeftRoundedRectPath(
        left: Float,
        top: Float,
        right: Float,
        bottom: Float,
        radius: Float
    ): Path {
        var rad = if (radius < 0) 0f else radius
        val path = Path()
        val width = right - left
        val height = bottom - top
        if (rad > width / 2) rad = width / 2
        if (rad > height / 2) rad = height / 2
        val widthMinusCorners = width - rad
        val heightMinusCorners = height - (2 * rad)

        path.moveTo(right, top)
        path.rLineTo(-widthMinusCorners, 0f)
        path.rQuadTo(-rad, 0f, -rad, rad)
        path.rLineTo(0f, heightMinusCorners)
        path.rQuadTo(0f, rad, rad, rad)
        path.rLineTo(widthMinusCorners, 0f)
        path.close()

        return path
    }
}
