package com.drawableextensions

import android.content.res.Resources
import android.graphics.drawable.GradientDrawable
import android.view.View
import androidx.annotation.ColorInt

open class DrawableMaker {
    var shape: DrawableShape = DrawableShape.RECTANGLE
    @ColorInt
    var color: Int = 0
    var cornerRadius: Int = 0
    var cornerRadii: CornerRadii? = null
    var gradientOrientation: GradientDrawable.Orientation = GradientDrawable.Orientation.TOP_BOTTOM
    var gradientColors: IntArray? = null
    var dashHeight: Int = 1
    var dashColor: Int = 0
    var dashGap: Int = 0
    var dashWidth: Int = 0
    var gradientType: GradientType = GradientType.LINEAR


    fun build(): GradientDrawable {
        val gradientDrawable: GradientDrawable =
            if (gradientColors.isNotNull()) GradientDrawable(
                gradientOrientation,
                gradientColors
            ).also { it.gradientType = gradientType.ordinal } else GradientDrawable()
        shape.notNull { gradientDrawable.shape = shape.ordinal }

        if (color != 0 && gradientColors.isNull()) gradientDrawable.setColor(color)
        if (cornerRadius != 0) gradientDrawable.cornerRadius = cornerRadius.dpToPx().toFloat()
        cornerRadii.notNull {
            gradientDrawable.cornerRadii = floatArrayOf(
                cornerRadii!!.leftTop.dpToPx().toFloat(),
                cornerRadii!!.leftTop.dpToPx().toFloat(),
                cornerRadii!!.rightTop.dpToPx().toFloat(),
                cornerRadii!!.rightTop.dpToPx().toFloat(),
                cornerRadii!!.rightBottom.dpToPx().toFloat(),
                cornerRadii!!.rightBottom.dpToPx().toFloat(),
                cornerRadii!!.leftBottom.dpToPx().toFloat(),
                cornerRadii!!.leftBottom.dpToPx().toFloat()
            )
        }
        if (dashColor != 0) gradientDrawable.setStroke(dashHeight,
            dashColor,
            dashWidth.dpToPx().toFloat(),
            dashGap.dpToPx().toFloat())
        return gradientDrawable
    }

}

enum class DrawableShape {
    RECTANGLE,
    OVAL,
    LINE,
    RING
}


enum class GradientType {
    LINEAR,
    RADIAL,
    SWEEP
}


data class CornerRadii(
    val leftTop: Int = 0,
    val leftBottom: Int = 0,
    val rightTop: Int = 0,
    val rightBottom: Int = 0,
)

fun View.setDrawable(lambda: DrawableMaker.() -> Unit) = apply {
    backgroundTintList = null
    background = DrawableMaker().apply(lambda).build()
}

fun <T> T.isNotNull(): Boolean {
    return this != null
}

fun <T> T.isNull(): Boolean {
    return this == null
}

fun <T> T.notNull(block: () -> Unit) {
    if (this != null) block.invoke()
}

fun Int.dpToPx(): Int {
    return (this * Resources.getSystem().displayMetrics.density).toInt()
}