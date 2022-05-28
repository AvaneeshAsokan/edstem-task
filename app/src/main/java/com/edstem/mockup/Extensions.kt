package com.edstem.mockup

import android.content.Context
import android.content.res.Resources
import android.util.TypedValue
import androidx.core.content.res.ResourcesCompat

fun Context.getDrawableRes(res: Int) =
    ResourcesCompat.getDrawable(resources, res, theme)

/**
 * converts dp to px
 */
val Float.px: Float get() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this, Resources.getSystem().displayMetrics)

/**
 * converts dp to px
 */
val Int.px: Int get() = toFloat().px.toInt()