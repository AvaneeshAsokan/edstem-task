package com.edstem.mockup

import android.content.Context
import androidx.core.content.res.ResourcesCompat

fun Context.getDrawableRes(res: Int) =
    ResourcesCompat.getDrawable(resources, res, theme)