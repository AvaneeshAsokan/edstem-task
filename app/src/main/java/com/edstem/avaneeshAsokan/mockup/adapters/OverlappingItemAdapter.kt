package com.edstem.avaneeshAsokan.mockup.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.edstem.avaneeshAsokan.mockup.adapters.viewHolders.NotificationVH
import com.edstem.avaneeshAsokan.mockup.data.UserNotification
import com.edstem.mockup.databinding.OverlappingNotificationItemBinding
import com.edstem.avaneeshAsokan.mockup.utils.getDrawableRes

/**
 * Adapter designed to showcase the functionality of [OverlappingItemDecoration] so the elements
 * can appear slightly stacked on top of each other.
 *
 * @property context
 */
class OverlappingItemAdapter(private val context: Context): RecyclerView.Adapter<NotificationVH>() {
    private val notificationList = arrayListOf<UserNotification>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationVH {
        val view = OverlappingNotificationItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NotificationVH(view)
    }

    override fun onBindViewHolder(holder: NotificationVH, position: Int) {
        holder.binding.profilePic.setImageDrawable(context.getDrawableRes(notificationList[position].profilePicture))
    }

    override fun getItemCount(): Int = notificationList.size

    fun setData(newNotifications: List<UserNotification>) {
        notificationList.clear()
        notificationList.addAll(newNotifications)
        notifyDataSetChanged()
    }
}