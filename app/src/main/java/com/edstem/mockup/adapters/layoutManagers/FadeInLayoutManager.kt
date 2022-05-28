package com.edstem.mockup.adapters.layoutManagers

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.abs

class FadeInLayoutManager(context: Context, orientation: Int, reverse: Boolean):LinearLayoutManager(context, orientation, reverse) {
    private fun updateChildrenAlpha() {
        for (i in 0 until childCount) {
            val child = getChildAt(i)!!
            val maxDist = height / 3 /* distance where alpha is min */
            val right = getDecoratedRight(child)
            val left = getDecoratedLeft(child)
            val childCenter = left + (right - left) / 2 // Get item center position
            val center = width / 2 // Get RecyclerView's center position

            val alpha = 1 - (abs(maxDist.toFloat() - abs(center - childCenter)) / (maxDist * 10))
            child.alpha = if (alpha < 0) 0F else alpha
        }
    }

    override fun scrollHorizontallyBy(
        dx: Int,
        recycler: RecyclerView.Recycler?,
        state: RecyclerView.State?
    ): Int {
        updateChildrenAlpha()
        return super.scrollHorizontallyBy(dx, recycler, state)
    }

    override fun onLayoutChildren(recycler: RecyclerView.Recycler?, state: RecyclerView.State?) {
        super.onLayoutChildren(recycler, state)
        updateChildrenAlpha()
    }
}