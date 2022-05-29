package com.edstem.avaneeshAsokan.mockup.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.edstem.mockup.R
import com.edstem.avaneeshAsokan.mockup.adapters.viewHolders.ReminderVH
import com.edstem.avaneeshAsokan.mockup.data.ReminderItems
import com.edstem.mockup.databinding.BirthdayItemBinding
import com.edstem.avaneeshAsokan.mockup.enums.ReminderType
import com.edstem.avaneeshAsokan.mockup.utils.getDrawableRes
import java.text.SimpleDateFormat
import java.util.*

/**
 * Recycler adapter implementation that compares the current system date with the date in the data
 * available and shows the birthday Icon if the days match.
 *
 * I have also provided the ability to filter and reuse the adapter for showing other events that
 * can be specified with [ReminderType]
 *
 * Note: The current implementation of the adapter using [notifyDataSetChanged] instead of [DiffUtil]
 *
 * @property context
 */
class EventReminderAdapter(private val context: Context): RecyclerView.Adapter<ReminderVH>() {

    private val filteredList = arrayListOf<ReminderItems>()
    private val reminderList = arrayListOf<ReminderItems>()

    private var showType = ReminderType.BIRTHDAY

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReminderVH {
        val view = BirthdayItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ReminderVH(view)
    }

    override fun onBindViewHolder(holder: ReminderVH, position: Int) {
        with(holder.binding){
            name.text = filteredList[position].name
            birthdayIcon.visibility =
                if (isEventToday(filteredList[position].date)) View.VISIBLE else View.GONE
            dateOfBirth.text = filteredList[position].date

            profilePic.setImageDrawable(when(position) {
                1 -> context.getDrawableRes(R.drawable.hm2)
                2 -> context.getDrawableRes(R.drawable.hm3)
                else -> context.getDrawableRes(R.drawable.hm1)
            })
        }
    }

    override fun getItemCount(): Int = filteredList.size

    fun setData(newMembersList: List<ReminderItems>, reminderType: ReminderType){
        reminderList.clear()
        reminderList.addAll(newMembersList)
        filteredList.clear()
        filteredList.addAll(newMembersList.filter { item -> item.eventType == reminderType || reminderType == ReminderType.ALL })
        notifyDataSetChanged()  //  Fixme: should be changed to diff util implementation
    }

    fun setShowType(newType: ReminderType){
        showType = newType
        filteredList.clear()
        filteredList.addAll(reminderList.filter { item -> item.eventType == showType || showType == ReminderType.ALL })
        notifyDataSetChanged()
    }

    /**
     * Compares the current system date with the data received by the adapter
     *
     * @param date
     * @return Boolean, true if the dates match else false
     */
    private fun isEventToday(date: String): Boolean {
        //  May 30 2022, Monday
        val df = SimpleDateFormat("MMM dd yyyy, EEEE", Locale.getDefault())
        val today = Calendar.getInstance().apply{
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }

        return today.time == df.parse(date)
    }
}