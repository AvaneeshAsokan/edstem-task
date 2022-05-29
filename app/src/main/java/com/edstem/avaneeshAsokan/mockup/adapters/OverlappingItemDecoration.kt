package com.edstem.avaneeshAsokan.mockup.adapters

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

/**
 * Can be used to overlap elements in the x direction by specifying the value of [space]
 *
 * @property space Int, px value of how much the 2nd element onwards must be shifted
 */
class OverlappingItemDecoration(private val space: Int) : ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        if (position != 0) outRect.left = space
    }
}