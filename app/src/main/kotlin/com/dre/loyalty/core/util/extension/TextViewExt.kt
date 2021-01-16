/*
 * Created by Andreas Oen on 1/16/21 7:34 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/16/21 7:34 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.core.util.extension

import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RectShape
import android.widget.TextView

fun TextView.addBorder(
    color : Int = Color.GRAY,
    width : Float = 10F
){
    // initialize a shape drawable
    val drawable = ShapeDrawable().apply {
        // specify the shape of shape drawable
        shape = RectShape()

        paint.apply {
            // specify the border color of shape
            this.color = color
            // set the border width
            strokeWidth = width
            // specify the style is a Stroke
            style = Paint.Style.STROKE
        }
    }
    // finally, add the shape drawable background to text view
    background = drawable
    setTextColor(color)
}
