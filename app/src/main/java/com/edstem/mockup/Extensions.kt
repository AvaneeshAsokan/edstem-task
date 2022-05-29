package com.edstem.mockup

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.util.TypedValue
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat

/**
 * returns the required drawable
 *
 * @param res Int
 * @return
 */
fun Context.getDrawableRes(res: Int) =
    ResourcesCompat.getDrawable(resources, res, theme)

/**
 * converts dp to px
 */
val Float.px: Float
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this,
        Resources.getSystem().displayMetrics
    )

/**
 * converts dp to px
 */
val Int.px: Int get() = toFloat().px.toInt()

fun Context.toast(msg: String, short: Boolean = true) {
    Toast.makeText(this, msg, if (short) Toast.LENGTH_SHORT else Toast.LENGTH_LONG).show()
}

/**
 * makes the activity go fullscreen
 */
fun Activity.hideSystemBars() {
//    WindowCompat.setDecorFitsSystemWindows(window, false)
    WindowInsetsControllerCompat(window, window.decorView).apply {
        hide(WindowInsetsCompat.Type.statusBars())
        hide(WindowInsetsCompat.Type.navigationBars())
        systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
    }
}